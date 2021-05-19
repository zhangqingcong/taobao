package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAll();
    OrderItem findById(String id);
    void add(OrderItem orderItem);
    void delete(String id);
    void update(OrderItem orderItem);
    PageInfo<OrderItem> findPage(Integer page,Integer size);
    List<OrderItem> findList(OrderItem orderItem);
    PageInfo<OrderItem> findPage(OrderItem orderItem,Integer page,Integer size);
}
