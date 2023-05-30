package com.wang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;

//直接继承,使用java进行对数据库(表)的操作
@Mapper
public interface UserDao extends BaseMapper<UserPojo> {
}
