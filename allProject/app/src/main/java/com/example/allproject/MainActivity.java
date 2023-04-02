package com.example.allproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.compute.MainActivityCompute;
import com.example.login.MainActivity_login;
import com.example.myapplication.MainActivity_shopping;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //定义按钮变量
    private Button bt_login,bt_compute,bt_shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //定义初始化方法
        init();
    }
    public void init(){
        //监听器
        bt_login = findViewById(R.id.bt_login);
        bt_compute = findViewById(R.id.bt_compute);
        bt_shop = findViewById(R.id.bt_shop);
        bt_login.setOnClickListener(this);
        bt_compute.setOnClickListener(this);
        bt_shop.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_login:
                //跳转到登录页面
                Intent intent_login = new Intent(MainActivity.this, MainActivity_login.class);
                startActivityForResult(intent_login,1);
                break;
            case R.id.bt_compute:
                //跳转到计算机页面
                Intent intent_compute = new Intent(MainActivity.this, MainActivityCompute.class);
                startActivityForResult(intent_compute,1);
                break;
            case R.id.bt_shop:
                //跳转到购物页面
                Intent intent_shop = new Intent(MainActivity.this, MainActivity_shopping.class);
                startActivityForResult(intent_shop,1);
                break;
        }

    }
}