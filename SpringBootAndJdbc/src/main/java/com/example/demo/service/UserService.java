package com.example.demo.service;

import java.util.List;

import javax.annotation.Resource;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public UserInfo createUser(UserInfo u) {
        return userDao.createUser(u);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public void updateUser(UserInfo u) {
        userDao.updateUser(u);
    }

    public List<UserInfo> queryUser(int id){
        return userDao.queryUser(id);
    }

    public void createListUser() {
        userDao.createUserList();
    }
}

