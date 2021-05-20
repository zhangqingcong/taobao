package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.soap.Addressing;
import java.math.BigInteger;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/add")
    public Result add(Integer num, BigInteger id) {
        //用户名
        String username = "szitheima";
        //将商品加入购物车
        cartService.add(num,id,username);
        return new Result(true, StatusCode.OK,"加入购物车成功");
    }
}
