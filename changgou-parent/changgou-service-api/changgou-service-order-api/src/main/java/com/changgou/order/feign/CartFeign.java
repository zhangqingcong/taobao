package com.changgou.order.feign;

import com.changgou.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.Map;

@FeignClient(name = "order")
@RequestMapping("/cart")
public interface CartFeign {

    @PostMapping(value = "/add")
     Result add(@RequestParam(name = "skuId") BigInteger skuId,@RequestParam(name = "num") Integer num);

    @GetMapping("/list")
    Map list();

}
