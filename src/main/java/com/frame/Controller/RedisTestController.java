package com.frame.Controller;

import com.frame.BeanVo.UserVo;
import com.frame.Config.User;
import com.frame.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: yinxin
 * @description: redis测试Controller
 * @create: 2019-07-29 20:51
 **/
@RestController
public class RedisTestController {

    @Autowired
    User users;

    @RequestMapping(value = "/user/setRedisValue",method = RequestMethod.POST,headers = "content-type=application/JSON")
    public Map<String,String> setRedisValue(@RequestBody UserVo user){
        Map<String,String> result = new HashMap<String,String>();
        List<Map<String,String>> filterUsers = users.getUsers().stream().filter(map -> map.get("name").toString().equals(user.getName())).collect(Collectors.toList());
        String flag = RedisUtil.setValue(filterUsers.get(0).get("name"),filterUsers.get(0).get("password"));
        if("OK".equals(flag)){
            result.put("code","0000");
            result.put("message","插入成功");
        }else{
            result.put("code","0001");
            result.put("message","未知错误");
        }
        return result;
    }
}
