package com.example.mymusic.musicAdapt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.mymusic.R;
import com.example.mymusic.util.Music;
import com.example.mymusic.util.MusicUtil;

import java.util.List;
import java.util.Locale;

public class MusicListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Music> mMusicList;
    private ViewHolder holder=null;
    private int currentItem=0;

//    public MusicListAdapter(Context context, List<Music> musicList) {
//        mContext = context;
//        mMusicList = musicList;
//    }
public  void setCurrentItem(int currentItem){
    this.currentItem=currentItem;
}
   public MusicListAdapter(Context context,List<Music> list){
    this.mContext=context;
    this.mMusicList=list;
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


//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_list, parent, false);
//            holder = new ViewHolder();
//            holder.nameTextView = convertView.findViewById(R.id.iv_title);
//            holder.durationTextView = convertView.findViewById(R.id.iv_time);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        Music music = mMusicList.get(position);
//        holder.nameTextView.setText(music.getName());
//        holder.durationTextView.setText(formatTime(music.getDuration()));
//
//        return convertView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.activity_list,null);
            holder.tv_title=convertView.findViewById(R.id.iv_title);
            holder.tv_artist=convertView.findViewById(R.id.iv_title);
            holder.tv_duration=convertView.findViewById(R.id.iv_time);
            holder.tv_position=convertView.findViewById(R.id.iv_position);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(mMusicList.get(position).getName());
        holder.tv_artist.setText(mMusicList.get(position).getArtist());
        long duration = mMusicList.get(position).getDuration();
        String time= MusicUtil.formatTime(duration);
        holder.tv_duration.setText(time);
        holder.tv_position.setText(position+1+"");
        if(currentItem == position){
            holder.tv_title.setSelected(true);
            holder.tv_position.setSelected(true);
            holder.tv_duration.setSelected(true);
            holder.tv_artist.setSelected(true);
        }else{
            holder.tv_title.setSelected(false);
            holder.tv_position.setSelected(false);
            holder.tv_duration.setSelected(false);
            holder.tv_artist.setSelected(false);
        }
        return convertView;
    }
    class ViewHolder{
        TextView tv_title;//歌曲名
        TextView tv_artist;//歌手
        TextView tv_duration;//时长
        TextView tv_position;//序号
    }

//    private static class ViewHolder {
//        TextView nameTextView;
//        TextView durationTextView;
//    }

    private String formatTime(long time) {
        int minute = (int) (time / 1000 / 60);
        int second = (int) (time / 1000 % 60);
        return String.format(Locale.getDefault(), "%02d:%02d", minute, second);
    }
}
