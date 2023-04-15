package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.datalogin.MainActivity;
import com.example.datalogin.R;

public class LoginCreate extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText createLogin_name,createLogin_emil,createLogin_passwd1,createLogin_passwd2;
    private RadioGroup two_sex;
    private String sex,name,emil,password1,password2,hobby;
    private CheckBox createLogin_bird,createLogin_pingpang,createLogin_music,createLogin_read;
    private Button createLogin;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlogin);
        //实例化创建数据库类
        init();
        hobby = new String();
    }

    private void init() {
        createLogin_name = findViewById(R.id.createLogin_name);
        createLogin_emil = findViewById(R.id.createLogin_emil);
        createLogin_passwd1 = findViewById(R.id.createLogin_passwd1);
        createLogin_passwd2 = findViewById(R.id.createLogin_passwd2);
        //性别
        two_sex = findViewById(R.id.two_sex);
        //兴趣爱好
        createLogin_bird = findViewById(R.id.createLogin_bird);
        createLogin_pingpang = findViewById(R.id.createLogin_pingpang);
        createLogin_music = findViewById(R.id.createLogin_music);
        createLogin_read = findViewById(R.id.createLogin_read);
        //按钮
        createLogin = findViewById(R.id.createLogin);
        createLogin.setOnClickListener(this);
        //复选框监听
        createLogin_bird.setOnCheckedChangeListener(this);
        createLogin_pingpang.setOnCheckedChangeListener(this);
        createLogin_music.setOnCheckedChangeListener(this);
        createLogin_read.setOnCheckedChangeListener(this);
        //单选框
        two_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.sex_boy:
                        sex = "男";
                        break;
                    case R.id.sex_girl:
                        sex = "女";
                        break;
                }
            }
        });
    }

    //获取输入界面的信息
    private void getData(){
        //trim()函数用于去除字符串前后的空格
        name = createLogin_name.getText().toString().trim();
        emil = createLogin_emil.getText().toString().trim();
        password1 = createLogin_passwd1.getText().toString();
        password2 = createLogin_passwd2.getText().toString();
    }

    //复选框点击事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String motion = buttonView.getText().toString();
        if (isChecked){
            if (!hobby.contains(motion)){
                hobby = hobby+motion;
            }
        }else {
            //不选中时会取消复选框
            if (!hobby.contains(motion)){
                hobby = hobby.replace(motion,"");
            }
        }
    }

    @Override
    public void onClick(View view) {
        //字典对象，可以用来存储键值对
        ContentValues values;
        switch (view.getId()){
            //触发按钮事件
            case R.id.createLogin:
                getData();
                // 校验用户名或密码是否为空
                if (TextUtils.isEmpty(emil) || TextUtils.isEmpty(password1)) {
                    Toast.makeText(LoginCreate.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 校验密码是否一致
                if (!TextUtils.equals(password1, password2)) {
                    Toast.makeText(LoginCreate.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 插入用户信息到数据库
                if (!dbHelper.validEmil(emil)){
                    if (dbHelper.insertUser(name, emil, password1,password2,sex,hobby)) {
                        Toast.makeText(LoginCreate.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginCreate.this, "用户名已存在！", Toast.LENGTH_SHORT).show();
                    return;
                }
                back();
                break;
        }
    }

    //返回到上一界面
    private void back(){
        //数据回传
        Intent intent = new Intent();
        intent.putExtra("emil",emil);
        intent.putExtra("password",password1);
        setResult(1,intent);
        LoginCreate.this.finish();
    }
    //返回上一页面(利用是本机的返回按键)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK){
            back();
        }
        return false;
    }
}