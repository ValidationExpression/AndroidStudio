package com.example.permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {
    //检查多个权限,返回true表示已完全启用权限,false表示未完全启用权限
    public static boolean checkPermission(Activity act, String[] permissions, int requestCode) {
        //版本判断(在android 6.0之前不需要动态申请权限)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int check = PackageManager.PERMISSION_GRANTED;
            for (String permission: permissions){
               check = ContextCompat.checkSelfPermission(act,permission);
               //只要有一个权限没有开启就会执行
               if (check != PackageManager.PERMISSION_GRANTED){
                   break;
               }
            }
            //未开启权限则请求系统弹出框,让用户选择
            if (check != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(act,permissions,requestCode);
                return false;
            }
        }
        return true;
    }

    //用于检查数组,返回true表示已经获取到权限(0有权限,-1没有权限)
    public static boolean checkGrant(int[] grantResults) {
         if (grantResults != null){
             //遍历数组结果
             for (int grant: grantResults){
                 //只要有一个没有权限返回false
                 if (grant != PackageManager.PERMISSION_GRANTED){
                     return false;
                 }
             }
             return true;
         }
        return false;
    }
}
