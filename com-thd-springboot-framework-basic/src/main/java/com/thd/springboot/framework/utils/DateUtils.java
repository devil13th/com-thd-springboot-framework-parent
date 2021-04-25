package com.thd.springboot.framework.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
     * 1.LocalDate转Date
     *
     * LocalDate nowLocalDate = LocalDate.now();
     * Date date = Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
     *
     * 2.LocalDateTime转Date
     *
     * LocalDateTime localDateTime = LocalDateTime.now();
     * Date date = Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
     *
     * 3.Date转LocalDateTime(LocalDate)
     *
     * Date date =newDate();
     * LocalDateTime localDateTime = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
     * LocalDate localDate = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
     *
     * 4.LocalDate转时间戳
     *
     * LocalDate localDate = LocalDate.now();
     * longtimestamp = localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
     *
     * 5.LocalDateTime转时间戳
     *
     * LocalDateTime localDateTime = LocalDateTime.now();
     * longtimestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
     *
     * 6.时间戳转LocalDateTime(LocalDate)
     *
     * longtimestamp = System.currentTimeMillis();
     * LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
     * LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
     */
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
     * 字符串 转 LocalDate
     * @param value
     * @return
     */
    public static LocalDate stringToLocalDate(String value){
        Date d = DateUtils.stringToDate(value);
        return DateUtils.dateToLocalDate(d);
    }

    /**
     * 字符串 转 LocalDateTime
     * @param value
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String value){
        Date d = DateUtils.stringToDate(value);
        return DateUtils.dateToLocalDateTime(d);
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
     * Date 转 Timestamp
     * @param d
     * @return
     */
    public static LocalDate dateToLocalDate(Date d){
        if(d == null){
            return null;
        }
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 Timestamp
     * @param d
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date d){
        if(d == null){
            return null;
        }
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date 转 Timestamp
     * @param d
     * @return
     */
    public static LocalTime dateToLocalTime(Date d){
        if(d == null){
            return null;
        }
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
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
