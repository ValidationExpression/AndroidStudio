package com.example.myfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // 开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 添加Fragment到容器中并提交事务
        MyFragment fragment1 = MyFragment.newInstance(R.layout.activity_fragment);
        fragmentTransaction.add(R.id.fragment_container, fragment1, "fragment1");

        MyFragment fragment2 = MyFragment.newInstance(R.layout.activity_fragment1);
        fragmentTransaction.add(R.id.fragment_container1, fragment2, "fragment2");
        //事务提交
        fragmentTransaction.commit();
    }
}