package com.changgou.order.service.impl;

import com.changgou.order.dao.ReturnOrderItemMapper;
import com.changgou.order.dao.ReturnOrderMapper;
import com.changgou.order.pojo.ReturnOrderItem;
import com.changgou.order.service.ReturnOrderItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigInteger;
import java.util.List;

public class ReturnOrderItemServiceImpl implements ReturnOrderItemService {
    @Autowired
    private ReturnOrderItemMapper returnOrderItemMapper;
    @Override
    public List<ReturnOrderItem> findAll() {
        return returnOrderItemMapper.selectAll();
    }

    @Override
    public ReturnOrderItem findById(BigInteger id) {
        return returnOrderItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(ReturnOrderItem returnOrderItem) {
        returnOrderItemMapper.insertSelective(returnOrderItem);
    }

    @Override
    public void delete(BigInteger id) {
        returnOrderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ReturnOrderItem returnOrderItem) {
        returnOrderItemMapper.updateByPrimaryKeySelective(returnOrderItem);
    }

    @Override
    public List<ReturnOrderItem> findList(ReturnOrderItem returnOrderItem) {
        Example example = createExample(returnOrderItem);
        return returnOrderItemMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ReturnOrderItem> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (PageInfo<ReturnOrderItem>) returnOrderItemMapper.selectAll();
    }

    @Override
    public PageInfo<ReturnOrderItem> findPage(ReturnOrderItem returnOrderItem, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(returnOrderItem);
        return (PageInfo<ReturnOrderItem>) returnOrderItemMapper.selectAll();
    }

    private Example createExample(ReturnOrderItem returnOrderItem){
        Example example = new Example(ReturnOrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if (returnOrderItem!=null){
            if (StringUtils.isEmpty(returnOrderItem.getId())){
                criteria.andEqualTo("id",returnOrderItem.getId());
            }
            if (StringUtils.isEmpty(returnOrderItem.getCategoryId())){
                criteria.andEqualTo("categoryId",returnOrderItem.getCategoryId());
            }
            if (StringUtils.isEmpty(returnOrderItem.getSpuId())){
                criteria.andEqualTo("spuId",returnOrderItem.getSpuId());
            }
            if (StringUtils.isEmpty(returnOrderItem.getSkuId())){
                criteria.andEqualTo("skuId",returnOrderItem.getSkuId());
            }
            if (StringUtils.isEmpty(returnOrderItem.getOrderId())){
                criteria.andEqualTo("orderId",returnOrderItem.getOrderId());
            }
            if (StringUtils.isEmpty(returnOrderItem.getOrderItemId())){
                criteria.andEqualTo("orderItemId",returnOrderItem.getOrderItemId());
            }
            if (StringUtils.isEmpty(returnOrderItem.getReturnOrderId())){
                criteria.andEqualTo("returnOrderId",returnOrderItem.getReturnOrderId());
            }
            if (StringUtils.isEmpty(returnOrderItem.getTitle())){
                criteria.andLike("title","%"+returnOrderItem.getTitle()+"%");
            }
            if (StringUtils.isEmpty(returnOrderItem.getPrice())){
                criteria.andEqualTo("price",returnOrderItem.getPrice());
            }
            if (StringUtils.isEmpty(returnOrderItem.getNum())){
                criteria.andEqualTo("num",returnOrderItem.getNum());
            }
            if (StringUtils.isEmpty(returnOrderItem.getMoney())){
                criteria.andEqualTo("money",returnOrderItem.getMoney());
            }
            if (StringUtils.isEmpty(returnOrderItem.getPayMoney())){
                criteria.andEqualTo("payMoney",returnOrderItem.getPayMoney());
            }
            if (StringUtils.isEmpty(returnOrderItem.getImage())){
                criteria.andEqualTo("image",returnOrderItem.getImage());
            }
            if (StringUtils.isEmpty(returnOrderItem.getWeight())){
                criteria.andEqualTo("weight",returnOrderItem.getWeight());
            }
        }
        return example;
    }

}
