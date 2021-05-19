package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.OrderConfig;
import com.changgou.order.service.OrderConfigService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderConfig")
@CrossOrigin
public class OrderConfigController {
    @Autowired
    private OrderConfigService orderConfigService;

    @GetMapping
    public Result<List<OrderConfig>> findAll() {
        List<OrderConfig> orderConfigList = orderConfigService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有OrderConfig成功", orderConfigList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        OrderConfig orderConfig = orderConfigService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询OrderConfig成功", orderConfig);
    }

    @PostMapping
    public Result add(@RequestBody OrderConfig orderConfig) {
        orderConfigService.add(orderConfig);
        return new Result(true, StatusCode.OK, "新增orderConfig成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        orderConfigService.delete(id);
        return new Result(true, StatusCode.OK, "根据id删除orderConfig成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody OrderConfig orderConfig, @PathVariable Integer id) {
        orderConfig.setId(id);
        orderConfigService.update(orderConfig);
        return new Result(true, StatusCode.OK, "根据id修改orderConfig成功");
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderConfig>> findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<OrderConfig> configPageInfo = orderConfigService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页查询orderConfig成功", configPageInfo);
    }

    @GetMapping("/search/")
    public Result<List<OrderConfig>> findList(@RequestBody OrderConfig orderConfig){
        List<OrderConfig> orderConfigList = orderConfigService.findList(orderConfig);
        return new Result(true,StatusCode.OK,"条件查询orderConfig成功",orderConfigList);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderConfig>> findPage(@RequestBody OrderConfig orderConfig,@PathVariable(name = "page") Integer page,@PathVariable(name = "size") Integer size){
        PageInfo<OrderConfig> orderConfigPageInfo = orderConfigService.findPage(orderConfig, page, size);
        return new Result(true,StatusCode.OK,"分页条件查询orderConfig成功",orderConfigPageInfo);
    }
}
