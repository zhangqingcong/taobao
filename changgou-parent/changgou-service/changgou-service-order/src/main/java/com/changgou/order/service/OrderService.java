package com.changgou.order.service;

import com.changgou.order.pojo.Order;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(String id);
    void add(Order order);
    void delete(String id);
    void update(Order order);

    PageInfo<Order> findPage(Integer page, Integer size);
    List<Order> findList(Order order);
    PageInfo<Order> findPage(Order order,Integer page, Integer size);
}
