package com.thd.springboot.framework.redis.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;

/**
 * com.thd.springboot.framework.redis.configuration.Configuration
 *
 * @author: wanglei62
 * @DATE: 2020/5/8 9:23
 **/
@Configuration
public class RedisConfig {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

//    @Value("${spring.redis.password}")
//    private String password;

    @Value("${spring.redis.block-when-exhausted}")
    private boolean  blockWhenExhausted;

    @Bean
    public JedisPool redisPoolFactory()  throws Exception{
        log.info("JedisPool注入成功！！");
        log.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return jedisPool;
    }



    @Bean("redisTemplateWithJacksonSer")
    public RedisTemplate<Object, Object> redisTemplateWithJacksonSer(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // =================== 创建Jackson2JsonRedisSerialize 序列化器  使用ObjectMapper进行序列化和反序列化=================== //

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class); // 默认的使用JdkSerializationRedisSerializer
        ObjectMapper objectMapper = new ObjectMapper();


        /**
         * 特别注意这个配置
         * 此项必须配置，否则反序列化会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
         * 因为反序列化时候都是转换成Map类型
         *
         * 加了此配置，序列化的时候会将数据类型写到json中,才可以进行正常json转对象的反序列化
         *
         * 加了改配置，如果有自定义的序列化器，不仅要重写JsonSerializer.serialize方法还要重写JsonSerializer.serializeWithType方法
         * 否则会报错 Type id handling not implemented for type XXX
         */
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        // 解决localdate反序列化报错
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.registerModule(new JavaTimeModule());

        // null属性不进行序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置在反序列化时忽略在JSON字符串中存在，而在Java中不存在的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // Jackson2JsonRedisSerializer内部是通过ObjectMapper来进行序列化和反序列化的,所以要设置ObjectMapper
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);





        // =================== 创建 StringRedisSerializer 序列化器 使用String进行序列化和反序列化=================== //
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        // hash的value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;

    }


    @Bean("redisTemplateWithJdkSer")
    public RedisTemplate<Object, Object> redisTemplateWithJdkSer(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

        // =================== 创建 StringRedisSerializer 序列化器 使用String进行序列化和反序列化=================== //
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        // hash的value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }
}
