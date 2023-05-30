package com.example.mymusic;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mymusic.musicAdapt.MusicListAdapter;
import com.example.mymusic.musicServers.MusicService;
import com.example.mymusic.util.Music;
import com.example.mymusic.util.MusicUtil;
import com.example.mymusic.util.PermissionUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private Handler handler;//交互页面处理机制
    private static final String TAG="MainActivity";
    private ConstraintLayout cl_main;
    private TextView tv_leftTime,tv_rightTime,tv_song;
    private SeekBar seekBar;
    private ImageButton ib_precious,ib_state,ib_next;
    private MusicService.MyBinder myBinder;
    private ListView lv_music;
    private List<Music> list;//数据集
    private MusicUtil utils;
    private MusicListAdapter adapter;
    private SimpleDateFormat time=new SimpleDateFormat("m:ss");//时间显示格式
    private Intent MediaServiceIntent;
    private boolean isPlaying=false;//判断是否在播放
    private int seek_flag=0;//进度条标志量
    private int music_index=0;//音乐索引
    private String song;//歌曲名字
    public ButtonBroadcastReceiver buttonBroadcastReceiver;//广播接收器
    private NotificationManager manager;
    private Notification notify;
    private RemoteViews remoteViews;
    public final static String BUTTON_PREV_ID="BUTTON_PREV_ID";//对应Action
    public final static String BUTTON_PLAY_ID="BUTTON_PLAY_ID";
    public final static String BUTTON_NEXT_ID="BUTTON_NEXT_ID";
    public final static String BUTTON_CLOSE_ID="BUTTON_CLOSE_ID";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){//全屏显示
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_music);

        InitView();
        MediaServiceIntent =new Intent(this,MusicService.class);//MediaServiceIntent为一个Intent
        bindService(MediaServiceIntent,connection,BIND_AUTO_CREATE);
        check();

    }

    //初始化各个控件，初始状态，以及监听
    private void InitView(){
        tv_leftTime=findViewById(R.id.tv_leftTime);
        tv_rightTime=findViewById(R.id.tv_rightTime);
        tv_song=findViewById(R.id.tv_song);
        seekBar=findViewById(R.id.seekBar);
        ib_precious=findViewById(R.id.ib_precious);
        ib_state=findViewById(R.id.ib_state);
        ib_next=findViewById(R.id.ib_next);

        utils=new MusicUtil();

        manager= (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        remoteViews=new RemoteViews(getPackageName(),R.layout.b);

        list=new ArrayList<>();
        list= MusicUtil.getMp3InfoList(this);
        adapter=new MusicListAdapter(this,list);
        adapter.setCurrentItem(0);
        song=list.get(0).getName();
        tv_song.setText(song);

        //歌曲列表点击某个Item
        lv_music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                check();
                music_index=position;
                isPlaying=true;
                adapter.setCurrentItem(music_index);
                adapter.notifyDataSetInvalidated();
                song=list.get(music_index).getName();
                tv_song.setText(song);
                myBinder.playMusic(music_index);
                ib_state.setImageResource(R.drawable.stop_sing_button);
                showNotification();
            }
        });
        ib_precious.setOnClickListener(l);
        ib_state.setOnClickListener(l);
        ib_next.setOnClickListener(l);
        handler=new Handler();
        setNotification();
        initButtonReceiver();
        showNotification();

    }

    //按钮点击事件监听
    public View.OnClickListener l=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ib_state:
                    check();
                    if(!isPlaying){
                        isPlaying=true;
                        song=list.get(music_index).getName();
                        tv_song.setText(song);
                        ib_state.setImageResource(R.drawable.stop_sing_button);
                        if(seek_flag==0) {//是0说明刚开始播放
                            myBinder.playMusic(music_index);
                        }else{//暂停过后的情况
                            myBinder.playMusic(music_index);
                            myBinder.seekToPosition(seek_flag);
                        }
                    }else{
                        isPlaying=false;
                        ib_state.setImageResource(R.drawable.start_sing_button);
                        seek_flag=myBinder.getPlayPosition();//获取当前位置暂停
                        myBinder.pauseMusic();
                    }
                    showNotification();
                    break;
                case R.id.ib_precious://上一首
                    check();
                    isPlaying=true;
                    ib_state.setImageResource(R.drawable.stop_sing_button);
                    seek_flag=0;
                    if(music_index<=0){
                        music_index=list.size()-1;
                    }else{
                        music_index-=1;
                    }
                    showNotification();
                    seekBar.setMax(myBinder.getProgress(music_index));
                    song=list.get(music_index).getName();
                    tv_song.setText(song);
                    myBinder.preciousMusic();
                    break;
                case R.id.ib_next://下一首
                    check();
                    isPlaying=true;
                    ib_state.setImageResource(R.drawable.stop_sing_button);
                    seek_flag=0;
                    if(music_index>=list.size()-1){
                        music_index=0;
                    }else{
                        music_index+=1;
                    }
                    showNotification();
                    seekBar.setMax(myBinder.getProgress(music_index));
                    song=list.get(music_index).getName();
                    tv_song.setText(song);
                    myBinder.nextMusic();
                    break;
            }
        }
    };

    //Service连接初始化
    private ServiceConnection connection=new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (MusicService.MyBinder) service;
            seekBar.setMax(myBinder.getProgress());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //这里是判断进度条移动是不是用户所为
                    if(fromUser){
                        myBinder.seekToPosition(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            handler.post(runnable);
            Log.d(TAG, "Service与Activity已连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //界面刷新
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(myBinder.getPlayPosition());
            tv_leftTime.setText(time.format(myBinder.getPlayPosition())+"");
            tv_rightTime.setText(time.format(myBinder.getProgress()-myBinder.getPlayPosition())+"");
            if(myBinder.getProgress()-myBinder.getPlayPosition()<1000){//时间不够了自动触发下一首
                runOnUiThread(new Runnable() {//使用uiThread触发点击事件
                    @Override
                    public void run() {
                        ib_next.performClick();
                    }
                });
            }
            handler.postDelayed(runnable,1000);
        }
    };

    //检查权限
    private void check(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                Log.d(TAG,"---------------------写权限不够-----------------");
            }
            if(checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO)!= PackageManager.PERMISSION_GRANTED ){
                requestPermissions(new String[]{Manifest.permission.READ_MEDIA_AUDIO}, 2);
                Log.d(TAG,"---------------------读权限不够-----------------");
            }
        }
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "---------------------写权限够了-----------------------------");
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "---------------------读权限够了-----------------------------");
                }
                break;
        }
    }

    //设置通知
    private void setNotification(){
        String channelID="cary";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(channelID,"xxx",NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notify=new Notification.Builder(MainActivity.this,channelID)
                    .setWhen(System.currentTimeMillis())
                    .setSound(null)
                    .build();
        }
        notify.icon=android.R.drawable.btn_star;
        notify.contentIntent=pi;
        notify.contentView=remoteViews;
        notify.flags=Notification.FLAG_ONGOING_EVENT;
        remoteViews.setOnClickPendingIntent(R.id.notice,pi);
        //上一首
        Intent prevIntent=new Intent(BUTTON_PREV_ID);
        PendingIntent prevPendingIntent=PendingIntent.getBroadcast(this,0,prevIntent,0);
        remoteViews.setOnClickPendingIntent(R.id.widget_prev,prevPendingIntent);
        //播放暂停
        Intent playIntent=new Intent(BUTTON_PLAY_ID);
        PendingIntent playPendingIntent=PendingIntent.getBroadcast(this,0,playIntent,0);
        remoteViews.setOnClickPendingIntent(R.id.widget_play,playPendingIntent);
        //下一首
        Intent nextIntent=new Intent(BUTTON_NEXT_ID);
        PendingIntent nextPendingIntent=PendingIntent.getBroadcast(this,0,nextIntent,0);
        remoteViews.setOnClickPendingIntent(R.id.widget_next,nextPendingIntent);
        //关闭
        Intent closeIntent=new Intent(BUTTON_CLOSE_ID);
        PendingIntent closePendingIntent=PendingIntent.getBroadcast(this,0,closeIntent,0);
        remoteViews.setOnClickPendingIntent(R.id.widget_close,closePendingIntent);
    }
    //展示通知
    private void showNotification(){
        if(isPlaying){
            remoteViews.setImageViewResource(R.id.widget_play,R.drawable.stop_sing_button);
        }else{
            remoteViews.setImageViewResource(R.id.widget_play,R.drawable.start_sing_button);
        }
        remoteViews.setTextViewText(R.id.widget_title,list.get(music_index).getName());
        remoteViews.setTextViewText(R.id.widget_artist,list.get(music_index).getArtist());
        remoteViews.setTextColor(R.id.widget_title,Color.BLACK);
        remoteViews.setTextColor(R.id.widget_artist,Color.BLACK);
        notify.contentView=remoteViews;
        manager.notify(100,notify);
    }

    //注册广播
    private void initButtonReceiver(){
        buttonBroadcastReceiver=new ButtonBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BUTTON_PREV_ID);
        intentFilter.addAction(BUTTON_PLAY_ID);
        intentFilter.addAction(BUTTON_NEXT_ID);
        intentFilter.addAction(BUTTON_CLOSE_ID);
        registerReceiver(buttonBroadcastReceiver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        myBinder.closeMusic();
        if(remoteViews!=null){
            manager.cancel(100);
        }
    }


    public class ButtonBroadcastReceiver extends BroadcastReceiver {

        //基本都是通过触发uiThread，执行相应按钮被按下的操作
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.d(TAG,"--------------------收到action:"+action+"--------------------------");
            if(action.equals(BUTTON_PREV_ID)){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ib_precious.performClick();
                        return;
                    }
                });
            }
            if(action.equals(BUTTON_PLAY_ID)){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ib_state.performClick();
                        return;
                    }
                });
            }
            if(action.equals(BUTTON_NEXT_ID)){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ib_next.performClick();
                        return;
                    }
                });
            }
            if(action.equals(BUTTON_CLOSE_ID)){
                handler.removeCallbacks(runnable);
                myBinder.closeMusic();
                unbindService(connection);
                if(remoteViews!=null){
                    manager.cancel(100);
                }
                unregisterReceiver(buttonBroadcastReceiver);
                finish();
            }
        }
    }
}