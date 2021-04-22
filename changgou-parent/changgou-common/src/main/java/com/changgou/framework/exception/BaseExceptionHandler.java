package com.changgou.framework.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import com.changgou.entity.*;
@ControllerAdvice
public class BaseExceptionHandler {
    public Result BaseExceptionHandler(Exception e){
        e.printStackTrace();
        return new Result(false,StatusCode.ERROR,e.getMessage());
    }
}
