package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.Order;
import com.changgou.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public Result<List<Order>> findAll() {
        List<Order> orderList = orderService.findAll();
        return new Result<List<Order>>(true, StatusCode.OK, "查询所有order成功", orderList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Order order = orderService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询order成功", order);
    }

    @PostMapping
    public Result add(@RequestBody Order order) {
        orderService.add(order);
        return new Result(true, StatusCode.OK, "新增order成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        orderService.delete(id);
        return new Result(true, StatusCode.OK, "删除order成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Order order, @PathVariable String id) {
        order.setId(id);
        orderService.update(order);
        return new Result(true, StatusCode.OK, "修改order成功");
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Order>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        PageInfo<Order> orderPageInfo = orderService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页查询order成功", orderPageInfo);
    }

    @GetMapping("/search")
    public Result<List<Order>> findList(@RequestBody Order order) {
        List<Order> orderList = orderService.findList(order);
        return new Result(true, StatusCode.OK, "条件查询order成功", orderList);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Order>> findPage(@RequestBody Order order,@PathVariable Integer page,@PathVariable Integer size){
        PageInfo<Order> orderList = orderService.findPage(order, page, size);
        return new Result(true,StatusCode.OK,"分页条件查询order成功",orderList);
    }
}
