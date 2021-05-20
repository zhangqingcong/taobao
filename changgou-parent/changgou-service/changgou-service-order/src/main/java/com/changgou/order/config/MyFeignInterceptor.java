package com.changgou.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
@Component
public class MyFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes!=null){
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames!=null){
                while (headerNames.hasMoreElements()){
                    String name = headerNames.nextElement();
                    String value = request.getHeader(name);
                    System.out.println("name:"+name+"::::::value:"+value);
                    //3.用restTemplate将头信息传递给feign
                    requestTemplate.header(name,value);
                }
            }
        }
    }
}
