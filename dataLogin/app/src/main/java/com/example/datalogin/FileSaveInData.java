package com.example.datalogin;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileSaveInData {
    /**
     * 用于保存登录的账号与密码(内部存储)
     */
    public static boolean saveUserInfo(Context context,String account,String password){
        //创建数据输入方法
        FileOutputStream fos = null;
        try {
            //获取文件输入流
            fos = context.openFileOutput("data.txt",Context.MODE_PRIVATE);
            //将数据转换形式存入到data.txt中,形式为--键:值
            fos.write((account+":"+password).getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos!=null){
                    fos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 获取data.txt文件中的账号密码
     */
    public static Map<String,String> getUserInfo(Context context){
        String content = "";
        //输出流
        FileInputStream fis = null;
        try {
            //获取对象
            fis = context.openFileInput("data.txt");
            //数据转化
            byte[] buffer = new byte[fis.available()];
            //读取数据
            fis.read(buffer);
            //将获取的字节码转换为字符串
            content = new String(buffer);
            Map<String,String > userMap = new HashMap<String,String>();
            //对字符串以":"分隔后形成一个数组
            String[] info = content.split(":");
            userMap.put("account",info[0]);
            userMap.put("password",info[1]);
            return userMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
