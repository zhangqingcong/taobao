package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 通过feign调用goods微服务中的方法 把数据库中审核通过的商品导入es中去
     */
    @Override
    public void importSku() {
        Result<List<Sku>> SkuListResult = skuFeign.findByStatus("1");
        List<Sku> data = SkuListResult.getData();
        List<SkuInfo> skuInfos = JSON.parseArray(JSON.toJSONString(data), SkuInfo.class);
        for (SkuInfo skuInfo : skuInfos) {
            Map<String, Object> map = JSON.parseObject(skuInfo.getSpec(), Map.class);
            skuInfo.setSpecMap(map);
        }
        skuEsMapper.saveAll(skuInfos);
    }

    /**
     * 关键字查询
     *
     * @param searchMap
     * @return
     */
    @Override
    public Map search(Map<String, String> searchMap) {
        //1.获取关键字的值
        String keywords = searchMap.get("keywords");
        if (StringUtils.isEmpty(keywords)) {
            keywords = "华为";//相当于给keywords一个默认的值
        }
        //2.创建查询对象的 构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //3.设置查询的条件
        //把 商品分类 作为分组条件
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategorygroup").field("categoryName").size(50));
        //把 商品品牌 设为分组的条件
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrandgroup").field("brandName").size(50));
        //设置分组条件 商品的规格
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuSpecgroup").field("spec.keyword").size(1000));

        //设置高亮条件
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name"));
        nativeSearchQueryBuilder.withHighlightBuilder(new HighlightBuilder().preTags("<em style=\"color:red\">").postTags("</em>"));

        //设置主关键字查询
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keywords,"name","brandName","categoryName"));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(searchMap.get("brand"))){
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandName",searchMap.get("brand")));
        }

        if (StringUtils.isEmpty("category")){
            boolQueryBuilder.filter(QueryBuilders.termQuery("categoryName",searchMap.get("category")));
        }
        if (searchMap!=null){
            for (String key : searchMap.keySet()) {
                if (key.startsWith("spec_")){
                    boolQueryBuilder.filter(QueryBuilders.termQuery("specMap."+key.substring(5)+".keyword",searchMap.get(key)));
                }
            }
        }
        String price = searchMap.get("price");
        if (!StringUtils.isEmpty(price)){
            String[] split = price.split("-");
            if (!split[1].equalsIgnoreCase("*")){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").from(split[0],true).to(split[1],true));
            }else {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(split[0]));
            }
        }
        String sortRule = searchMap.get("sortRule");
        String sortField = searchMap.get("sortField");
        if (!StringUtils.isEmpty(sortRule)&&!StringUtils.isEmpty(sortField)){
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(sortRule.equals("DESC")? SortOrder.DESC:SortOrder.ASC));
        }
        //构建过滤查询
        nativeSearchQueryBuilder.withFilter(boolQueryBuilder);

        //构建分页查询
        Integer pageNum = 1;
        if (!StringUtils.isEmpty(searchMap.get("pageNum"))){
            try{
                Integer.valueOf(searchMap.get("pageNum"));
            }catch (NumberFormatException e){
                e.printStackTrace();
                pageNum=1;
            }
        }
        Integer pageSize =3;
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum-1,pageSize));

        //4.构建查询的对象
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        //5.执行查询
        AggregatedPage<SkuInfo> skuPage = elasticsearchTemplate.queryForPage(query, SkuInfo.class,new SearchResultMapperImpl());

        //获取分组结果
        StringTerms stringTerms = (StringTerms) skuPage.getAggregation("skuCategorygroup");
        StringTerms stringTermsBrand = (StringTerms) skuPage.getAggregation("skuBrandgroup");
        StringTerms stringTermsSpec = (StringTerms) skuPage.getAggregation("skuSpecgroup");
        List<String> categoryList = getStringsCategoryList(stringTerms);
        List<String> brandList = getStringsBrandList(stringTermsBrand);
        Map<String, Set<String>> specMap = getStringSetMap(stringTermsSpec);

        //6.返回结果
        Map resultMap = new HashMap<>();
        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        resultMap.put("totalPages", skuPage.getTotalPages());
        resultMap.put("categoryList", categoryList);
        resultMap.put("brandList", brandList);
        resultMap.put("specMap", specMap);

        return resultMap;
    }

    /**
     * 抽取的获取分组的方法
     *
     * @param stringTerms
     * @return
     */
    private List<String> getStringsCategoryList(StringTerms stringTerms) {
        List<String> categoryList = new ArrayList<>();
        if (stringTerms != null) {
            for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
                String keyAsString = bucket.getKeyAsString();
                categoryList.add(keyAsString);
            }
        }
        return categoryList;
    }

    /**
     * 获取品牌列表
     *
     * @param stringTermsBrand
     * @return
     */
    private List<String> getStringsBrandList(StringTerms stringTermsBrand) {
        List<String> brandList = new ArrayList<>();
        if (stringTermsBrand != null) {
            for (StringTerms.Bucket bucket : stringTermsBrand.getBuckets()) {
                brandList.add(bucket.getKeyAsString());
            }
        }
        return brandList;
    }

    /**
     * 获取规格分组
     *
     * @param stringTermsSpec
     * @return
     */
    private Map<String, Set<String>> getStringSetMap(StringTerms stringTermsSpec) {
        Map<String, Set<String>> specMap = new HashMap<>();
        Set<String> specList = new HashSet<>();
        if (stringTermsSpec != null) {
            for (StringTerms.Bucket bucket : stringTermsSpec.getBuckets()) {
                specList.add(bucket.getKeyAsString());
            }
        }
        for (String specjson : specList) {
            Map<String, String> map = JSON.parseObject(specjson, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Set<String> specValues = specMap.get(key);
                if (specValues == null) {
                    specValues = new HashSet<String>();
                }
                specValues.add(value);
                specMap.put(key, specValues);
            }
        }
        return specMap;
    }
}
