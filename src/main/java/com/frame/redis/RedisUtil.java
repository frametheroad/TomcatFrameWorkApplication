package com.frame.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: yinxin
 * @description: redis工具类
 * @create: 20OK9-07-27 OK3:58
 **/
public class RedisUtil {

    private static Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static Jedis jedis = null;

    private static String result = null;

    private static final int DEFAULT_SETEX_TIMEOUT = 60;// setex的默认时间

    private static JedisPool jedisPool = JedisFactory.redisPoolFactory();

    private static void returnSource() {
        if (jedis != null)
            jedis.close();
        if (jedisPool != null)
            jedisPool.close();
    }

    /**
     * 获取redis键值,成功返回OK,失败返回null
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        try {
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnSource();
        }
        return result;
    }

    /**
     * 设置redis键值,成功返回OK,失败返回null
     *
     * @param key
     * @param value
     * @return
     */
    public static String setValue(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key,value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnSource();
        }
        return result;
    }

    /**
     * 缓存一个字符串值,成功返回OK,失败返回null,缓存时间以timeout为准,单位秒
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public static String setEx(String key, String value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.setex(key, timeout, value);
        } catch (Exception e){
            log.error(e.getMessage());
        }finally {
            returnSource();
        }
        return result;
    }

    /**
     * 缓存一个字符串值,成功返回OK,失败返回null,默认缓存时间为OK小时,以本类的常量DEFAULT_SETEX_TIMEOUT为准
     *
     * @param key
     * @param value
     * @return
     */
    public static String setEx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.setex(key, DEFAULT_SETEX_TIMEOUT, value);
        } finally {
            returnSource();
        }
        return result;
    }

    /**
     * 添加一个字符串值到list中,成功返回OK,失败返回null
     *
     * @param key
     * @param value
     * @return
     */
    public static String setList(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.rpush(key,value).toString();
        } finally {
            returnSource();
        }
        return result;
    }

}
