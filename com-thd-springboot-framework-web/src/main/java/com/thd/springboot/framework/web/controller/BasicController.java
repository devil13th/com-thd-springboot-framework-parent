package com.thd.springboot.framework.web.controller;

import com.thd.springboot.framework.constants.CommonConstants;
import com.thd.springboot.framework.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.thd.springboot.framework.web.controller.BasicController
 *
 * @author: wanglei62
 * @DATE: 2020/1/20 17:58
 **/
public class BasicController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    public Message success(){
        return Message.success();
    }

    public Message success(String msg){
        return Message.success(msg);
    }

    public Message success(Object result){
        return Message.success(result);
    }

    public Message error(){
        return Message.error();
    }

    public Message error(String msg){
        return Message.error(msg);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }



}
