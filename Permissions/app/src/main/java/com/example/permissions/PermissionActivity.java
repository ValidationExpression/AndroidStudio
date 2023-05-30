package com.example.permissions;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 动态申请权限的步骤
 * 1. 检查app是否开启了指定权限
 *    使用ContextCompat的checkSelfPermission方法
 * 2. 请求系统弹框.以便用户选择是否开启权限
 *    调用ActivityCompat的requestPermission方法,
 * 3. 判断用户权限的结果
 *     重写活动页面的权限请求回调方法onRequestPermissionsResult,该方法用于内部处理用户权限的选择结果
 */

/**
 * 实现功能当点击读写通信录或发短信时:会触发权限的授予提示.便于用户权限获取(只有点击时才会触发)
 */
public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private final String[] PERMISSIONS_CONTACTS = new String[]{
            //通信录的读写权限
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };
    private final String[] PERMISSIONS_SMS = new String[]{
            //短信的读写权限
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS
    };
    private static final int  REQUEST_CODE_CONTACTS = 1;
    private static final int  REQUEST_CODE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        findViewById(R.id.contact).setOnClickListener(this);
        findViewById(R.id.msm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contact:
                PermissionUtil.checkPermission(this,PERMISSIONS_CONTACTS,REQUEST_CODE_CONTACTS);
                break;
            case R.id.msm:
                PermissionUtil.checkPermission(this,PERMISSIONS_SMS,REQUEST_CODE_SMS);
                break;
        }
    }

    //返回判断用户是否给定权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_CONTACTS:
                if (PermissionUtil.checkGrant(grantResults)){
                    Log.d("111","通信录权限获取成功");
                }else {
                    Toast.makeText(this, "通讯录权限获取失败", Toast.LENGTH_SHORT).show();
                    //当获取权限失败时进行系统应用跳转
                    jumToSettings();
                }
                break;
            case REQUEST_CODE_SMS:
                if (PermissionUtil.checkGrant(grantResults)){
                    Log.d("222","短信权限获取成功");
                }else {
                    Toast.makeText(this, "短信权限获取失败", Toast.LENGTH_SHORT).show();
                    jumToSettings();
                }
                break;

        }
    }

    //跳转到应用设置权限界面(可以解决误点导致权限获取失败是进行跳转)
    private void jumToSettings(){
        Intent intent = new Intent();
        //跳转
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package",getPackageName(),null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}