package com.changgou.order.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.feign.CartFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.Map;

@Controller
@RequestMapping("/wcart")
public class CartController {
    @Autowired
    private CartFeign cartFeign;

    @GetMapping("/add")
    @ResponseBody
    public Result<Map> add(@RequestParam(value = "skuId") BigInteger skuId, @RequestParam(value = "num") Integer num){
        cartFeign.add(skuId,num);
        Map map = cartFeign.list();
        return new Result(true, StatusCode.OK,"添加购物车成功",map);
    }

    /**
     * 查询购物车
     * @param model
     * @return
     */
    @GetMapping (value = "/list")
    public String list(Model model){
        Map map = cartFeign.list();
        model.addAttribute("items",map);
        return "cart";
    }
}
