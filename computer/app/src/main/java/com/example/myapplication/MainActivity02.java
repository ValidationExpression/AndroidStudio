package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity02 extends AppCompatActivity implements View.OnClickListener {

    //按钮变量定义
    Button bc,bde,b_add,remove,dot,multiply,divide,equal;
    public Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    //定义文本变量
    TextView edit;
    //定义用于存储输入的值（以字符串形式）
    private String inputs;
    //结果接收变量
    //用于接收来自类Compute运算层的结果
    String tests;
    float data;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //输入页面的定义
        edit = findViewById(R.id.edit);
        //对于按钮的定义
        bc = findViewById(R.id.bc);
        bde = findViewById(R.id.bde);
        b_add = findViewById(R.id.b_add);
        multiply = findViewById(R.id.multiply);
        dot = findViewById(R.id.dot);
        divide = findViewById(R.id.divide);
        remove = findViewById(R.id.remove);
        equal = findViewById(R.id.equal);
        //监听方法二
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        //
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bc.setOnClickListener(this);
        //
        bc.setOnClickListener(this);
        bde.setOnClickListener(this);
        b_add.setOnClickListener(this);
        multiply.setOnClickListener(this);
        dot.setOnClickListener(this);
        divide.setOnClickListener(this);
        remove.setOnClickListener(this);
        equal.setOnClickListener(this);
        edit.setOnClickListener(this);
        //用于存放按钮的文本信息
        inputs= new String();
    }
    //实例化类名，为调用其中的方法名
    Compute compute=new Compute();

    @Override
    public void onClick(View view) {
        //用于获取输入在文本框的內容
        String input = edit.getText().toString();
        switch (view.getId()){
            case R.id.b0:
                //获取按钮的值，并赋给inputs变量
                inputs = b0.getText().toString();
                //字符串相加操作
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b1:
                inputs = b1.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b2:
                inputs = b2.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b3:
                inputs = b3.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b4:
                inputs = b4.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b5:
                inputs = b5.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b6:
                inputs = b6.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b7:
                inputs = b7.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b8:
                inputs = b8.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.b9:
                inputs = b9.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.dot:
                inputs = dot.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.bc:
                //清除按钮，先进行判断是否有字符
                if(edit.getText()!=null){
                    inputs=inputs.replace(inputs,"");
                    edit.setText(inputs);
                }
                break;
            case R.id.bde:
                //回退操作
                inputs=inputs.substring(0,input.length()-1);
                edit.setText(inputs);
                break;
            case R.id.b_add:
                inputs = b_add.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.remove:
                inputs = remove.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.multiply:
                inputs = multiply.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.divide:
                inputs = divide.getText().toString();
                inputs=input+inputs;
                edit.setText(inputs);
                break;
            case R.id.equal:
                if(input.charAt(0)=='*' | input.charAt(0)=='÷'){
                    inputs=input.replace(input,"格式有错误,请重新输入...");
                    edit.setText(inputs);
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public synchronized void run() {
                            tests=input.replace(input,"");
                            edit.setText(tests);
                        }
                    });
                    thread.start();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //时间进程为什么
//                    try {
//                        Thread t = new Thread();
//                        Thread.sleep(3000);
//                        tests=input.replace(input,"");
//                        edit.setText(tests);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }

                }else {
                    data=compute.equaldata(equal.getText().toString(),input);
                    inputs = Float.toString(data);
                    edit.setText(inputs);
                    break;
                }
        }
        System.out.println("输出的值是:"+edit.getText());
    }
}
