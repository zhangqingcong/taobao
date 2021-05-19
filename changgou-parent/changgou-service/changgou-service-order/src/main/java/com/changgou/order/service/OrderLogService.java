package com.changgou.order.service;

import com.changgou.order.pojo.OrderLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderLogService {
    List<OrderLog> findAll();
    OrderLog findById(String id);
    void add(OrderLog orderLog);
    void delete(String id);
    void update(OrderLog orderLog);
    List<OrderLog> findList(OrderLog orderLog);
    PageInfo<OrderLog> findPage(Integer page,Integer size);
    PageInfo<OrderLog> findPage(OrderLog orderLog,Integer page,Integer size);

}
