package com.itheima.controller;

import com.itheima.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String hello(Model model){
//        model.addAttribute("hello", "hello thymeleaf");
        List<User> users = new ArrayList<>();
        users.add(new User(1,"tom","LS"));
        users.add(new User(2,"jery","DC"));
        users.add(new User(3,"mike","UC"));
        model.addAttribute("users",users);

        Map<String,Object> dataMap= new HashMap<>();
        dataMap.put("No","123");
        dataMap.put("address","深圳");
        model.addAttribute("dataMap",dataMap);

        String[] names={"张三","lisi","wangwu"};
        model.addAttribute("names",names);
        model.addAttribute("now",new Date());

        model.addAttribute("age",17);

        model.addAttribute("hello","hello 崇恩");
        model.addAttribute("class1","aaa");
        model.addAttribute("class2","bbb");
        return "demo01";
    }
}
