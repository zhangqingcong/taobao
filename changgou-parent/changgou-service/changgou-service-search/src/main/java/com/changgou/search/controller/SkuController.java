package com.changgou.search.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 导入数据到MySQL
     *
     * @return
     */
    @RequestMapping("/import")
    public Result importEs() {
        skuService.importSku();
        return new Result(true, StatusCode.OK, "导入成功");
    }

    @GetMapping
    public Map search(@RequestParam Map searchMap) {
        return skuService.search(searchMap);
    }
}
