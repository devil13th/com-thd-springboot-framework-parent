package com.thd.springboot.framework.jackson.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thd.springboot.framework.jackson.jsondeserializers.JsonDateDeserializer;
import com.thd.springboot.framework.jackson.jsondeserializers.JsonTimestampDeserializer;
import com.thd.springboot.framework.jackson.jsonserializers.JsonDateSerializer;
import com.thd.springboot.framework.jackson.jsonserializers.JsonTimestampSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
@Configuration
public class JacksonAutoConfig {
    /**
     * 处理jackson反序列化转换器，
     * 用于转换Post请求体的json以及我们的对象序列化为返回响应的json
     * 使用@RequestBody注解的对象中的Date类型将从这里被转换
     */
//    @Bean
//    public ObjectMapper objectMapper(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
//
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//
//        //Date序列化和反序列化
//        javaTimeModule.addSerializer(Date.class,new JsonDateSerializer());
//        javaTimeModule.addDeserializer(Date.class,new JsonDateDeserializer());
//
//
//        // timestamp的序列化和反序列话
//        javaTimeModule.addSerializer(Timestamp.class,new JsonTimestampSerializer());
//        javaTimeModule.addDeserializer(Timestamp.class,new JsonTimestampDeserializer());
//
//
//        objectMapper.registerModule(javaTimeModule);
//        return objectMapper;
//    }

}
