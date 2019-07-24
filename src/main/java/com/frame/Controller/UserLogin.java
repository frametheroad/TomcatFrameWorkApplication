package com.frame.Controller;

import com.frame.BeanVo.UserVo;
import com.frame.Config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserLogin {
    @Autowired
    User users;
    @RequestMapping("/user/list")
    public List<Map<String,String>> list(){
        return users.getUsers();
    }
    @RequestMapping(value = "/user/login",method = RequestMethod.POST,headers = "content-type=application/JSON")
    public Map<String,String> login(@RequestBody  UserVo user){
        Map<String,String> result = new HashMap<String,String>();
        List<Map<String,String>> filterUsers = users.getUsers().stream().filter(map -> map.get("name").toString().equals(user.getName())).collect(Collectors.toList());
        if(filterUsers.size()==0){
            result.put("code","0000000000");
            result.put("message","用户名不存在");
            return result;
        }
        if(filterUsers.stream().filter(map -> map.get("password").toString().equals(user.getPassword())).collect(Collectors.toList()).size()==0){
            result.put("code","0000000000");
            result.put("message","【"+filterUsers.get(0).get("uname").toString()+"】用户密码错误");
            return result;
        }
        result.put("code","0000000000");
        result.put("message","【"+filterUsers.get(0).get("uname").toString()+"】用户登录成功！");
        return result;
    }
}
