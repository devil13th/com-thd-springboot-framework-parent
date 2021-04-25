package com.thd.springboot.framework.jackson.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thd.springboot.framework.jackson.jsondeserializers.StringXssDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class JacksonAutoConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
            jacksonObjectMapperBuilder.failOnUnknownProperties(false);
            jacksonObjectMapperBuilder.deserializers(new StringXssDeserializer());

//            jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // jacksonObjectMapperBuilder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
            // jacksonObjectMapperBuilder.timeZone("GMT+8");
        };
    }




    /**
     * 处理jackson反序列化转换器，
     * 用于转换Post请求体的json以及我们的对象序列化为返回响应的json
     * 使用@RequestBody注解的对象中的Date类型将从这里被转换
     */
//    @Bean
//    public ObjectMapper objectMapper(){
//        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
////        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
//
////        JavaTimeModule javaTimeModule = new JavaTimeModule();
//
//        //Date序列化和反序列化
////        javaTimeModule.addSerializer(Date.class,new JsonDateSerializer());
////        javaTimeModule.addDeserializer(Date.class,new JsonDateDeserializer());
//
//
//        // timestamp的序列化和反序列话
////        javaTimeModule.addSerializer(Timestamp.class,new JsonTimestampSerializer());
////        javaTimeModule.addDeserializer(Timestamp.class,new JsonTimestampDeserializer());
//
//
////        objectMapper.registerModule(javaTimeModule);
//
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        SimpleModule itemModule = new SimpleModule();
//        itemModule.addSerializer(LocalDate.class,new JsonLocalDateSerializer());
//        itemModule.addDeserializer(LocalDate.class,new JsonLocalDateDeserializer());
//
//        itemModule.addSerializer(LocalDateTime.class,new JsonLocalDateTimeSerializer());
//        itemModule.addDeserializer(LocalDateTime.class,new JsonLocalDateTimeDeserializer());
//
//        objectMapper.registerModule(itemModule);
//
//        return objectMapper;
//    }

}
