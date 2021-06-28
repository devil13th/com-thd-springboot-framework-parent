package com.thd.springboot.framework.rabbitmq.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.thd.springboot.framework.jackson.jsondeserializers.JsonDateDeserializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.thd.springboot.framework.rabbitmq.configuration.RabbitConfig
 *
 * @author: wanglei62
 * @DATE: 2021/6/25 16:06
 **/
@Configuration
public class RabbitConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    public RabbitTemplate rabbitTemplate(){

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);


        // 事务模式
//        rabbitTemplate.setChannelTransacted(true);

        // 使用return-callback时必须设置mandatory为true，设置交换机处理失败消息的模式
        rabbitTemplate.setMandatory(true);

        // confirm模式
        // 确认模式
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("消息唯一标识：" + correlationData);
                System.out.println("确认结果：" + ack);
                System.out.println("失败原因：" + cause);
                if (!ack) {
                    System.err.println("异常处理...");
                }
            }
        });

        // 回退模式： 当消息发送给Exchange后，Exchange路由到Queue失败是 才会执行 ReturnCallBack
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.err.println("消息丢失：return exchange: " + exchange + ", routingKey: " + routingKey + ", replyCode" +
                        ": " + replyCode + ", replyText: " + replyText);
            }
        });


        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(new ObjectMapper() {{
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            registerModule(new SimpleModule().addDeserializer(Date.class, new JsonDateDeserializer()));
            setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        }});


        // 设置json序列化
        // rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }
}
