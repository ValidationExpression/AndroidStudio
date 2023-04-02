package com.example.createactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_peach_out extends AppCompatActivity implements View.OnClickListener {

    //定义变量
    private Button peach01, peach02, peach03, peach04;
    private Button peach_out;
    //记录桃子个数
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peach_out);
        //方法体-监听的初始化
        init();
    }

    private void init() {
        peach01 = findViewById(R.id.peach01);
        peach02 = findViewById(R.id.peach02);
        peach03 = findViewById(R.id.peach03);
        peach04 = findViewById(R.id.peach04);
        peach01.setOnClickListener(this);
        peach02.setOnClickListener(this);
        peach03.setOnClickListener(this);
        peach04.setOnClickListener(this);
        //退出监听
        peach_out = findViewById(R.id.peach_out);
        peach_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.peach01:
                info(peach01);
                break;
            case R.id.peach02:
                info(peach02);
                break;
            case R.id.peach03:
                info(peach03);
                break;
            case R.id.peach04:
                info(peach04);
                break;
            case R.id.peach_out:
                returnData();
                break;
        }
    }

    //定义摘桃子的方法体(可以有显示)
    private void info(Button bt) {
        //当有此动作时桃子数量变化
        count++;
        /**
         * setVisibility( )的用法（可显示或隐藏布局或控件...）
         * 1.View.VISIBLE View可见
         * 2.View.INVISIBLE View不可以见，但仍然占据可见时的大小和位置。
         * 3.View.GONE View不可见，且不占据空间。
         */
        bt.setVisibility(View.INVISIBLE);
        //提醒机制设置
        Toast.makeText(Activity_peach_out.this, "摘到" + count + "个", Toast.LENGTH_SHORT).show();
    }

    //将数据回传数据
    private void returnData() {
        Intent intent = new Intent();
        //利用键值的形式传参
        intent.putExtra("count", count);
        //携带数据进行回传
        setResult(1, intent);
        /**
         *  finish() 用于结束一个Activity的生命周期
         *  使用finish()：从activity 1中启动activity 2,在activity 2调用finish()，
         *  然后在activity 2 启动activity 3，这时按下返回键 程序就直接返回了activity 1
         */
        Activity_peach_out.this.finish();
    }

    //监听手机屏幕上的按键(返回键)
    //目的:是在手机上使用返回键时调用了returnData方法的返回方式,手机返回键可能会返回到第一个页面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK){
            //调用returnData方法--
            returnData();
        }
        return false;
    }
}