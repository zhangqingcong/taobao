package com.changgou.order.service.impl;

import com.changgou.entity.Result;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SpuFeign spuFeign;
    @Autowired
    private SkuFeign skuFeign;

    private static final String CART = "Cart_";

    /**
     * 添加商品到redis中
     *
     * @param num      购买商品数量
     * @param skuId    购买id
     * @param username 购买用户
     */
    @Override
    public void add(BigInteger skuId, Integer num, String username) {
        /**
         *分析思路
         * 1.先从redis中查询购物车中是否存在这个商品
         * 2.如果redis中已经存在了 就追加数量 重新计算金额
         * 3.如果没有 将商品添加到缓存
         */
        //1.查询redis中对应的商品信息
        OrderItem orderItem = (OrderItem) redisTemplate.boundHashOps(CART + username).get(skuId);
        if (orderItem != null) {
            //orderItem不为空 说明存在 则增加数量 刷新购物车
            orderItem.setNum(orderItem.getNum()+num);
            if(orderItem.getNum()<=0){
                //删除该商品
                redisTemplate.boundHashOps(CART+username).delete(skuId);
                return;
            }
            orderItem.setMoney(orderItem.getNum()*orderItem.getPrice());
            orderItem.setPayMoney(orderItem.getNum()*orderItem.getPrice());
        }else {
            //说明redis中不存在该商品 就新增购物车
            Result<Sku> skuResult = skuFeign.findById(skuId);
            Sku sku = skuResult.getData();
            Result<Spu> spuResult = spuFeign.findById(sku.getSpuId().toString());
            Spu spu = spuResult.getData();
            //将SKU转换成orderitem
             orderItem = this.sku2OrderItem(sku, spu, num);
        }
        redisTemplate.boundHashOps(CART+username).put(skuId,orderItem);
    }

    /**
     * 查询购物车
     *
     * @param username
     * @return
     */
    @Override
    public Map list(String username) {
        Map map = new HashMap();
        List<OrderItem> orderItems = redisTemplate.boundHashOps("Cart_" + username).values();
        map.put("orderItemList", orderItems);
        //商品数量与价格
        Integer totalNum = 0;
        Integer totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalNum += orderItem.getNum();
            totalPrice += orderItem.getMoney();
        }
        map.put("totalNum", totalNum);
        map.put("totalPrice", totalPrice);
        return map;
    }

    //SKU转换成为orderItem
    private OrderItem sku2OrderItem(Sku sku, Spu spu, Integer num) {
        OrderItem orderItem = new OrderItem();
        orderItem.setSpuId(sku.getSpuId());
        orderItem.setSkuId(sku.getId());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);
        orderItem.setMoney(num * sku.getPrice()); //数量*单价
        orderItem.setPayMoney(num * sku.getPrice()); //实付金额
        orderItem.setImage(sku.getImage());
        orderItem.setWeight(sku.getWeight() * num);//总重量

        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        return orderItem;
    }
}
