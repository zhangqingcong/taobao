package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.Preferential;
import com.changgou.order.service.PreferentialService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preferential")
@CrossOrigin
public class PreferentialController {
    @Autowired
    private PreferentialService preferentialService;

    @GetMapping
    public Result<List<Preferential>> findAll() {
        List<Preferential> preferentialList = preferentialService.findAll();
        return new Result(true, StatusCode.OK, "查询所有preferential成功", preferentialList);
    }

    @GetMapping("/{id}")
    public Result<Preferential> findById(@PathVariable(name = "id") Integer id) {
        Preferential preferential = preferentialService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询preferential成功", preferential);
    }

    @PostMapping
    public Result add(@RequestBody Preferential preferential) {
        preferentialService.add(preferential);
        return new Result(true, StatusCode.OK, "新增preferential成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(name = "id") Integer id) {
        preferentialService.delete(id);
        return new Result(true, StatusCode.OK, "根据id删除preferential成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Preferential preferential, @PathVariable Integer id) {
        preferential.setId(id);
        preferentialService.update(preferential);
        return new Result(true, StatusCode.OK, "根据id更新preferential成功");
    }

    @GetMapping("/search")
    public Result<List<Preferential>> findList(@RequestBody Preferential preferential) {
        List<Preferential> preferentialList = preferentialService.findList(preferential);
        return new Result<>(true, StatusCode.OK, "条件查询preferential成功");
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Preferential>> findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        PageInfo<Preferential> preferentialPageInfo = preferentialService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询preferential成功", preferentialPageInfo);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Preferential>> findPage(@RequestBody Preferential preferential,@PathVariable(name = "page") Integer page,@PathVariable(name = "size") Integer size){
        PageInfo<Preferential> preferentialPageInfo = preferentialService.findPage(preferential, page, size);
        return new Result(true,StatusCode.OK,"分页条件查询preferential成功",preferentialPageInfo);
    }
}
