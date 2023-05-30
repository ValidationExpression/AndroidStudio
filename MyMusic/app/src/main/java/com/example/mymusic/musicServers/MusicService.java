package com.example.mymusic.musicServers;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.example.mymusic.util.Music;
import com.example.mymusic.util.MusicUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service {
    private static final String TAG="MusicService";
    private List<Music> list;
    private MyBinder myBinder;
    public MediaPlayer mediaPlayer;
    public MusicService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind is call");
        myBinder=new MyBinder();
        return myBinder;
    }
    public class MyBinder extends Binder{
        private int index=0;//歌曲索引
        //播放音乐
        public void playMusic(int index){
            this.index=index;
            try {
                File file=new File(list.get(this.index).getPath());
                if(!file.exists()){
                    Log.d(TAG,"文件不存在");
                    return ;
                }else{
                    Log.d(TAG,"文件："+file.getPath()+"存在    ------------------------------");
                }
                if(mediaPlayer!=null){
                    mediaPlayer.reset();
                    mediaPlayer.release();
                }
                mediaPlayer=new MediaPlayer();
                String str=list.get(this.index).getPath();
                mediaPlayer.setDataSource(str);
                Log.d(TAG,list.get(this.index).getPath()+"");
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //暂停音乐
        public void pauseMusic(){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }
        //关闭音乐
        public void closeMusic(){
            if(mediaPlayer!=null){
                mediaPlayer.release();
            }
        }
        //下一首
        public void nextMusic(){
            if(index>=list.size()-1){
                this.index=0;
            }else{
                this.index+=1;
            }
            playMusic(this.index);
        }
        //上一首
        public void preciousMusic(){
            if(index<=0){
                this.index=list.size()-1;
            }else{
                this.index-=1;
            }
            playMusic(this.index);
        }
        //获取歌曲时长
        public int getProgress(int dex){
            return (int)list.get(dex).getDuration();
        }
        public int getProgress(){
            return (int)list.get(index).getDuration();
        }
        //获取当前播放位置
        public int getPlayPosition(){
            return mediaPlayer.getCurrentPosition();
        }
        //移动到当前点播放
        public void seekToPosition(int m){
            mediaPlayer.seekTo(m);
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=new MediaPlayer();
        list=new ArrayList<>();
        list= MusicUtil.getMp3InfoList(getApplicationContext());
        Log.d(TAG,list.size()+"");
        Log.d(TAG,"onCreate is call");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand is call");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy is call");
        myBinder.closeMusic();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind is call");
        return super.onUnbind(intent);
    }
}