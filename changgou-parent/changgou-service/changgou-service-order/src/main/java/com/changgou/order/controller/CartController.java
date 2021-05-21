package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.config.TokenDecode;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenDecode tokenDecode;

    @PostMapping(value = "/add")
    public Result add(@RequestParam("skuId") BigInteger skuId, @RequestParam("num") Integer num) {
        //用户名
        String username = "szitheima";
//        String username = tokenDecode.getUserInfo().get("username");
        //将商品加入购物车
        cartService.add(skuId, num, username);
        return new Result(true, StatusCode.OK, "加入购物车成功");
    }

    @GetMapping("/list")
    public Map list() {
        String username = "szitheima";
//        String username = tokenDecode.getUserInfo().get("username");
        Map map = cartService.list(username);
        return map;
    }
}
