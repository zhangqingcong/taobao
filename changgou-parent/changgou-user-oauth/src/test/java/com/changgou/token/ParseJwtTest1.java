package com.changgou.token;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest1 {

    /***
     * 校验令牌
     */
    @Test
    public void testParseToken() {
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJpdGhlaW1hIiwiaWQiOiIxIn0.dMzouwYilrZu3jKf1KKiu6A2H_5PzCChUIDXDLRIendUF6pTgX8Vc5RJ41ZBIMSr1Cgcc4emUobe5BPAuF9BD_yF5eYsInYAS1bX9RD5-wdU8Pa1cuiUtKqwgN9ZZAakhsJswx89pNhkj27ParQb-R6NlSi-rbb4N1KcfxdJVe7fuyEMhEaAN5Igllcf_o2xisbq29a4By-z8fYZawrljuCbcm2m6obNI0zRKOLQxxMujbli0X1XoW2t-BMXt2obzkuNXU38HYSRkxlbQq8Frjzc4P-rpBGtwuhnBpfhA4ef6At5_tgF5oVAlDb0kh6WZ4ExEa1SBMc-DWZiqBrJxw";
        //公钥
        String publickey ="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuKGJYfyHDh40xwYtHvznwCLsoPPeodfMydUykLKb9MPkjc1/u91bRrUAssgcSJ4bOtAD+DS4Ky8dgJfsbbkWKQMuU8adVum+pNEM5Ej59T42A8AEovH78IOQFdsyf0PK7XgXNlY3tyHbzwhqayc1it7higcIy5Xl7xF1GVOC04KhzQuk0TfUB2fvB6qiamQF43cbHsRUMzbpaHyeEKpnSmGaYGrgJtZeSpu4HYcZ8DZi6sUbZ5nNQ/OSTbxaCyDOkvxO4YDpm3ka8RMNOH2lfw+SY+1nH3ZPuARhak62qH3TCFu5rK3hA9MpnQWTwzwOQwSHtSbOFAiKKCksJd1WxQIDAQAB-----END PUBLIC KEY-----";
        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
