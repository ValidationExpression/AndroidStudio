package com.wang.utils;

import com.alibaba.fastjson2.JSONObject;
import com.wang.pojo.UserPojo;
import org.springframework.objenesis.ObjenesisHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Result {
    public static String getString(String message){
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("message",message);
        String s = JSONObject.toJSONString(map);
        return s;
    }

    public static String getStringData(String message, Object data){
        //Map<String,Object> map=new HashMap<>();
//        map.put("code",200);
//        map.put("message",message);
        ArrayList list=new ArrayList<>();
        list.add(data);
        String s = JSONObject.toJSONString(list);
        return s;
    }
}
