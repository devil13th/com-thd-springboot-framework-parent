package com.thd.springboot.framework.model;

import com.thd.springboot.framework.constants.CommonConstants;
import lombok.Data;

import java.io.Serializable;

/**
 * com.thd.springboot.framework.model.Message
 *
 * @author: wanglei62
 * @DATE: 2020/1/20 18:02
 **/
@Data
public class Message<T> implements Serializable {

    private static final long serialVersionUID = 6407243559651442287L;

    /**
     * 0为正常返回，>0为业务错误,<0为系统错误
     */
    private String code;

    /**
     * 提示msg
     */
    private String msg;

    /**
     * 内容
     */
    private T result;

    public Message(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(String code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }


    /**
     * 成功消息
     *
     * @param msg
     * @param result
     * @return
     */
    public static Message success(String msg, Object result) {
        return new Message(CommonConstants.STATUS_SUCCESS_CODE, msg, result);
    }

    public static Message success(Object result) {
        return new Message(CommonConstants.STATUS_SUCCESS_CODE, CommonConstants.STATUS_SUCCESS, result);
    }

    public static Message success() {
        return new Message(CommonConstants.STATUS_SUCCESS_CODE, CommonConstants.STATUS_SUCCESS);
    }

    public static Message success(String msg) {
        return new Message(CommonConstants.STATUS_SUCCESS_CODE, msg);
    }

    /**
     * 失败消息
     *
     * @return
     */
    public static Message error() {
        return new Message(CommonConstants.STATUS_ERROR_CODE, CommonConstants.STATUS_ERROR);
    }

    /**
     * 失败消息
     *
     * @param msg
     * @return
     */
    public static Message error(String msg) {
        return new Message(CommonConstants.STATUS_ERROR_CODE, msg);
    }

    /**
     * 失败消息
     *
     * @param code
     * @param msg
     * @return
     */
    public static Message error(String code, String msg) {
        return new Message(code, msg);
    }

}
