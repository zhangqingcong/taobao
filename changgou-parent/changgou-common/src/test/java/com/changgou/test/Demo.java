package com.changgou.test;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    @Test
    public void createJwt(){
        JwtBuilder builder = Jwts.builder()
                .setId("168") //设置唯一的编号
                .setSubject("测试") //设置主题 可以是JSON数据
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"changgou");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        Map<String,Object> userInfo =new  HashMap<String,Object>();
        userInfo.put("name","tom");
        userInfo.put("age",29);
        userInfo.put("address","北京");
        builder.addClaims(userInfo);
        //构建 并返回一个字符串
        System.out.println(builder.compact());
    }

    @Test
    public void testPareJwt(){
        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNjgiLCJzdWIiOiLmtYvor5UiLCJpYXQiOjE2MTk0Mzg2ODMsImFkZHJlc3MiOiLljJfkuqwiLCJuYW1lIjoidG9tIiwiYWdlIjoyOX0.NN3r5GygAH8ORT_WwCP6h6E-ocFNZ-51xH19GzPZXg0";
        Claims claims = Jwts.parser().setSigningKey("changgou").parseClaimsJws(jwt).getBody();
        System.out.println(claims);
    }

}
