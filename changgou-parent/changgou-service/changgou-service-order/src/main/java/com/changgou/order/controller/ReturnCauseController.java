package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.ReturnCause;
import com.changgou.order.service.ReturnCauseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returnCause")
@CrossOrigin
public class ReturnCauseController {
    @Autowired
    private ReturnCauseService returnCauseService;

    @GetMapping
    public Result<List<ReturnCause>> findAll() {
        List<ReturnCause> returnCauseList = returnCauseService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有returnCause成功");
    }

    @GetMapping("/{id}")
    public Result<ReturnCause> findById(@PathVariable(name = "id") Integer id) {
        ReturnCause returnCause = returnCauseService.findById(id);
        return new Result<>(true, StatusCode.OK, "根据id查询returnCause成功", returnCause);
    }

    @PostMapping
    public Result add(@RequestBody ReturnCause returnCause) {
        returnCauseService.add(returnCause);
        return new Result(true, StatusCode.OK, "新增returnCause成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(name = "id") Integer id) {
        returnCauseService.delete(id);
        return new Result(true, StatusCode.OK, "根据id删除returnCause成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody ReturnCause returnCause, @PathVariable(name = "id") Integer id) {
        returnCause.setId(id);
        returnCauseService.update(returnCause);
        return new Result(true, StatusCode.OK, "根据id更新returnCause成功");
    }

    @GetMapping("/search")
    public Result<List<ReturnCause>> findList(@RequestBody ReturnCause returnCause) {
        List<ReturnCause> returnCauseList = returnCauseService.findList(returnCause);
        return new Result<>(true, StatusCode.OK, "条件查询returnCause成功", returnCauseList);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnCause>> findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<ReturnCause> causePageInfo = returnCauseService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页查询returnCause成功", causePageInfo);
    }

    @PostMapping("/search/{page}/{size}")
    public Result findPage(ReturnCause returnCause, Integer page, Integer size) {
        PageInfo<ReturnCause> returnCausePageInfo = returnCauseService.findPage(returnCause, page, size);
        return new Result(true, StatusCode.OK, "分页条件查询returnCause成功", returnCausePageInfo);
    }
}
