package com.changgou.user.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.BCrypt;
import com.changgou.entity.JwtUtil;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.user.pojo.User;
import com.changgou.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.util.*;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public Result login(String username, String password, HttpServletRequest request,HttpServletResponse response) {
        //查询用户信息
        User user = userService.findByUserName(username);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            //设置令牌信息
            Map<String, Object> info = new HashMap<>();
            info.put("role", "USER");
            info.put("success", "SUCCESS");
            info.put("username", username);
            //生成令牌
            String jwt = JwtUtil.creatJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
            //设置到头文件中去
            response.setHeader("Authorization",jwt);
//            response.addHeader("Authorization",jwt);
            //把生成的令牌添加到cookie中去 会话保持技术
            Cookie cookie = new Cookie("Authorization", jwt);
            cookie.setDomain("127.0.0.1");
            cookie.setPath("/");
            response.addCookie(cookie);
            return new Result(true, StatusCode.OK, "登录成功", jwt);
        }
        return new Result(false, StatusCode.LOGINERROR, "密码账户不匹配");
    }

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) User user, @PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页条件查询User
        PageInfo<User> pageInfo = userService.findPage(user, page, size);
        return new Result(true, StatusCode.OK, "查询成功1", pageInfo);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页查询User
        PageInfo<User> pageInfo = userService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功2", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<User>> findList(@RequestBody(required = false) User user) {
        //调用UserService实现条件查询User
        List<User> list = userService.findList(user);
        return new Result<List<User>>(true, StatusCode.OK, "查询成功3", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        //调用UserService实现根据主键删除
        userService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody User user, @PathVariable String id) {
        //设置主键值
        user.setUsername(id);
        //调用UserService实现修改User
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @PostMapping
    public Result add(@RequestBody User user) {
        //调用UserService实现添加User
        userService.add(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @GetMapping({"/{id}","/load/{id}"})
    public Result<User> findById(@PathVariable String id) {
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return new Result<User>(true, StatusCode.OK, "查询成功4", user);
    }

    /***
     * 查询User全部数据
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin','vip')")
    @GetMapping
    public Result<List<User>> findAll(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        System.out.println("令牌信息" + authorization);
        //调用UserService实现查询所有User
        List<User> list = userService.findAll();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功5", list);
    }
}
