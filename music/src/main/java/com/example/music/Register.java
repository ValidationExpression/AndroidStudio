package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private EditText et_name,et_email,et_pwd;
    private Button btn_submit;
    private String name,email,pwd,sex,hobbys;
    private CheckBox cb_sing,cb_dance,cb_read;
    private RadioGroup rg_sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        init();
    }
    private void init(){
        //获取页面控件
        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_pwd=findViewById(R.id.et_pwd);
        rg_sex=findViewById(R.id.rg_sex);
        cb_sing=findViewById(R.id.cb_sing);
        cb_dance=findViewById(R.id.cb_dance);
        cb_read=findViewById(R.id.cb_read);
        btn_submit=findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);//提交按钮的监听器
        // 设置复选框控件的监听器
        cb_sing.setOnCheckedChangeListener(this);
        cb_dance.setOnCheckedChangeListener(this);
        cb_read.setOnCheckedChangeListener(this);
        hobbys=new String();
        //设置单选按钮的监听事件
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_boy:
                        sex="男";
                        break;
                    case R.id.rb_girl:
                        sex="女";
                        break;
                }
            }
        });
    }
    //获取文本框里面的信息
    private void getData(){
        name=et_name.getText().toString().trim();
        email=et_email.getText().toString().trim();
        pwd=et_pwd.getText().toString().trim();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_submit:
                getData();
            if(TextUtils.isEmpty(name)){
                Toast.makeText(Register.this,"请输入名字",Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(email)) {
                Toast.makeText(Register.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(Register.this,"请输入密码",Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(sex)) {
                Toast.makeText(Register.this,"请选择性别",Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(hobbys)) {
                Toast.makeText(Register.this,"请选择爱好",Toast.LENGTH_SHORT).show();
            }else{
                //跳转到登录界面的同时把名字，账号，密码信息传送到登录界面
//                1.创建一个意图,使用putExtra方法添加数据,name,email,pwd就是注册时输入的信息
                Intent intent=new Intent();
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("pwd",pwd);
                //调用数据回传的setResult()方法
                setResult(1,intent);
                //把注册的name和pwd存储在SharePreferences内
                String content_name=name;
                String content_pwd=pwd;
                boolean isSaveSuccess=SPSaveQQ.saveUserInfo(this,content_name,content_pwd);
                finish();
            }
            break;
        }
    }
    //复选框的点击事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String motion=buttonView.getText().toString();
        if(isChecked){
            if(!hobbys.contains(motion)){
                hobbys=hobbys+motion;
            }
        }else{
            if(hobbys.contains(motion)){
                hobbys=hobbys.replace(motion," ");
            }
        }
    }
}