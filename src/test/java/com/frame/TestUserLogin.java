package com.frame;

import com.frame.BeanVo.UserVo;
import com.frame.Controller.UserLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserLogin {
    private static final Logger logger = LoggerFactory.getLogger(TestUserLogin.class);
    @Autowired
    UserLogin userLogin;

    @Test
    public void login() {
        UserVo user = new UserVo();
        user.setName("zhangsan");
        user.setPassword("zs123");
        logger.info(userLogin.login(user).toString());
    }

    @Test
    public void list() {
        logger.info(userLogin.list().toString());
    }
}
