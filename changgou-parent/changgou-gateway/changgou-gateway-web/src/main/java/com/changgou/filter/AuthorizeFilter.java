package com.changgou.filter;

import com.changgou.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取request response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获取请求的URI
        String path = request.getURI().getPath();

        //如果请求的服务是登录或者商品的部分微服务直接放行 这里模拟实现 完整演示需要设计一套权限系统
        if (path.startsWith("/api/user/login") || path.startsWith("/api/brand/search")) {
            //放行
            Mono<Void> filter = chain.filter(exchange);
            return filter;
        }

        //获取用户令牌信息
        //1.优先获取头文件中的令牌信息
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //true代表令牌在头文件中 false代表令牌不在头文件中，需要将令牌封装到头文件中，再传递给其他微服务
        boolean hasToken = true;
        System.out.println("从request头文件中获取的token：" + token);
//        token = response.getHeaders().getFirst(AUTHORIZE_TOKEN);
//        System.out.println("从responseheaders获取的token：" + token);

        //2.如果头文件中没有，则从请求参数中获取
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken = false;
            System.out.println("从请求参数中获取的token：" + token);
        }
        //3.如果头文件和请求参数中都没有 则从cookie中获取令牌数据
        if (StringUtils.isEmpty(token)) {
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (cookie != null) {
                token = cookie.getValue();
                System.out.println("从cookie中获取的token" + token);
            }
        }
        //如果头文件、请求参数、cookie中都没有token 则输出代码错误
        if (StringUtils.isEmpty(token)){
            System.out.println(token);
            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            System.out.println("走到这一步了？");
            return response.setComplete();
        }

        //走到这一步说明从cookie中或者头文件中或者请求参数中 获取到了jwt令牌数据，这里就可以解析令牌数据
//        try {
////            Claims claims = JwtUtil.parseJWT(token);
////           request.mutate().header(AUTHORIZE_TOKEN,claims.toString());
//            //令牌数据添加到头文件中 少了一个空格 耽误了好几天 尴尬
//            request.mutate().header(AUTHORIZE_TOKEN,"Bearer "+token);
//            System.out.println("添加到头信息传递各个微服务了"+token);
//        } catch (Exception e) {
//            e.printStackTrace();
//            //解析失败，响应401错误
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
        if (StringUtils.isEmpty(token)) {
            //设置没有权限的状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            return response.setComplete();
        } else {
            if (!hasToken){
                //判断令牌有没有bearer前缀 没有的话就添加
                if(!token.startsWith("bearer ") && !token.startsWith("Bearer ")){
                    token ="bearer "+token;
                }
                //将令牌封装到头文件中
                request.mutate().header(AUTHORIZE_TOKEN,token);
                System.out.println("封装到请求头了");
            }
        }
        //有效放行
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }

}
