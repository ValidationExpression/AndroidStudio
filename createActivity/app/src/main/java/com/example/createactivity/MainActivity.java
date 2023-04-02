package com.example.createactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //定义变量
    private Button peach;
    private TextView t_count;
    //定义一个变量用于存储回传数据
    private int sumpeach = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peach);
        //监听事件
        peach = findViewById(R.id.peach);
        t_count = findViewById(R.id.t_count);
        peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_peach_out.class);
                // startActivity(intent)不可回传数据
                //startActivity(intent);
                //startActivityForResult(intent,1);用于数据回传使用
                startActivityForResult(intent,1);

            }
        });
    }
    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        //重写该事件处理程序来处理子Activity的返回结果。
        //Data:用于打包返回数据的Intent,可以包括用于表示所选内容的URI
        super.onActivityResult(requestCode,resultCode,data);
        /**
         * requestCode 请求码，即调用startActivityForResult()传递过去的值
         * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
         */
        if (requestCode == 1&& resultCode==1){
            int count = data.getIntExtra("count",0);
            sumpeach = sumpeach+count;
            //在首页面显示摘回桃子的个数
            t_count.setText("摘了"+sumpeach+"个桃子.");
        }
    }
}