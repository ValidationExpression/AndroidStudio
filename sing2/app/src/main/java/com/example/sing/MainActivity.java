package com.example.sing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS = new String[]{
            "android.permission.READ_MEDIA_AUDIO"
    };
    private static final int PERMISSION_REQUEST_CODE = 1;

    private ListView mListView;
    private List<Music> mMusicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_list);

        mListView = findViewById(R.id.lv);
        //手动让MediaStore扫描
        MediaScannerConnection.scanFile(this,
                new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()},
                null, null);

        //检查权限
        if (PermissionUtil.checkPermission(this, PERMISSIONS, PERMISSION_REQUEST_CODE)) {
            // 获取本地音乐列表
            getLocalMusicList();
        }

        // 绑定适配器
        MusicListAdapter adapter = new MusicListAdapter(this, mMusicList);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && PermissionUtil.checkGrant(grantResults)) {
            getLocalMusicList();
        }
    }

    @SuppressLint("Range")
    private void getLocalMusicList() {

        String[] projection = { MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION};

        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Music music = new Music();
                // 获取音乐文件的路径
                music.path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                // 获取音乐文件名
                music.name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                // 获取音乐文件时长
                music.duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

                // 创建Music对象，并添加到列表中
                mMusicList.add(music);
                Log.d("1111111111111111","sing"+music.toString());
            }

            cursor.close();
        }
    }
}