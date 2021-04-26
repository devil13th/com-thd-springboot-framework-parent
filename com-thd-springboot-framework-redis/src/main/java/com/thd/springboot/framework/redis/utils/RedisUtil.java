package com.thd.springboot.framework.redis.utils;

import com.thd.springboot.framework.utils.SpringUtils;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 简单封装redis操作 <br>
 * 目前没有从DB层面做项目间区分，仅仅通过项目前缀
 *
 */
public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplateWithJacksonSer");

    private RedisUtil() {
    }

    /**
     * 保存数据到redis
     *
     * @param key
     * @param value
     * @return void
     */
    public static void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存数据到redis，有时效时间，默认：秒
     *
     * @param key
     * @param value
     * @param timeout
     * @return void
     */
    public static void setValue(String key, Object value, long timeout) {
        setValue(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 保存数据到redis，有时效时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @return void
     */
    public static void setValue(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 保存Map值数据到redis
     *
     * @param key
     * @param hashKey
     * @param value
     * @return void
     */
    public static void setMapValue(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 保存Map值数据到redis
     *
     * @param key
     * @param hashKey
     * @param value
     * @return void
     */
    public static void setMapValue(String key, String hashKey, Object value, long timeout) {
        setMapValue(key, hashKey, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 保存Map值数据到redis
     *
     * @param key
     * @param hashKey
     * @param value
     * @param timeout
     * @param timeUnit
     * @return void
     */
    public static void setMapValue(String key, String hashKey, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }


    /**
     * redis中获取数据
     *
     * @param key
     * @return T
     */
    public static <T> T getValue(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * redis中获取Map值数据
     *
     * @param key
     * @param hashKey
     * @return T
     */
    public static <T> T getMapValue(String key, String hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }


    /**
     * 删除redis里某个Map中的值
     *
     * @param key
     * @param hashKey
     * @return void
     */
    public static void delMapValue(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 删除redis中某个值
     *
     * @param key
     * @return void
     */
    public static void delValue(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 设置失效时间
     *
     * @param key
     * @param timeout
     * @return void
     */
    public static void expire(String key, long timeout) {
        expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置失效时间
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return void
     */
    public static void expire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获取Map键值对
     */
    public static <HK, HV> Map<HK, HV> getMapEntries(String key) {
        return (Map<HK, HV>) redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取Map所有Value
     */
    public static <HV> List<HV> getMapValues(String key) {
        return (List<HV>) redisTemplate.opsForHash().values(key);
    }

    /**
     * 获取Map所有key
     */
    public static <T> Set<T> getMapKeys(String key) {
        return (Set<T>) redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取Map,根据map kay的pattern条件
     */
    public static <HK, HV> Map<HK, HV> getMapScan(String key, String pattern) {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().count(1000).match(pattern).build());
        Map<HK, HV> map = new HashMap<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            map.put((HK) entry.getKey(), (HV) entry.getValue());
        }
        try {
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 判断时候存在这个key
     *
     * @param key
     * @return boolean
     */
    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取满足条件的key集合
     *
     * @param pattern
     * @return Set<E>
     */
    public static <E> Set<E> keys(String pattern) {
        return (Set<E>) redisTemplate.keys(pattern);
    }

    /**
     * 数值增加
     *
     * @param key
     * @return
     */
    public static Long incr(String key, long n) {
        return redisTemplate.opsForValue().increment(key, n);
    }

    /**
     * 添加set值
     *
     * @param
     * @return
     **/
    public static boolean zsetAadd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 删除set值
     *
     * @param
     * @return
     **/
    public static Long zsetDel(String key, Object... value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 获取set数量
     *
     * @param
     * @return
     **/
    public static Long zsetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 查找set，按照score正序分页
     *
     * @param key
     * @param start 起始条数（包含）
     * @param end   结束条数（包含）
     * @return
     */
    public static Set<Object> zsetRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }


    /**
     * 查找set，按照score倒序分页
     *
     * @param key
     * @param start 起始条数（包含）
     * @param end   结束条数（包含）
     * @return
     */
    public static Set zsetRevRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 放入列表尾部
     *
     * @param key
     * @param value
     * @return
     */
    public static <V> Long rpush(String key, V value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }


    /**
     * 查看列表大小
     *
     * @param key
     * @return
     */
    public static Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 查看列表数据
     *
     * @param key
     * @param start 起始(包含)
     * @param end   结束(包含)
     * @return
     */
    public static <V> List<V> lrange(String key, int start, int end) {
        return (List<V>) redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 出去列表头部，先进先出
     *
     * @param key
     * @return
     */
    public static <V> V lpop(String key) {
        return (V) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 删除指定的值
     *
     * @param key
     * @param value 指定的值
     * @return
     */
    public static <V> V lremove(String key, V value) {
        return (V) redisTemplate.opsForList().remove(key, 0, value);
    }

    /**
     * redis发布消息
     *
     * @param channel
     * @param message
     */
    public static void sendMessage(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }

    public static String getValueSerializer(byte[] body) {
        return (String) redisTemplate.getValueSerializer().deserialize(body);
    }

    public static String getStringSerializer(byte[] channel) {
        return (String) redisTemplate.getStringSerializer().deserialize(channel);
    }

    /**
     * 锁
     *
     * @param key
     * @param timeOut ms 毫秒
     * @return 是否取到
     */
    public static boolean lock(String key, long timeOut) {
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            String redisKey = key;
            long expireAt = connection.time() + timeOut + 1;
            Boolean acquire = connection.setNX(redisKey.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(redisKey.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < connection.time()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(redisKey.getBytes(), String.valueOf(connection.time() + timeOut + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < connection.time();
                    }
                }
            }
            return false;
        });
    }

    /**
     * 解锁
     *
     * @param key
     * @return void
     */
    public static void unlock(String key) {
        delValue(key);
    }


}
