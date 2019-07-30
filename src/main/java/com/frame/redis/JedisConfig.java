package com.frame.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: yinxin
 * @description: Jedis配置文件
 * @create: 2019-07-27 13:46
 **/
@Component
@ConfigurationProperties(prefix = "jedis")
public class JedisConfig extends CachingConfigurerSupport {

    private static Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    private static String host;

    private static int port;

    private static int timeout;

    private static int maxActive;

    private static int maxIdle;

    private static int minIdle;

    private static long maxWaitMillis;

    @Value("${jedis.host}")
    public void setHost(String host) {
        this.host = host;
    }

    @Value("${jedis.port}")
    public void setPort(int port) {
        this.port = port;
    }

    @Value("${jedis.timeout}")
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Value("${jedis.max-active}")
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    @Value("${jedis.max-idle}")
    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Value("${jedis.max-wait}")
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    @Bean
    public static JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,null);

        logger.info("JedisPool注入成功！");
        logger.info("redis地址：" + host + ":" + port);

        return jedisPool;
    }

}
