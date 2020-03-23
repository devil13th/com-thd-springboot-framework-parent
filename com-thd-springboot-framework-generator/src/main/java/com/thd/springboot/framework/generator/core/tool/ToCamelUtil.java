package com.thd.springboot.framework.generator.core.tool;

import com.google.common.base.CaseFormat;

/**
 * com.thd.springboot.framework.generator.core.tool.ToCamelUtil
 * 字符串转驼峰
 * @author: wanglei62
 * @DATE: 2020/3/21 22:58
 **/
public class ToCamelUtil {
    /**
     * 字符串转驼峰
     * @param name
     * @return
     */
    public static String toCamel(String name){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }
    /**
     * 字符串转大驼峰
     * @param name
     * @return
     */
    public static String toBigCamel(String name){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }
}
