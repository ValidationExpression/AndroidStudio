package com.example.mymusic.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.List;

public class MusicUtil {

    //生成歌曲列表
    @SuppressLint("Range")
    public static List<Music> getMp3InfoList(Context context){
        Cursor cursor=context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,null);
        List<Music> mp3InfoList=new ArrayList<>();
        while(cursor.moveToNext()){
            Music mp3Info=new Music();
            mp3Info.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));//path
            mp3Info.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            mp3Info.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            mp3Info.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
            mp3Info.setId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            mp3InfoList.add(mp3Info);
        }
        return mp3InfoList;
    }
    //格式化时间，转换为分/秒
    public static String formatTime(long time){
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + time / (1000 * 60) + "";
        } else {
            min = time / (1000 * 60) + "";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
}
