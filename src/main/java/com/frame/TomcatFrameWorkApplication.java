package com.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TomcatFrameWorkApplication {
    private static final Logger logger = LoggerFactory.getLogger(TomcatFrameWorkApplication.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TomcatFrameWorkApplication.class, args);
        logger.info("当前框架启动环境为："+applicationContext.getEnvironment().getActiveProfiles()[0]);
    }

}
