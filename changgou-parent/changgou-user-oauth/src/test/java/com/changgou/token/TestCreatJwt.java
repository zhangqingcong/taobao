package com.changgou.token;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.lang.management.PlatformLoggingMXBean;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class TestCreatJwt {

    @Test
    public void test(){
        ClassPathResource resource = new ClassPathResource("changgou.jks");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource,"changgou".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("changgou", "changgou".toCharArray());
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();

        Map<String,Object> payload = new HashMap<>();
        payload.put("nickname","tom");
        payload.put("address","sz");
        payload.put("role","admin,user");

        Jwt jwt = JwtHelper.encode(JSON.toJSONString(payload), new RsaSigner(aPrivate));
        String token = jwt.getEncoded();
        System.out.println(token);

    }

    @Test
     public void testParse(){
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZGRyZXNzIjoic3oiLCJyb2xlIjoiYWRtaW4sdXNlciIsIm5pY2tuYW1lIjoidG9tIn0.JsfYGYSFp-H4DBBwH2qytct_UDOK_nTE6-WTThxJUQMXhM8pBv-4BET1EZ3IlH7tIssA3O1nFMB8ERM9M2AsKMwIcUhFwBWzXMewt8K59oaGrCC2F9L8zU6tUVpPmnZHGnLrhXmm5m-IeXVwa4uDhn2NMNN0N3aTXwlMzhDzz30gnNe1yXn6nSO5PrcNmdrKDeWLfhtpmlFQcjOqDZroc9F6F9Mb6UrQ4oeK2bUjQkkVSVevN0NyrBpYUngqdLdVcVAN94bE9lfqx74EIXWBDkpY7i46eUw2oH5Dc-fZFSIT_RRwYNdEHYshDPGuOZRozMExUQlKM-n-fy_Oqa_VJg";
        String publickey="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuKGJYfyHDh40xwYtHvznwCLsoPPeodfMydUykLKb9MPkjc1/u91bRrUAssgcSJ4bOtAD+DS4Ky8dgJfsbbkWKQMuU8adVum+pNEM5Ej59T42A8AEovH78IOQFdsyf0PK7XgXNlY3tyHbzwhqayc1it7higcIy5Xl7xF1GVOC04KhzQuk0TfUB2fvB6qiamQF43cbHsRUMzbpaHyeEKpnSmGaYGrgJtZeSpu4HYcZ8DZi6sUbZ5nNQ/OSTbxaCyDOkvxO4YDpm3ka8RMNOH2lfw+SY+1nH3ZPuARhak62qH3TCFu5rK3hA9MpnQWTwzwOQwSHtSbOFAiKKCksJd1WxQIDAQAB-----END PUBLIC KEY-----";
         Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));
         String claims = jwt.getClaims();
         System.out.println(claims);

     }

     public void testDecodeBase64(){

     }
}
