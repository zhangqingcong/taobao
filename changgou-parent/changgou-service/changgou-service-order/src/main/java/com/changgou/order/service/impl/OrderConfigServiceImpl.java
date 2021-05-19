package com.changgou.order.service.impl;

import com.changgou.order.dao.OrderConfigMapper;
import com.changgou.order.pojo.OrderConfig;
import com.changgou.order.service.OrderConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderConfigServiceImpl implements OrderConfigService {
    @Autowired
    private OrderConfigMapper orderConfigMapper;

    @Override
    public List<OrderConfig> findAll() {
        return orderConfigMapper.selectAll();
    }

    @Override
    public OrderConfig findById(Integer id) {
        return orderConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(OrderConfig orderConfig) {
        orderConfigMapper.insertSelective(orderConfig);
    }

    @Override
    public void delete(Integer id) {
        orderConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderConfig orderConfig) {
        orderConfigMapper.updateByPrimaryKeySelective(orderConfig);
    }

    @Override
    public PageInfo<OrderConfig> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (PageInfo<OrderConfig>) orderConfigMapper.selectAll();
    }

    @Override
    public List<OrderConfig> findList(OrderConfig orderConfig) {
        Example example = createExample(orderConfig);
        return orderConfigMapper.selectByExample(example);
    }

    @Override
    public PageInfo<OrderConfig> findPage(OrderConfig orderConfig, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(orderConfig);
        return (PageInfo<OrderConfig>) orderConfigMapper.selectByExample(example);
    }

    private Example createExample(OrderConfig orderConfig){
        Example example = new Example(OrderConfig.class);
        Example.Criteria criteria = example.createCriteria();
        if (orderConfig!=null){
            if (!StringUtils.isEmpty(orderConfig.getId())){
                criteria.andEqualTo("id",orderConfig.getId());
            }
            if (!StringUtils.isEmpty(orderConfig.getOrderTimeout())){
                criteria.andGreaterThanOrEqualTo("orderTimeout",orderConfig.getOrderTimeout());
            }
            if (!StringUtils.isEmpty(orderConfig.getSeckillTimeout())){
                criteria.andGreaterThanOrEqualTo("seckillTimeout",orderConfig.getSeckillTimeout());
            }
            if (!StringUtils.isEmpty(orderConfig.getTakeTimeout())){
                criteria.andGreaterThanOrEqualTo("takeTimeout",orderConfig.getOrderTimeout());
            }
            if (!StringUtils.isEmpty(orderConfig.getServiceTimeout())){
                criteria.andGreaterThanOrEqualTo("serviceTimeout",orderConfig.getServiceTimeout());
            }
            if (!StringUtils.isEmpty(orderConfig.getCommentTimeout())){
                criteria.andGreaterThanOrEqualTo("commentTimeout",orderConfig.getCommentTimeout());
            }
        }
        return example;
    }
}
