package com.thd.springboot.framework.utils;

import java.util.UUID;

/**
 * com.thd.springboot.framework.utils.UuidUtils
 *
 * @author: wanglei62
 * @DATE: 2021/4/25 10:00
 **/
public class UuidUtils {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
