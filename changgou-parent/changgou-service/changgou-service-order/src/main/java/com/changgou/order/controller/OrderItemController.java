package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.OrderItemService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
@CrossOrigin
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping()
    public Result<List<OrderItem>> findAll() {
        List<OrderItem> orderItemList = orderItemService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有orderItem成功", orderItemList);
    }

    @GetMapping("/{id}")
    public Result<OrderItem> findById(@PathVariable String id) {
        OrderItem orderItem = orderItemService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询orderItem成功", orderItem);
    }

    @PostMapping
    public Result add(@RequestBody OrderItem orderItem) {
        orderItemService.add(orderItem);
        return new Result(true, StatusCode.OK, "新增orderItem成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        orderItemService.delete(id);
        return new Result(true, StatusCode.OK, "根据id删除orderItem成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody OrderItem orderItem, @PathVariable String id) {
        orderItem.setId(id);
        orderItemService.update(orderItem);
        return new Result(true, StatusCode.OK, "根据id更新orderItem成功");
    }

    @GetMapping("/search")
    public Result<List<OrderItem>> findList(@RequestBody OrderItem orderItem) {
        List<OrderItem> orderItemList = orderItemService.findList(orderItem);
        return new Result(true, StatusCode.OK, "条件查询orderItem成功", orderItem);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderItem>> findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<OrderItem> orderItemPageInfo = orderItemService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页查询orderItem成功", orderItemPageInfo);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderItem>> findPage(@RequestBody OrderItem orderItem, @PathVariable Integer page, @PathVariable Integer size) {
        PageInfo<OrderItem> orderItemPageInfo = orderItemService.findPage(orderItem, page, size);
        return new Result(true, StatusCode.OK, "分页条件查询orderItem成功", orderItemPageInfo);
    }
}
