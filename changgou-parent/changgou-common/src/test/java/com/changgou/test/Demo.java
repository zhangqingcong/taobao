package com.changgou.test;
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
        long currentTimeMillis = System.currentTimeMillis();
        long l = currentTimeMillis + 20000;
        //生成令牌
        //创建jwtbuilder
        JwtBuilder builder = Jwts.builder();
        //1.头(不设置,默认) 2 载荷(数据) 3. 签名
        builder.setId("唯一的标识")
                .setIssuer("颁发者")//生成令牌的一方
                .setSubject("主题")//就是描述 jwt的信息
                .setExpiration(new Date(l))//设置过期时间
                .signWith(SignatureAlgorithm.HS256,"itcast");//设置签名



        //3.可以自定义载荷
        Map<String, Object> map = new HashMap<>();
        map.put("myaddress","cn");
        map.put("mycity","sz");
        builder.addClaims(map);


        //生成令牌
        String compact = builder.compact();
        System.out.println(compact);

    }

}
