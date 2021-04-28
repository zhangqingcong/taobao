package com.changgou.search.controller;

import com.changgou.entity.Page;
import com.changgou.search.feign.SkuFeign;
import com.changgou.search.pojo.SkuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/search")
public class SkuController {
    @Autowired
    private SkuFeign skuFeign;

    @GetMapping(value = "/list")
    public String search(@RequestParam(required = false) Map searchMap, Model model){
        //1.调用搜索微服务的feign 根据搜索的条件参数 查询数据
        Map resultMap = skuFeign.search(searchMap);
        //2.将数据设置到model中 模板文件中根据 th：标签数据展示
        model.addAttribute("result",resultMap);
        //3.设置搜索的条件回显
        model.addAttribute("searchMap",searchMap);

        //4.记住之前的URL
        //拼接url
        String url = url(searchMap);
        model.addAttribute("url",url);
        return "search";
    }

    private String url(Map<String,String> searchMap){
        String url = "/search/list";
        if (searchMap!=null&&searchMap.size()>0){
            url+="?";
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("pageNum")){
                    continue;
                }
                url+=key+"="+value+"&";
            }
            if (url.lastIndexOf("&")!=-1){
                url=url.substring(0,url.lastIndexOf("&"));
            }
        }
        return url;
    }


}
