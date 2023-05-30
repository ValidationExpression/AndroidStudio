package com.example.sing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MusicListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Music> mMusicList;

    public MusicListAdapter(Context context, List<Music> musicList) {
        mContext = context;
        mMusicList = musicList;
    }

    @Override
    public int getCount() {
        return mMusicList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMusicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_list, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = convertView.findViewById(R.id.iv_title);
            holder.durationTextView = convertView.findViewById(R.id.iv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Music music = mMusicList.get(position);
        holder.nameTextView.setText(music.getName());
        holder.durationTextView.setText(formatTime(music.getDuration()));

        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
        TextView durationTextView;
    }

    private String formatTime(long time) {
        int minute = (int) (time / 1000 / 60);
        int second = (int) (time / 1000 % 60);
        return String.format(Locale.getDefault(), "%02d:%02d", minute, second);
    }
}
