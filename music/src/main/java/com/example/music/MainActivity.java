package com.example.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] song_name={"错位时空","山茶花读不懂白玫瑰","带我去找夜生活"};
    private String[] singer_name={"艾辰","王为","告五人"};
    private int[] icons={R.drawable.t1,R.drawable.t2,R.drawable.t3};
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=findViewById(R.id.lv);
        MyBaseAdapter myBaseAdapter=new MyBaseAdapter();
        mListView.setAdapter(myBaseAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //创建Intent对象，参数就是从歌曲列表页面跳转到MusicActivity音乐播放页面
                Intent intent=new Intent(MainActivity.this, MusicActivity.class);
                //将歌曲名和歌曲的下标存入Intent对象
                intent.putExtra("name",song_name[position]);
                intent.putExtra("position",String.valueOf(position));
                //开始跳转
                startActivity(intent);
            }
        });
    }
    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return song_name.length;
        }

        @Override
        public Object getItem(int position) {
            return song_name[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.activility_list_view,null);
                holder=new ViewHolder();
                holder.song_name=convertView.findViewById(R.id.song_name);
                holder.singer_name=convertView.findViewById(R.id.singer_name);
                holder.iv_photo=convertView.findViewById(R.id.iv_photo);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            holder.song_name.setText(song_name[position]);
            holder.singer_name.setText(singer_name[position]);
            holder.iv_photo.setBackgroundResource(icons[position]);
            return convertView;
        }
    }
    class ViewHolder{
        TextView song_name,singer_name;
        ImageView iv_photo;
    }
    }
