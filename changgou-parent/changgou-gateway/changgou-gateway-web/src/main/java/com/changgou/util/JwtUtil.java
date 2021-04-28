package com.changgou.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    //有效期为
    public static final Long JWT_TTL = 3600000l;

    public static final String JWT_KEY = "itheima";

    public static String creatJWT(String id, String subject, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //获取系统当前时间
        long nowMillis = System.currentTimeMillis();
        //令牌签发时间
        Date date = new Date(nowMillis);
        //如果令牌有效期为null，则默认设置有效期1小时
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        Long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = generalKey();

        //封装jwt令牌信息
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(date)
                .signWith(signatureAlgorithm,secretKey)
                .setExpiration(expDate);

        return jwtBuilder.compact();
    }

    /**
     * 生成加密 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encode = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKeySpec key = new SecretKeySpec(encode, 0, encode.length, "AES");
        return key;
    }
    /**
     * 解析令牌数据
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generalKey();
       return Jwts.parser()
               .setSigningKey(secretKey)
               .parseClaimsJws(jwt)
               .getBody();
    }
}
