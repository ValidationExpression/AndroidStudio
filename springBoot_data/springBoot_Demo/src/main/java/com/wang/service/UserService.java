package com.wang.service;

import com.wang.dao.UserDao;
import com.wang.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//服务层实现功能
@Service
public class UserService {
    //实现UserDao中的功能
    @Autowired
    UserDao userDao;

    public void addUser(String username,String password){
        //插入数据
        userDao.insert(new UserPojo(username,password));
    }
    //查询操作(精准查询)
    public Object findUserName(String username){
        UserPojo userPojo = userDao.selectById(username);
        System.out.println("id:"+userPojo.getUsername()+"--"+"password"+userPojo.getPassword());
        return userPojo;
    }

}
