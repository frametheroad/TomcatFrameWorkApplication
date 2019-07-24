package com.frame.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
//@Component 添加这个注释方便Spring扫描器
@Component
//加载配置文件配置,通过Set方法注入对象
@ConfigurationProperties(prefix = "project")
public class User {
    private List<Map<String,String>> users;

    public List<Map<String, String>> getUsers() {
        return users;
    }

    public void setUsers(List<Map<String, String>> users) {
        this.users = users;
    }
}
