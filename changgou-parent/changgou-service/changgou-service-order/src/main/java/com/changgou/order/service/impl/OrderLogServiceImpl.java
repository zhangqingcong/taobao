package com.changgou.order.service.impl;

import com.changgou.order.dao.OrderLogMapper;
import com.changgou.order.pojo.OrderLog;
import com.changgou.order.service.OrderLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class OrderLogServiceImpl implements OrderLogService {
    @Autowired
    private OrderLogMapper orderLogMapper;
    @Override
    public List<OrderLog> findAll() {
        return orderLogMapper.selectAll();
    }

    @Override
    public OrderLog findById(String id) {
        return orderLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(OrderLog orderLog) {
        orderLogMapper.insertSelective(orderLog);
    }

    @Override
    public void delete(String id) {
        orderLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderLog orderLog) {
        orderLogMapper.updateByPrimaryKeySelective(orderLog);
    }

    @Override
    public List<OrderLog> findList(OrderLog orderLog) {
        Example example = createExample(orderLog);
        return orderLogMapper.selectByExample(example);
    }

    @Override
    public PageInfo<OrderLog> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (PageInfo<OrderLog>) orderLogMapper.selectAll();
    }

    @Override
    public PageInfo<OrderLog> findPage(OrderLog orderLog, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(orderLog);
        return (PageInfo<OrderLog>) orderLogMapper.selectByExample(example);
    }

    private Example createExample(OrderLog orderLog){
        Example example = new Example(OrderLog.class);
        Example.Criteria criteria = example.createCriteria();
        if (orderLog!=null){
            if (StringUtils.isEmpty(orderLog.getId())){
                criteria.andEqualTo("id",orderLog.getId());
            }
            if (StringUtils.isEmpty(orderLog.getOpereter())){
                criteria.andEqualTo("operater",orderLog.getOpereter());
            }
            if (StringUtils.isEmpty(orderLog.getOperaterTime())){
                criteria.andEqualTo("operaterTime",orderLog.getOperaterTime());
            }
            if (StringUtils.isEmpty(orderLog.getOrderId())){
                criteria.andEqualTo("orderId",orderLog.getOrderId());
            }
            if (StringUtils.isEmpty(orderLog.getOrderStatus())){
                criteria.andEqualTo("orderStatus",orderLog.getOrderStatus());
            }
            if (StringUtils.isEmpty(orderLog.getPayStatus())){
                criteria.andEqualTo("payStatus",orderLog.getPayStatus());
            }
            if (StringUtils.isEmpty(orderLog.getConsignStatus())){
                criteria.andEqualTo("consignStatus",orderLog.getConsignStatus());
            }
            if (StringUtils.isEmpty(orderLog.getRemarks())){
                criteria.andLike("remarks","%"+orderLog.getRemarks()+"%");
            }
            if (StringUtils.isEmpty(orderLog.getMoney())){
                criteria.andEqualTo("money",orderLog.getMoney());
            }
            if (StringUtils.isEmpty(orderLog.getUsername())){
                criteria.andLike("username","%"+orderLog.getUsername()+"%");
            }
        }
        return example;
    }
}
