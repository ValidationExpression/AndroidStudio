package com.example.login;

import android.content.Intent;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        findViewById(R.id.but).setOnClickListener(this);
        findViewById(R.id.but_dail).setOnClickListener(this);
        findViewById(R.id.but_sms).setOnClickListener(this);
        findViewById(R.id.but_ben).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String phoneNum = "152";
        switch (view.getId()){
            //触发按钮事件
            case R.id.but:
                Intent intent = new Intent();
                // 注意这是在其他应用AndroidManifest.xml文件中自定义的一个名
                intent.setAction("android.intent.action.NING");
                // 通过添加Intent.CATEGORY_DEFAULT类别，可以确保该Intent能够被系统匹配到最适合的组件来处理
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
                break;
            case R.id.but_dail:
                //隐式跳转(到拨号页面)
                Intent intent1 = new Intent();
                // 使用该代码后，当这个意图被启动时，会自动打开手机拨号界面并填充指定的电话号码，用户可以直接点击拨号按钮呼叫该号码
                intent1.setAction(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:"+phoneNum);
                intent1.setData(uri);
                startActivity(intent1);
                break;
            case R.id.but_sms:
                //隐式跳转(到短信页面)
                Intent intent2 = new Intent();
                // 使用该代码后，当这个意图被启动时，会打开短信页面
                intent2.setAction(Intent.ACTION_SENDTO);
                Uri uri2 = Uri.parse("sms:"+phoneNum);
                intent2.setData(uri2);
                startActivity(intent2);
                break;
            case R.id.but_ben:
                Intent intent3 = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent3);
                break;
        }
    }
}
