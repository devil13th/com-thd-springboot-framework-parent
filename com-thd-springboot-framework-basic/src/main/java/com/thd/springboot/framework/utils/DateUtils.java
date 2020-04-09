package com.thd.springboot.framework.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.thd.springboot.framework.utils.DateUtils
 *
 * @author: wanglei62
 * @DATE: 2020/4/8 19:15
 **/
public class DateUtils {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String timestampFormat = "^\\d+$";

    /**
     * 字符串 转 Date
     * @param value
     * @return
     */
    public static Date stringToDate(String value){
        if(value == null || value.trim().equals("") || value.equalsIgnoreCase("null")){
            return null;
        }
        try{
            if(value.contains("-")){
                SimpleDateFormat sdf;
                if(value.contains(":")){ // 长日期 yyyy_MM_dd HH:mm:ss
                    sdf = new SimpleDateFormat(dateFormat);
                }else{   // 短日期 yyyy_MM_dd
                    sdf = new SimpleDateFormat(shortDateFormat);
                }
                return sdf.parse(value);
            }else if(value.matches(timestampFormat)){ // 时间戳 1585875084
                Long timestamp = new Long(value);
                return new Date(timestamp);
            }
        }catch(Exception e){
            throw new RuntimeException(String.format(" parser %s to Date Failed",value));
        }
        throw new RuntimeException(String.format(" parser %s to Date Failed",value));
    }

    /**
     * 字符串 转 Timestamp
     * @param value
     * @return
     */
    public static Timestamp stringToTimestamp(String value){
        Date d = DateUtils.stringToDate(value);
        return new Timestamp(d.getTime());
    }

    /**
     * Date 转 字符串
     * @param d
     * @param pattern 格式
     * @return
     */
    public static String dateToString(Date d , String pattern){
        if(d == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(d);
    }

    /**
     * Timestamp 转 字符串
     * @param t
     * @param pattern
     * @return
     */
    public static String timestampToString(Timestamp t,String pattern){
        Date d = new Date(t.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(d);
    }

    /**
     * Timestamp 转 Date
     * @param t
     * @return
     */
    public static Date timestampToDate(Timestamp t){
        if(t == null){
            return null;
        }
        return new Date(t.getTime());
    }

    /**
     * Date 转 Timestamp
     * @param d
     * @return
     */
    public static Timestamp dateToTimestamp(Date d){
        if(d == null){
            return null;
        }
        return new Timestamp(d.getTime());
    }

    /**
     * Date 转 Long
     * @param d
     * @return
     */
    public static Long dateToLong(Date d){
        if(d == null){
            return null;
        }
        return d.getTime();
    }

    /**
     * Timestamp 转 Long
     * @param t
     * @return
     */
    public static Long timestampToLong(Timestamp t){
        if(t == null){
            return null;
        }
        return t.getTime();
    }

    /**
     * Date 转 时间戳字符串
     * @param d
     * @return
     */
    public static String dateToLongString(Date d){
        if(d == null){
            return null;
        }
        return String.valueOf(d.getTime());
    }

    /**
     * Timestamp 转 时间戳字符串
     * @param t
     * @return
     */
    public static String timestampToLongString(Timestamp t){
        if(t == null){
            return null;
        }
        return String.valueOf(t.getTime());
    }
}
