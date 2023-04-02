package com.example.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    EditText name,edit,password;
    String hobbys,sex,names,edits,passwords;
    RadioGroup two_sex;
    CheckBox cd_sing,cd_dance,cd_read;
    Button butt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.antive);
        init();

    }
    private void init(){
        name = findViewById(R.id.name);
        edit = findViewById(R.id.edit);
        password = findViewById(R.id.password);

        two_sex = findViewById(R.id.two_sex);
        cd_sing = findViewById(R.id.cd_sing);
        cd_dance = findViewById(R.id.cd_dance);
        cd_read = findViewById(R.id.cd_read);
        butt = findViewById(R.id.butt);
        //设置按钮监听事件
        butt.setOnClickListener(this);
        //设置复选按钮监听事件
        cd_sing.setOnCheckedChangeListener(this);
        cd_dance.setOnCheckedChangeListener(this);
        cd_read.setOnCheckedChangeListener(this);
        hobbys =new String();
        //单选框监听事件
        two_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
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
        names = name.getText().toString().trim();
        edits = edit.getText().toString().trim();
        passwords = password.getText().toString();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //触发按钮事件
            case R.id.butt:
                getData();
                //TextUtils.isEmpty(names)进行字符串非空判断
                if(TextUtils.isEmpty(names)){
                    Toast.makeText(MainActivity.this,"请输入名字",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(edits)){
                    Toast.makeText(MainActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(passwords)){
                    Toast.makeText(MainActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sex)){
                    Toast.makeText(MainActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(hobbys)){
                    Toast.makeText(MainActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    //控制台输出信息
                    Log.i("MainActivity","用户信息:"+"名字:"+names+",邮箱:"+edits+",性别:"+sex+",兴趣爱好:"+hobbys);
                }
                break;
        }
    }
    //复选框点击事件
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //获取复选框的內容
        String motion = compoundButton.getText().toString();
        if(b){
            if(!hobbys.contains(motion)){
                hobbys=hobbys+motion;
            }
        }else {
            if(hobbys.contains(motion)){
                hobbys=hobbys.replace(motion,"");
            }
        }
    }
}
