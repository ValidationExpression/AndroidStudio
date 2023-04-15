package com.example.datalogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.DBHelper;
import com.example.login.LoginCreate;
import com.example.shopping.Shopping;
import com.example.shopping.Shopping1;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    //定义变量
    private EditText login_emil, login_passwd;
    private CheckBox login_save;
    private TextView login_create;
    private Button login;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //定义初始化方法
        init();
        //通过工具类获取账号密码
        Map<String, String> userInfo = FileSaveInData.getUserInfo(this);
        if (userInfo != null) {
            login_emil.setText(userInfo.get("account"));
            login_passwd.setText(userInfo.get("password"));
        }
    }

    private void init() {
        login_emil = findViewById(R.id.login_emil);
        login_passwd = findViewById(R.id.login_passwd);
        login = findViewById(R.id.login);
        login_create = findViewById(R.id.login_create);
        login_save = findViewById(R.id.login_save);
        //监听方案3
        login.setOnClickListener(this);
        login_create.setOnClickListener(this);
        login_save.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                //设置跳转方式(登录)
                //当点击保存账号密码时,获取上方输入的账号与密码
                String emil = login_emil.getText().toString().trim();//trim()函数用于去除字符串前后的空格
                String password1 = login_passwd.getText().toString();
                // 校验用户名和密码是否正确
                if (dbHelper.validUser(emil, password1)) {
                    Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    //跳转到登录成功后的页面
                    Intent intent0 = new Intent(MainActivity.this, Shopping1.class);
                    startActivityForResult(intent0,1);
                } else {
                    Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_create:
                //设置跳转方式(注册)
                Intent intent1 = new Intent(MainActivity.this, LoginCreate.class);
                startActivityForResult(intent1,1);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //当点击保存账号密码时,获取上方输入的账号与密码
        String emil = login_emil.getText().toString().trim();//trim()函数用于去除字符串前后的空格
        String password = login_passwd.getText().toString();
        //检验账号密码是否为空
        if (TextUtils.isEmpty(emil)) {
            Toast.makeText(this, "请输入邮箱:", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码:", Toast.LENGTH_SHORT).show();
            return;
        }
        //保存用户信息到data.txt文件
        boolean isSaveSucccess = FileSaveInData.saveUserInfo(this, emil, password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        String e="",p;
        if (requestCode == 1 && resultCode==1){
            String emil = data.getStringExtra("emil");
            String password = data.getStringExtra("password");
            login_emil.setText(emil);
            login_passwd.setText(password);

        }
    }
}