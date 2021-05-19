package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.CategoryReport;
import com.changgou.order.service.CategoryReportService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/categoryReport")
@CrossOrigin
public class CategoryReportController {
    @Autowired
    private CategoryReportService categoryReportService;

    @GetMapping
    public Result<List<CategoryReport>> findAll() {
        List<CategoryReport> reportList = categoryReportService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有CategoryReport成功", reportList);
    }

    @GetMapping("/{countDate}")
    public Result<CategoryReport> findByCountDate(@PathVariable(name = "countDate") Date countDate) {
        CategoryReport categoryReport = categoryReportService.findByCountDate(countDate);
        return new Result<>(true, StatusCode.OK, "根据countDate查询CategoryReport成功", categoryReport);
    }

    @PostMapping
    public Result add(@RequestBody CategoryReport categoryReport) {
        categoryReportService.add(categoryReport);
        return new Result(true, StatusCode.OK, "新增categoryReport成功");
    }

    @DeleteMapping("/{countDate}")
    public Result delete(@PathVariable Date countDate){
        categoryReportService.delete(countDate);
        return new Result(true,StatusCode.OK,"根据id删除countDate成功");
    }

    @PutMapping("/{countDate}")
    public Result update(@RequestBody CategoryReport categoryReport,@PathVariable Date countDate){
        categoryReport.setCountDate(countDate);
        categoryReportService.update(categoryReport);
        return new Result(true,StatusCode.OK,"修改CategoryReport成功");
    }

    @GetMapping("/search")
    public Result<List<CategoryReport>> findList(@RequestBody CategoryReport categoryReport){
        List<CategoryReport> categoryReportList = categoryReportService.findList(categoryReport);
        return new Result<>(true,StatusCode.OK,"条件查询CategoryReport成功",categoryReportList);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<CategoryReport>> findPage(@PathVariable(name = "page") Integer page,@PathVariable(name = "size") Integer size){
        PageInfo<CategoryReport> categoryReportPageInfo = categoryReportService.findPage(page, size);
        return new Result<>(true,StatusCode.OK,"分页查询CategoryReport成功",categoryReportPageInfo);
    }

    @PostMapping("/search/{page}/{size}")
    public Result findPage(CategoryReport categoryReport,Integer page,Integer size){
        PageInfo<CategoryReport> categoryReportPageInfo = categoryReportService.findPage(categoryReport, page, size);
        return new Result(true,StatusCode.OK,"分页条件查询categoryReport成功",categoryReportPageInfo);
    }
}
