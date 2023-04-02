package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_shopping extends AppCompatActivity {
    //定义列表布局
    private ListView mListView;
    //准备适配器
    private SimpleAdapter mSimpleAdapter;
    //准备数据源
    private List<Map<String,Object>> mList;
    //物品
    private String[] titles = {"a","b","c","d","e","f"};
    //价格
    private String[] prices = {"1","2","3","4","5","6"};
    //声明图片资源
    private int[] icons = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        mListView = findViewById(R.id.lv);
        //数据加载
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String,Object> map=new HashMap<>();
            //1.按着上方定义的图片顺序进行取,并放入map中
            //对i取余的含义时,因为上方图片数量,不够满足循环.
            map.put("img",icons[i%icons.length]);
            //2.物品放入
            map.put("title",titles[i%titles.length]);
            //3.价钱
            map.put("price","  "+prices[i%prices.length]);
            //此时创建了一条Map,在放入到列表中
            mList.add(map);

        }
        //参数的配置
        mSimpleAdapter = new SimpleAdapter(this,
                mList,
                //要和上方map中写入的相同
                R.layout.activity_shopping,
                new String[]{"img","title","price"},
                new int[]{R.id.iv_img,R.id.iv_title,R.id.iv_price}
                );

        //将适配器设置到view
        mListView.setAdapter(mSimpleAdapter);
        //设置点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String ,Object> map = mList.get(position);
                String title = (String)map.get("title");
                Toast.makeText(MainActivity_shopping.this,"点击:"+position+title,Toast.LENGTH_SHORT);
            }
        });
    }

}
