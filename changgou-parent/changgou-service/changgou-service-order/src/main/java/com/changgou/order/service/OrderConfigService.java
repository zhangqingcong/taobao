package com.changgou.order.service;

import com.changgou.order.pojo.OrderConfig;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderConfigService {
    List<OrderConfig> findAll();
    OrderConfig findById(Integer id);
    void add(OrderConfig orderConfig);
    void delete(Integer id);
    void update(OrderConfig orderConfig);
    PageInfo<OrderConfig> findPage(Integer page,Integer size);
    List<OrderConfig> findList(OrderConfig orderConfig);
    PageInfo<OrderConfig> findPage(OrderConfig orderConfig,Integer page,Integer size);
}
