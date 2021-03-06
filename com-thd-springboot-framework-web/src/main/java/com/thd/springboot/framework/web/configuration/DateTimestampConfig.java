package com.thd.springboot.framework.web.configuration;


import com.thd.springboot.framework.web.converts.DateConverter;
import com.thd.springboot.framework.web.converts.LocalDateConverter;
import com.thd.springboot.framework.web.converts.LocalDateTimeConverter;
import com.thd.springboot.framework.web.converts.TimestampConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * com.thd.springboottest.datetimestamp.configuration.DateConfig
 *
 * @author: wanglei62
 * @DATE: 2020/4/3 8:14
 **/
@Configuration
public class DateTimestampConfig {



    /**
     * Date转换器, 用于转换@RequestParam和@PathVariable修饰的参数
     */
    @Bean
    public Converter<String, Date> dateConverter(){
        return new DateConverter();
    }

    /**
     * 时间戳转换器 , 用于转换@RequestParam和@PathVariable修饰的参数
     * @return
     */
    @Bean
    public Converter<String, Timestamp> timestampConverter(){
        return new TimestampConverter();
    }


    /**
     * localDate转换器 , 用于转换@RequestParam和@PathVariable修饰的参数
     * @return
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter(){
        return new LocalDateConverter();
    }

    /**
     * localDate转换器 , 用于转换@RequestParam和@PathVariable修饰的参数
     * @return
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter(){
        return new LocalDateTimeConverter();
    }











}
