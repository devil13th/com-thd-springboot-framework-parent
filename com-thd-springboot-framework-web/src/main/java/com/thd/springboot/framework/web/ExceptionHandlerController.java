package com.thd.springboot.framework.web;

import com.thd.springboot.framework.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * com.thd.springboot.framework.web.ExceptionHandlerController
 *
 * @author: wanglei62
 * @DATE: 2020/9/15 15:42
 **/
@RestControllerAdvice
@Order(999)
public class ExceptionHandlerController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public Message handleException(Exception e){
        logger.error(e.getMessage(), e);
        return Message.error(e.getMessage());
    }
}
