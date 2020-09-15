package com.thd.springboot.framework;

/**
 * com.thd.springboot.framework.MyException
 *
 * @author: wanglei62
 * @DATE: 2020/9/15 16:57
 **/
public class MyException extends RuntimeException{
    private String code;
    private String msg;
    public MyException(){
        super();
    }

    public MyException(String code,String msg){
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
