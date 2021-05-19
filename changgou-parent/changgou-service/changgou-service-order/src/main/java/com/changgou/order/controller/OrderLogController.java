package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.OrderConfig;
import com.changgou.order.pojo.OrderLog;
import com.changgou.order.service.OrderLogService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderLog")
@CrossOrigin
public class OrderLogController {
    @Autowired
    private OrderLogService orderLogService;

    @GetMapping
    public Result<List<OrderConfig>> findAll() {
        List<OrderLog> orderLogList = orderLogService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有orderLog成功", orderLogList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        OrderLog orderLog = orderLogService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询orderLog成功", orderLog);
    }

    @PostMapping
    public Result add(@RequestBody OrderLog orderLog) {
        orderLogService.add(orderLog);
        return new Result(true, StatusCode.OK, "新增orderLog成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        orderLogService.delete(id);
        return new Result(true, StatusCode.OK, "根据id删除orderLog成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody OrderLog orderLog, @PathVariable(name = "id") String id) {
        orderLog.setId(id);
        orderLogService.update(orderLog);
        return new Result(true, StatusCode.OK, "根据id更新orderLog成功");
    }

    @GetMapping("/search")
    public Result<List<OrderLog>> findList(@RequestBody OrderLog orderLog) {
        List<OrderLog> orderLogList = orderLogService.findList(orderLog);
        return new Result<>(true, StatusCode.OK, "条件查询orderLog成功", orderLogList);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderLog>> findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<OrderLog> logPageInfo = orderLogService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页查询orderLog成功", logPageInfo);
    }

    @PostMapping("/search/{page}/{size}")
    public Result findPage(@RequestBody OrderLog orderLog, @PathVariable Integer page, @PathVariable Integer size) {
        PageInfo<OrderLog> orderLogPageInfo = orderLogService.findPage(orderLog, page, size);
        return new Result(true, StatusCode.OK, "分页条件查询orderLog成功", orderLogPageInfo);
    }
}
