**SpringBoot基础框架(基于Tomcat)**
在线编写MD文件：[https://www.mdeditor.com/](https://www.mdeditor.com/)
### 配置文件加载实体类
#### 配置文件：

------------

##### 1. application.yml

```yaml
server:
#启动端口
  port: 8899
#项目根路径
  servlet:
    context-path: /service
#启动环境
spring:
  profiles:
    active: dev
```
springboot 启动默认加载 resources文件夹下：application.(propertyes/yml)文件
根据配置文件里spring.profiles.active参数引入对应配置文件
#####  2. application-dev.xml
```yaml
#定义当前配置环境
spring:
  profiles:
    active: dev
#配置日志配置路径使用${spring.profiles.active}动态加载Log配置
logging:
  config: classpath:logCfg/logback-${spring.profiles.active}.xml
#自定义配置文件,后续用于加载成JavaBean
project:
  users:
    - {name: zhangsan,password: zs123,uname: 天上的云}
    - {name: lisi,password: ls123,uname: 地上的草}

```
#### 3. User.java
```java
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

```
