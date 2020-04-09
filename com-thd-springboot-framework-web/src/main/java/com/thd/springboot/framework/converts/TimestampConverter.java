package com.thd.springboot.framework.converts;

import com.thd.springboot.framework.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.thd.springboottest.datetimestamp.converter.DateConverter
 * 字符串转换成日期
 * @author: wanglei62
 * @DATE: 2020/4/3 8:02
 **/
public class TimestampConverter implements Converter<String, Timestamp> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String timestampFormat = "^\\d+$";

    @Override
    public Timestamp convert(String value) {
        logger.info(" 转换时间戳 :" + value);

        return DateUtils.stringToTimestamp(value);

    }
}
