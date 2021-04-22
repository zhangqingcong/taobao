package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface BrandMapper extends Mapper<Brand> {

    @Select("select tb.* from tb_category_brand tcb,tb_brand tb where  tb.id = tcb.brand_id and tcb.category_id=#{categoryId}")
    List<Brand> findByCategoryId(Integer categoryId);
}
