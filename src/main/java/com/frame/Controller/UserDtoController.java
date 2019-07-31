package com.frame.Controller;

import com.frame.BeanVo.UserDto;
import com.frame.exception.NullIdException;
import com.frame.service.MongodbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wuming on 2019/7/30.
 */
@RestController
public class UserDtoController {
    @Autowired
    MongodbService<UserDto> MongodbService;


    @RequestMapping(value = "save")
    public String save() {
        UserDto user = new UserDto("cs", 1, "wum");
        MongodbService.save(user);
        return "保存成功!";
    }


    @RequestMapping(value = "findAll")
    public List<UserDto> findAll() {
        return MongodbService.findAll(new UserDto());
    }


    @RequestMapping(value = "findById/{id}")
    public UserDto findById(@PathVariable("id") String id) {
        return MongodbService.findById(id, new UserDto());
    }


    @RequestMapping(value = "deleteById")
    public List<UserDto> delete() {
        String id = "5b0f99d2d3372328a097c5d6";
        MongodbService.deleteById(id, new UserDto());
        return findAll();
    }


    @RequestMapping(value = "update")
    public List<UserDto> update() {
        String id = "5b0f60d3c9b20d735cbfe087";
        MongodbService.updateById(id, "name", "zhangssss", new UserDto());
        return findAll();
    }


    @RequestMapping(value = "saveOrUpdate")
    public List<UserDto> saveOrUpdate() {
        UserDto user = new UserDto();
        user.setOccupation("java开发");
        user.set_id("5b0fba07d3372324f0c61baf");
        MongodbService.saveOrUpdate(user);
        return findAll();
    }


    @RequestMapping(value = "updates")
    public List<UserDto> up() {
        UserDto user = new UserDto("wum11111", 55, "IT");
        user.set_id("5b0fba07d3372324f0c61baf");
        try {
            MongodbService.update(user);
        } catch (NullIdException e) {
            e.printStackTrace();
        }
        return findAll();
    }
}
