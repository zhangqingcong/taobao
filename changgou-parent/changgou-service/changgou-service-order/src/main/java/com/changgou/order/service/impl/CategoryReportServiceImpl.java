package com.changgou.order.service.impl;

import com.changgou.order.dao.CategoryReportMapper;
import com.changgou.order.pojo.CategoryReport;
import com.changgou.order.service.CategoryReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CategoryReportServiceImpl implements CategoryReportService {
    @Autowired
    private CategoryReportMapper categoryReportMapper;

    @Override
    public List<CategoryReport> findAll() {
        return categoryReportMapper.selectAll();
    }

    @Override
    public CategoryReport findByCountDate(Date countDate) {
        return categoryReportMapper.selectByPrimaryKey(countDate);
    }

    @Override
    public void add(CategoryReport categoryReport) {
        categoryReportMapper.insertSelective(categoryReport);
    }

    @Override
    public void delete(Date countDate) {
        categoryReportMapper.deleteByPrimaryKey(countDate);
    }

    @Override
    public void update(CategoryReport categoryReport) {
        categoryReportMapper.updateByPrimaryKeySelective(categoryReport);
    }

    @Override
    public PageInfo<CategoryReport> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return (PageInfo<CategoryReport>) categoryReportMapper.selectAll();
    }

    @Override
    public List<CategoryReport> findList(CategoryReport categoryReport) {
        Example example = createExample(categoryReport);
        return categoryReportMapper.selectByExample(example);
    }

    @Override
    public PageInfo<CategoryReport> findPage(CategoryReport categoryReport, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(categoryReport);
        return (PageInfo<CategoryReport>) categoryReportMapper.selectByExample(example);
    }

    private Example createExample(CategoryReport categoryReport) {
        Example example = new Example(CategoryReport.class);
        Example.Criteria criteria = example.createCriteria();
        if (categoryReport != null) {
            if (StringUtils.isEmpty(categoryReport.getCategoryId1())) {
                criteria.andEqualTo("categoryId1", categoryReport.getCategoryId1());
            }
            if (StringUtils.isEmpty(categoryReport.getCategoryId2())) {
                criteria.andEqualTo("categoryId2", categoryReport.getCategoryId2());
            }
            if (StringUtils.isEmpty(categoryReport.getCategoryId3())) {
                criteria.andEqualTo("categoryId3", categoryReport.getCategoryId3());
            }
            if (StringUtils.isEmpty(categoryReport.getCountDate())) {
                criteria.andGreaterThanOrEqualTo("countDate", categoryReport.getCountDate());
            }
            if (StringUtils.isEmpty(categoryReport.getNum())) {
                criteria.andEqualTo("num", categoryReport.getNum());
            }
            if (StringUtils.isEmpty(categoryReport.getMoney())) {
                criteria.andEqualTo("money", categoryReport.getMoney());
            }

        }
        return example;

    }

}
