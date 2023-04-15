package com.example.shopping;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datalogin.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shopping extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    //定义列表布局
    private ListView mListView;
    //准备适配器
    private SimpleAdapter mSimpleAdapter;

    private Map<String,List<Map<String ,Object>>> map;
    //物品
    private String[] titles1 = {"a","b","c","d","e","f"};
    //价格
    private String[] prices1 = {"1","2","3","4","5","6"};
    //声明图片资源
    private int[] icons1 = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    //物品
    private String[] titles2 = {"a1","b1","c1","d1","e1","f1"};
    //价格
    private String[] prices2 = {"1","2","3","4","5","6"};
    //声明图片资源
    private int[] icons2 = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.right_layout);
        mListView = findViewById(R.id.lv);

        //参数的配置
//        mSimpleAdapter = new SimpleAdapter(this,
//                mList1,
//                //要和上方map中写入的相同
//                R.layout.activity_shopping,
//                new String[]{"img","title","price"},
//                new int[]{R.id.iv_img,R.id.iv_title,R.id.iv_price}
//        );

        //将适配器设置到view
        mListView.setAdapter(mSimpleAdapter);
        //设置点击事件
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Map<String ,Object> map = mList1.get(position);
//                String title = (String)map.get("title");
//                Toast.makeText(Shopping.this,"点击:"+position+title,Toast.LENGTH_SHORT);
//            }
//        });
    }

    private void setData(){
        //数据加载
        map = new HashMap<>();
        List<Map<String ,Object>> mList1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String,Object> map1=new HashMap<>();
            //1.按着上方定义的图片顺序进行取,并放入map中
            //对i取余的含义时,因为上方图片数量,不够满足循环.
            map1.put("img",icons1[i%icons1.length]);
            //2.物品放入
            map1.put("title",titles1[i%titles1.length]);
            //3.价钱
            map1.put("price","  "+prices1[i%prices1.length]);
            //此时创建了一条Map,在放入到列表中
            mList1.add(map1);
        }
        //将推荐列表放入到map中
        map.put("1",mList1);
        List<Map<String ,Object>> mList2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Map<String,Object> map1=new HashMap<>();
            //1.按着上方定义的图片顺序进行取,并放入map中
            //对i取余的含义时,因为上方图片数量,不够满足循环.
            map1.put("img",icons2[i%icons2.length]);
            //2.物品放入
            map1.put("title",titles2[i%titles2.length]);
            //3.价钱
            map1.put("price","  "+prices2[i%prices2.length]);
            //此时创建了一条Map,在放入到列表中
            mList2.add(map1);
        }
        map.put("2",mList2);

    }

    public void switchData(List<Map<String ,Object>> list){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //
    }
}
