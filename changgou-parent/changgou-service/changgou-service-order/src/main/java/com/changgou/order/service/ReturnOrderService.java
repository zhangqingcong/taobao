package com.changgou.order.service;

import com.changgou.order.pojo.ReturnOrder;
import com.github.pagehelper.PageInfo;

import java.math.BigInteger;
import java.util.List;

public interface ReturnOrderService {
    List<ReturnOrder> findAll();
    ReturnOrder findById(BigInteger id);
    void add(ReturnOrder returnOrder);
    void delete(BigInteger id);
    void update(ReturnOrder returnOrder);
    List<ReturnOrder> findList(ReturnOrder returnOrder);
    PageInfo<ReturnOrder> findPage(Integer page,Integer size);
    PageInfo<ReturnOrder> findPage(ReturnOrder returnOrder,Integer page,Integer size);
}
