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
        //1.优先获取头文件中的令牌信息
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        System.out.println("从头文件中获取的toke"+token);
        //2.如果头文件中没有，则从请求参数中获取
        if (StringUtils.isEmpty(token)){
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            System.out.println("从请求参数中获取的token"+token);
        }
        //3.如果头文件和请求参数中都没有 则从cookie中获取令牌数据
        HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
        if (cookie!=null){
            token = cookie.getValue();
            System.out.println("从cookie中获取的token"+token);

        }

        //如果头文件合请求参数 cookie中都没有token 则输出代码错误
        if (StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            return response.setComplete();
        }

        //走到这一步说明从cookie中或者头文件中获取到了jwt令牌数据，这里就可以解析令牌数据
       try {
           Claims claims = JwtUtil.parseJWT(token);
//           request.mutate().header(AUTHORIZE_TOKEN,claims.toString());
       }catch (Exception e){
           e.printStackTrace();
           //解析失败，响应401错误
           response.setStatusCode(HttpStatus.UNAUTHORIZED);
           return response.setComplete();
       }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }

}
