package com.changgou.order.service.impl;

import com.changgou.order.dao.PreferentialMapper;
import com.changgou.order.pojo.Preferential;
import com.changgou.order.service.PreferentialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PreferentialServiceImpl implements PreferentialService {
    @Autowired
    private PreferentialMapper preferentialMapper;

    @Override
    public List<Preferential> findAll() {
        return preferentialMapper.selectAll();
    }

    @Override
    public Preferential findById(Integer id) {
        return preferentialMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Preferential preferential) {
        preferentialMapper.insertSelective(preferential);
    }

    @Override
    public void delete(Integer id) {
        preferentialMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Preferential preferential) {
        preferentialMapper.updateByPrimaryKeySelective(preferential);
    }

    @Override
    public List<Preferential> findList(Preferential preferential) {
        Example example = createExample(preferential);
        return preferentialMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Preferential> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (PageInfo<Preferential>) preferentialMapper.selectAll();
    }

    @Override
    public PageInfo<Preferential> findPage(Preferential preferential, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(preferential);
        return (PageInfo<Preferential>) preferentialMapper.selectByExample(example);
    }

    private Example createExample(Preferential preferential) {
        Example example = new Example(Preferential.class);
        Example.Criteria criteria = example.createCriteria();
        if (preferential != null) {
            if (!StringUtils.isEmpty(preferential.getId())) {
                criteria.andEqualTo("id", preferential.getId());
            }
            if (!StringUtils.isEmpty(preferential.getBuyMoney())) {
                criteria.andEqualTo("buyMoney", preferential.getBuyMoney());
            }
            if (!StringUtils.isEmpty(preferential.getPreMoney())) {
                criteria.andEqualTo("preMoney", preferential.getPreMoney());
            }
            if (!StringUtils.isEmpty(preferential.getCategoryId())) {
                criteria.andEqualTo("categoryId", preferential.getCategoryId());
            }
            if (!StringUtils.isEmpty(preferential.getStartTime())) {
                criteria.andGreaterThanOrEqualTo("startTime", preferential.getStartTime());
            }
            if (!StringUtils.isEmpty(preferential.getEndTime())) {
                criteria.andLessThanOrEqualTo("endTime", preferential.getEndTime());
            }
            if (!StringUtils.isEmpty(preferential.getState())) {
                criteria.andEqualTo("state", preferential.getState());
            }
            if (!StringUtils.isEmpty(preferential.getType())) {
                criteria.andEqualTo("type", preferential.getType());
            }
        }
        return example;
    }
}
