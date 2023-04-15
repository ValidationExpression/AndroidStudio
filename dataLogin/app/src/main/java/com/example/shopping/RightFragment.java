package com.example.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.datalogin.R;

public class RightFragment extends Fragment {
    private ListView mListView;

    public RightFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载第一个视图
        View view1 = inflater.inflate(R.layout.right_layout, container, false);

        // 加载第二个视图
        //view2 = inflater.inflate(R.layout.layout_fragment2, container, false);

        //mListView = view.findViewById(R.id.shop);

        // 设置 RecyclerView 的布局管理器和适配器
        // mListView.setAdapter(new LinearLayoutManager(getActivity()));
        //mListView.setAdapter(new ProductAdapter(getActivity()));

        return view1;
    }
}
