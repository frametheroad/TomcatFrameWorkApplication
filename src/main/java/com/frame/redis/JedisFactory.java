package com.frame.redis;

import com.frame.Config.JedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: yinxin
 * @description:
 * @create: 2019-07-31 18:16
 **/
@Component
public class JedisFactory {

    @Autowired
    private static JedisConfig jedisConfig;

    private static Logger logger = LoggerFactory.getLogger(JedisFactory.class);

    @Bean
    public static JedisPool redisPoolFactory(){
        String host = jedisConfig.getJedis().get("host");
        Integer port = Integer.parseInt(jedisConfig.getJedis().get("port"));
        Integer timeout = Integer.parseInt(jedisConfig.getJedis().get("timeout"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(jedisConfig.getJedis().get("max-idle")));
        jedisPoolConfig.setMinIdle(Integer.parseInt(jedisConfig.getJedis().get("min-idle")));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(jedisConfig.getJedis().get("max-active")));
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,null);

        logger.info("JedisPool注入成功！");
        logger.info("redis地址：" + host + ":" + port);
        return jedisPool;
    }

}
