package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.ReturnOrderItem;
import com.changgou.order.service.ReturnOrderItemService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/returnOrderItem")
@CrossOrigin
public class ReturnOrderItemController {
    @Autowired
    private ReturnOrderItemService returnOrderItemService;

    @GetMapping
    public Result<List<ReturnOrderItem>> findAll() {
        List<ReturnOrderItem> returnOrderItemList = returnOrderItemService.findAll();
        return new Result<List<ReturnOrderItem>>(true, StatusCode.OK, "查询所有returnOrderItemList成功", returnOrderItemList);
    }

    @GetMapping("/{id}")
    public Result<ReturnOrderItem> findById(@PathVariable(name = "id") BigInteger id) {
        ReturnOrderItem returnOrderItem = returnOrderItemService.findById(id);
        return new Result<>(true, StatusCode.OK, "根据id查询returnOrerItem成功");
    }

    @PostMapping
    public Result add(@RequestBody ReturnOrderItem returnOrderItem) {
        returnOrderItemService.add(returnOrderItem);
        return new Result(true, StatusCode.OK, "新增returnOrderItem成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(name = "id") BigInteger id) {
        returnOrderItemService.delete(id);
        return new Result(true, StatusCode.OK, "根据id删除returnOrderItem成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody ReturnOrderItem returnOrderItem, @RequestParam BigInteger id) {
        returnOrderItem.setId(id);
        returnOrderItemService.update(returnOrderItem);
        return new Result(true, StatusCode.OK, "根据id更新returnOrderItem成功");
    }

    @GetMapping("/search")
    public Result findList(@RequestBody ReturnOrderItem returnOrderItem) {
        List<ReturnOrderItem> returnOrderItemList = returnOrderItemService.findList(returnOrderItem);
        return new Result(true, StatusCode.OK, "条件查询returnOrderItem成功", returnOrderItemList);
    }

    @GetMapping("/search/{page}/{size}")
    public Result findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<ReturnOrderItem> orderItemPageInfo = returnOrderItemService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页查询returnOrderItem成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findPage(@RequestBody ReturnOrderItem returnOrderItem, @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        returnOrderItemService.findPage(returnOrderItem, page, size);
        return new Result(true, StatusCode.OK, "条件分页查询returnOrderItem成功");
    }
}
