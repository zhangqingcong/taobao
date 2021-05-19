package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.ReturnOrder;
import com.changgou.order.service.ReturnOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/returnOrder")
@CrossOrigin
public class ReturnOrderController {
    @Autowired
    private ReturnOrderService returnOrderService;

    @GetMapping
    public Result<List<ReturnOrder>> findAll() {
        List<ReturnOrder> returnOrderList = returnOrderService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有returnOrder成功", returnOrderList);
    }

    @GetMapping("/{id}")
    public Result<ReturnOrder> findById(@PathVariable(name = "id") BigInteger id) {
        ReturnOrder returnOrder = returnOrderService.findById(id);
        return new Result<>(true, StatusCode.OK, "根据id查询returnOrder成功", returnOrder);
    }

    @PostMapping
    public Result add(@RequestBody ReturnOrder returnOrder) {
        returnOrderService.add(returnOrder);
        return new Result(true, StatusCode.OK, "新增returnOrder成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(name = "id") BigInteger id) {
        returnOrderService.delete(id);
        return new Result(true, StatusCode.OK, "根据id删除returnOrder成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody ReturnOrder returnOrder, @PathVariable(name = "id") BigInteger id) {
        returnOrder.setId(id);
        returnOrderService.update(returnOrder);
        return new Result(true, StatusCode.OK, "根据id更新returnOrder成功");
    }

    @GetMapping("/search")
    public Result<List<ReturnOrder>> findList(@RequestBody ReturnOrder returnOrder) {
        List<ReturnOrder> returnOrderList = returnOrderService.findList(returnOrder);
        return new Result<>(true, StatusCode.OK, "条件查询returnOrder成功", returnOrderList);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnOrder>> findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<ReturnOrder> returnOrderPageInfo = returnOrderService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询returnOrder成功", returnOrderPageInfo);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnOrder>> findPage(@RequestBody ReturnOrder returnOrder, @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<ReturnOrder> returnOrderPageInfo = returnOrderService.findPage(returnOrder, page, size);
        return new Result<PageInfo<ReturnOrder>>(true, StatusCode.OK, "分页条件查询成功", returnOrderPageInfo);
    }
}
