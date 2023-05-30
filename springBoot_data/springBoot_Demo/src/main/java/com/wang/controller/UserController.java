package com.wang.controller;

import com.wang.service.UserService;
import com.wang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 提供接口
 * 接口的作用:为前端提供数据支持
 */
@RestController
public class UserController {

    //服务
    @Autowired
    UserService userService;

    //添加
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password){
        userService.addUser(username,password);
        return Result.getString("插入成功");
    }
    //查找
    @RequestMapping(value = "/findUserName/{username}")
    public String findUserByIdName(@PathVariable("username") String username){
        Object userName = userService.findUserName(username);
        return Result.getStringData("查找成功",userName);
    }

}
