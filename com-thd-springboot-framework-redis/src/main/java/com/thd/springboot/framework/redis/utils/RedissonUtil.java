package com.thd.springboot.framework.redis.utils;

import com.thd.springboot.framework.utils.SpringUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Redisson封装(redis) 操作简单<br>
 * redis分布式锁

 */
public class RedissonUtil {
    private static RedissonClient redissonClient = SpringUtils.getBean(RedissonClient.class);

    /**
     * 获取Bucket
     */
    public static <T> RBucket<T> getRBucket(String key) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket;
    }

    /**
     * 保存数据
     */
    public static <T> void setObject(String key, T object) {
        RBucket<T> bucket = RedissonUtil.getRBucket(key);
        bucket.set(object);
    }

    /**
     * 保存数据（失效时间）
     */
    public static <T> void setObject(String key, T object, long expire) {
        RBucket<T> bucket = RedissonUtil.getRBucket(key);
        bucket.set(object);
        bucket.expire(expire, TimeUnit.SECONDS);
    }

    /**
     * 获取数据
     */
    public static <T> T getObject(String key) {
        return (T) RedissonUtil.getRBucket(key).get();
    }

    /**
     * 删除数据
     */
    public static <T> boolean deleteObject(String key) {
        RBucket<T> bucket = RedissonUtil.getRBucket(key);
        return bucket.delete();
    }

    /**
     * 保存map数据
     */
    public static <K, V> void setMapObject(String key, K mapKey, V mapObject) {
        RMap<K, V> map = RedissonUtil.getMap(key);
        map.put(mapKey, mapObject);
    }

    /**
     * 获保存map数据（失效时间）
     */
    public static <K, V> void setMapObject(String key, K mapKey, V mapObject, long expire) {
        RMap<K, V> map = RedissonUtil.getMap(key);
        map.put(mapKey, mapObject);
        map.expire(expire, TimeUnit.SECONDS);
    }

    /**
     * 获取RMAP
     */
    public static <K, V> RMap<K, V> getMap(String key) {
        RMap<K, V> map = redissonClient.getMap(key);
        return map;
    }

    /**
     * 获取map数据
     */
    public static <K, V> V getMapObject(String key, K mapKey) {
        RMap<K, V> map = RedissonUtil.getMap(key);
        return map.get(mapKey);
    }

    /**
     * 删除map数据
     */
    public static <K, V> boolean deleteMap(String key) {
        RMap<K, V> map = RedissonUtil.getMap(key);
        return map.delete();
    }

    /**
     * 获取RAtomicLong
     */
    public static RAtomicLong getAtomicLong(String key) {
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(key);
        return rAtomicLong;
    }

    /**
     * 自增原子long
     */
    public static void setAtomicLongNum(String key, long num, long expire) {
        try {
            RAtomicLong rAtomicLong = RedissonUtil.getAtomicLong(key);
            if (rAtomicLong.isExists()) {
                rAtomicLong.addAndGet(num);
            } else {
                rAtomicLong.set(num);
                rAtomicLong.expire(expire, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取原子long
     */
    public static long getAtomicLongNum(String key) {
        RAtomicLong bucket = RedissonUtil.getAtomicLong(key);
        return bucket.get();
    }

    public static boolean tryLock(String key) {
        try {
            return redissonClient.getLock(key).tryLock();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void unLock(String key) {
        try {
            redissonClient.getLock(key).unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
