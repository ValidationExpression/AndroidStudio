package com.example.music;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSaveQQ {
    public static boolean saveUserInfo(Context context, String account, String password){
        //获取sp对象
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=sp.edit();
        //Editor的putString()方法把账号和密码放入该对象
        edit.putString("username",account);
        edit.putString("pwd",password);
        //commit()方法用于把数据保存到data.xml文件内
        edit.commit();
        return true;
    }
    public static Map<String ,String> getUserInfo(Context context){
        //获取sp对象,
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        //获取数据,如果没有需要的数据就返回空字符串
        String account=sp.getString("username",null);
        String password=sp.getString("pwd",null);
        //把获取的数据放到map内
        Map<String,String> userMap=new HashMap<String,String>();
        userMap.put("account",account);
        userMap.put("password",password);
        return userMap;
    }
}
