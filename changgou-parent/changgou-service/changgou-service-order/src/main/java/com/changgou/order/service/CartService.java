package com.changgou.order.service;

import java.math.BigInteger;

public interface CartService {
    /**
     * 添加购物车
     * @param num 购买商品数量
     * @param id 购买id
     * @param username 购买用户
     */
    void add(Integer num, BigInteger id, String username);
}
