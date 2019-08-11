package com.example.demo.controller;

import com.example.demo.pojo.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/createUser")
    public UserInfo createUser() {
        UserInfo u=new UserInfo();
        u.setName("林志玲");
        u.setAddress("上海浦东");

        UserInfo u1=userService.createUser(u);

        //System.out.println(u1.getId());

        return u1;

    }

    @RequestMapping("/deleteUser")
    public String deleteUser() {

        userService.deleteUser(15);

        return "删除成功";
    }

    @RequestMapping("/updateUser")
    public String updateUser() {

        UserInfo u1=new UserInfo();
        u1.setId(16);
        u1.setName("Justin Timberlake");
        userService.updateUser(u1);

        return "修改成功";
    }
    @RequestMapping("/queryUser")
    public List<UserInfo> queryUser(){
        return userService.queryUser(16);
    }

    @RequestMapping("/createListUser")
    public String createListuser() {
        userService.createListUser();
        return "test";
    }
}

