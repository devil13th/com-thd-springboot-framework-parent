package com.thd.springboot.framework.web.controller;

import com.thd.springboot.framework.MyException;
import com.thd.springboot.framework.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * com.thd.springboot.framework.web.controller.ExceptionHandlerController
 *
 * @author: wanglei62
 * @DATE: 2020/9/15 15:42
 **/
@RestControllerAdvice
@Order(999) // 如果定义了Exception的子类处理器,则那个处理器设置的@Order要比999小才可以
public class ExceptionHandlerController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public Message handleException(Exception e){
        logger.error(e.getMessage(), e);
        return Message.error(e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    public Message handleMyException(MyException e){
        logger.error(e.getMessage(), e);
        return Message.error(e.getCode(),e.getMsg());
    }


}
