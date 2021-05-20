package com.changgou.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
@Configuration
@EnableResourceServer //开启资源服务器 标识这是一个oauth2中的资源服务器
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)//激活方法上的preAuthorize注解
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    //公钥
    private static final String PUBLIC_KEY = "public.key";

    //从类路径下获取非对称加密公钥
    private String getPublicKey() {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 定义JwtAccessTokenConverter
     * jwt的转换器 需要传入公钥作为参数 作用是校验令牌
     * @return 返回值交给IoC容器管理
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPublicKey());
        return converter;
    }

    /**
     * 定义JwtTokenStore
     * @param jwtAccessTokenConverter 参数需要jwt转换器
     * @return 交给JwtTokenStore管理 返回值交给IoC容器管理
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * http安全配置 对每个到达系统的http请求连接进行校验
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        //所有请求必须认证通过
        httpSecurity.authorizeRequests()
                //放行路径 参数里面配置放行地址
                .antMatchers("/cart/add")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
