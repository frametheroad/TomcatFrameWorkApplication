package com.frame.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component 添加这个注释方便Spring扫描器
@Component
//加载配置文件配置,通过Set方法注入对象
@ConfigurationProperties(prefix = "demo")
public class JedisConfig {
    private static Map<String,String> jedis;

    public static Map<String, String> getJedis() {
        return jedis;
    }

    public void setJedis(Map<String, String> jedis) {
        this.jedis = jedis;
    }
}
