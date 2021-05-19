package com.changgou.order.service;

import com.changgou.order.pojo.ReturnOrderItem;
import com.github.pagehelper.PageInfo;

import java.math.BigInteger;
import java.util.List;

public interface ReturnOrderItemService {
    List<ReturnOrderItem> findAll();
    ReturnOrderItem findById(BigInteger id);
    void add(ReturnOrderItem returnOrderItem);
    void delete(BigInteger id);
    void update(ReturnOrderItem returnOrderItem);
    List<ReturnOrderItem> findList(ReturnOrderItem returnOrderItem);
    PageInfo<ReturnOrderItem> findPage(Integer page,Integer size);
    PageInfo<ReturnOrderItem> findPage(ReturnOrderItem returnOrderItem,Integer page,Integer size);
}
