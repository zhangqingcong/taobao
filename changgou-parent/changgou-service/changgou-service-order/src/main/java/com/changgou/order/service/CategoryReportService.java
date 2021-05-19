package com.changgou.order.service;

import com.changgou.order.pojo.CategoryReport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface CategoryReportService {
    List<CategoryReport> findAll();
    CategoryReport findByCountDate(Date countDate);
    void add(CategoryReport categoryReport);
    void delete(Date countDate);
    void update(CategoryReport categoryReport);
    PageInfo<CategoryReport> findPage(Integer page, Integer size);
    List<CategoryReport> findList(CategoryReport categoryReport);
    PageInfo<CategoryReport> findPage(CategoryReport categoryReport,Integer page,Integer size);
}
