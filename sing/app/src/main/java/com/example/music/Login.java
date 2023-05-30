package com.example.music;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
     String email,pwd,email1="1",pwd1="1",save_zhanghao,save_pwd;
     Button btn,btn_register;
     EditText ed_email,ed_pwd;
//     设置读取通讯录的权限
    private static final String[] PERMISSIONS_CONTACTS=new String[]{
        Manifest.permission.READ_CONTACTS
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //查找界面控件
        ed_email=findViewById(R.id.et_email1);
        ed_pwd=findViewById(R.id.et_pwd1);
        //把email写入ed_email
        ed_email.setText(email);
        //把pwd写入ed_pwd
        ed_pwd.setText(pwd);
        //设置账号和密码文本框的监听器
        ed_pwd.setOnClickListener(this);
        ed_email.setOnClickListener(this);
        //登录按钮
        btn=findViewById(R.id.btn_submit1);
        btn.setOnClickListener(this);
        //注册按钮
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        //获取SharedPerferences存储的名字和密码
        Map map=SPSaveQQ.getUserInfo(Login.this);
        save_zhanghao=(String)map.get("account");//名字
        save_pwd=(String)map.get("password");//密码
        ed_email.setText(save_zhanghao);
        ed_pwd.setText(save_pwd);
        ActivityCompat.requestPermissions(Login.this,new String[]{"android.permission.READ_CONTANTED"},1);
    }

    //onActivityResult()方法可以获取回传的数据并且调用getInfo方法把注册时使用的账号和密码自动填写到文本框内
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&& resultCode==1){
            getInfo(data);
        }
    }

    //Activity之间的数据传输
    public void getInfo(Intent data){
        //获取注册时的账号和密码
     email=data.getStringExtra("email");
     pwd=data.getStringExtra("pwd");
     //把数据填入文本框
     ed_email.setText(email);
     ed_pwd.setText(pwd);
    }

//设置登录按钮和注册按钮的监听事件
    @Override
    public void onClick(View v) {
        //获取登录页面账号和密码文本框的内容
        email1=ed_email.getText().toString().trim();
        pwd1=ed_pwd.getText().toString().trim();
        switch (v.getId()){
            //如果点击登录按钮，并且都editview都不为空的话，并且自己填写的就跳转到计算器页面
            case R.id.btn_submit1:

                         Toast.makeText(Login.this,"登录成功" ,Toast.LENGTH_SHORT).show();
                         //信息填写没有错误之后跳转到List_View页面
                         Intent intent=new Intent(Login.this,MainActivity.class);
                         //跳转之后清空现有的任务栈重新创建一个新的任务栈 ，使用新的任务栈放置计算器页面
                          intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                         startActivity(intent);

                         Toast.makeText(Login.this,"用户信息有误，请重新输入" ,Toast.LENGTH_SHORT).show();
                         //同时清空文本框的内容 ed_email,ed_pwd
                         ed_email.setText("");
                         ed_pwd.setText("");

                break;
            case R.id.btn_register:
                //如果点击注册按钮就进行数据回传
                Intent intent1=new Intent(Login.this,Register.class);
                startActivityForResult(intent1,1);
        }
    }
}