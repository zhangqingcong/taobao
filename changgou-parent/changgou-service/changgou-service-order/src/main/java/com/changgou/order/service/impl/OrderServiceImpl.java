package com.changgou.order.service.impl;

import com.changgou.order.dao.OrderMapper;
import com.changgou.order.pojo.Order;
import com.changgou.order.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }

    @Override
    public Order findById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Order order) {
        orderMapper.insertSelective(order);
    }

    @Override
    public void delete(String id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public PageInfo<Order> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        PageInfo<Order> orderList = (PageInfo<Order>) orderMapper.selectAll();
        return orderList;
    }

    @Override
    public List<Order> findList(Order order) {
        Example example = createExample(order);
        List<Order> orderList = orderMapper.selectByExample(example);
        return orderList;
    }

    @Override
    public PageInfo<Order> findPage(Order order, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(order);
        PageInfo<Order> orderList = (PageInfo<Order>) orderMapper.selectByExample(example);
        return orderList;
    }

    private Example createExample(Order order){
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if (order!=null){
            if (!StringUtils.isEmpty(order.getId())){
                criteria.andEqualTo("id",order.getId());
            }
            if (!StringUtils.isEmpty(order.getTotalNum())){
                criteria.andEqualTo("num",order.getTotalNum());
            }
            if (!StringUtils.isEmpty(order.getTotalMoney())){
                criteria.andEqualTo("totalMoney",order.getTotalMoney());
            }
            if (!StringUtils.isEmpty(order.getPreMoney())){
                criteria.andEqualTo("preMoney",order.getPreMoney());
            }
            if (!StringUtils.isEmpty(order.getPostFee())){
                criteria.andEqualTo("postFee",order.getPostFee());
            }
            if (!StringUtils.isEmpty(order.getPayMoney())){
                criteria.andEqualTo("payMoney",order.getPayMoney());
            }
            if (!StringUtils.isEmpty(order.getPayType())){
                criteria.andEqualTo("payType",order.getPayType());
            }
            if (!StringUtils.isEmpty(order.getCreateTime())){
                criteria.andGreaterThanOrEqualTo("createTime",order.getCreateTime());
            }
            if (!StringUtils.isEmpty(order.getUpdateTime())){
                criteria.andGreaterThanOrEqualTo("updateTime",order.getUpdateTime());
            }
            if (!StringUtils.isEmpty(order.getPayTime())){
                criteria.andGreaterThanOrEqualTo("payTime",order.getPayTime());
            }
            if (!StringUtils.isEmpty(order.getConsignTime())){
                criteria.andGreaterThanOrEqualTo("consignTime",order.getConsignTime());
            }
            if (!StringUtils.isEmpty(order.getEndTime())){
                criteria.andGreaterThanOrEqualTo("endTime",order.getEndTime());
            }
            if (!StringUtils.isEmpty(order.getCloseTime())){
                criteria.andGreaterThanOrEqualTo("closeTime",order.getCloseTime());
            }
            if (!StringUtils.isEmpty(order.getShippingName())){
                criteria.andEqualTo("shippingName",order.getShippingName());
            }
            if (!StringUtils.isEmpty(order.getShippingCode())){
                criteria.andEqualTo("shippingCode",order.getShippingCode());
            }
            if (!StringUtils.isEmpty(order.getUsername())){
                criteria.andLike("username","%"+order.getUsername()+"%");
            }
            if (!StringUtils.isEmpty(order.getBuyerMessage())){
                criteria.andLike("buyerMessage","%"+order.getBuyerMessage()+"%");
            }
            if (!StringUtils.isEmpty(order.getBuyerRate())){
                criteria.andEqualTo("buyerRate",order.getBuyerRate());
            }
            if (!StringUtils.isEmpty(order.getReceiverContact())){
                criteria.andEqualTo("receiverContact",order.getReceiverContact());
            }
            if (!StringUtils.isEmpty(order.getReceiverMobile())){
                criteria.andEqualTo("receiverMobile",order.getReceiverMobile());
            }
            if (!StringUtils.isEmpty(order.getReceiverAddress())){
                criteria.andEqualTo("receiverAddress",order.getReceiverAddress());
            }
            if (!StringUtils.isEmpty(order.getSourceType())){
                criteria.andLike("sourceType","%"+order.getSourceType()+"%");
            }
            if (!StringUtils.isEmpty(order.getTransactionId())){
                criteria.andLike("transactionId",order.getTransactionId());
            }
            if (!StringUtils.isEmpty(order.getOrderStatus())){
                criteria.andEqualTo("orderStatus",order.getOrderStatus());
            }
            if (!StringUtils.isEmpty(order.getPayStatus())){
                criteria.andEqualTo("payStatus",order.getPayStatus());
            }
            if (!StringUtils.isEmpty(order.getConsignStatus())){
                criteria.andEqualTo("consignStatus",order.getConsignStatus());
            }
            if (!StringUtils.isEmpty(order.getIsDelete())){
                criteria.andEqualTo("isDelete",order.getIsDelete());
            }
        }
        return example;
    }
}
