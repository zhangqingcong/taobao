package com.changgou.order.service;

import com.changgou.order.pojo.OrderItem;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface CartService {
    /**
     * 添加购物车
     * @param num 购买商品数量
     * @param skuId 购买id
     * @param username 购买用户
     */
    void add (BigInteger skuId,Integer num, String username);

    /**
     * 查询用户的购物车数据
     *
     * @return
     */
    Map list(String username);
}
