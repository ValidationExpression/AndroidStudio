package com.example.myfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private int mLayoutResId;

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance(int layoutResId) {
        MyFragment fragment = new MyFragment();
        //Bundle 是 Android 中的一个类，用于存储数据的容器，具有类似于 Map 的键值对结构，可以用于在不同的组件之间传递数据
        Bundle args = new Bundle();
        args.putInt("layout_res_id", layoutResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getArguments().getInt()它主要是用于获取 Fragment 中传递过来的布局资源 ID
        if (getArguments() != null) {
            mLayoutResId = getArguments().getInt("layout_res_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(mLayoutResId, container, false);
    }
}