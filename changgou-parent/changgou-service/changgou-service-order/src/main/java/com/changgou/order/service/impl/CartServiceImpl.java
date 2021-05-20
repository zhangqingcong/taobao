package com.changgou.order.service.impl;

import com.changgou.entity.Result;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SpuFeign spuFeign;
    @Autowired
    private SkuFeign skuFeign;

    @Override
    public void add(Integer num, BigInteger id, String username) {
        //根据id查询sku
        Result<Sku> skuResult = skuFeign.findById(id);
        if (skuResult != null && skuResult.isFlag()) {
            //获取sku
            Sku sku = skuResult.getData();
            //获取spu
            Result<Spu> spuResult = spuFeign.findById(sku.getSpuId().toString());

            //讲SKU转换成OrderItem 数据格式不太一样 所以要转换
            OrderItem orderItem = sku2OrderItem(sku, spuResult.getData(), num);

            /**
             * 购物车数据存入redis
             * namespace = Cart_[username]
             * key=skuId
             * value=orderItem
             */
            redisTemplate.boundHashOps("Cart_"+username).put(id,orderItem);


        }
    }

    private OrderItem sku2OrderItem(Sku sku, Spu spu,Integer num){
        OrderItem orderItem = new OrderItem();
        orderItem.setSpuId(sku.getSpuId());
        orderItem.setSkuId(sku.getId());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);
        orderItem.setMoney(num*sku.getPrice()); //数量*单价
        orderItem.setPayMoney(num*sku.getPrice()); //实付金额
        orderItem.setImage(sku.getImage());
        orderItem.setWeight(sku.getWeight()*num);//总重量

        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        return orderItem;
    }
}
