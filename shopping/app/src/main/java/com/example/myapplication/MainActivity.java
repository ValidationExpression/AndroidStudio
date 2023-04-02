package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private String[] titles = {"a","b","c","d","e","f"};
    private String[] prices = {"1","2","3","4","5","6"};
    private int[] icons = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        lv=findViewById(R.id.lv);


    }
    class MyBaseAdapter extends BaseAdapter{

        //获取数据的条数
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int i) {
            return titles[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            //找出list_view文件并转换成view对象
            if(view == null){
                view = View.inflate(MainActivity.this,R.layout.activity_shopping,null);
                holder = new ViewHolder();
                holder.title = view.findViewById(R.id.iv_title);
                holder.price = view.findViewById(R.id.iv_price);
                holder.lv = view.findViewById(R.id.lv);
                view.setTag(holder);
            }else {
                holder = (ViewHolder)view.getTag();
            }
            holder.title.setText(titles[i]);
            holder.price.setText(prices[i]);
            holder.lv.setBackgroundResource(icons[i]);
            return view;
        }
    }
}