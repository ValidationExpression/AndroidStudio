package com.example.newsix;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConAdapter extends RecyclerView.Adapter<ConAdapter.MyViewHolder> {

    private Context mContent;
    private List<ContactInfo> contentInfoList;

    public ConAdapter(Context context,List<ContactInfo> contactInfoList){
        this.mContent=context;
        this.contentInfoList=contactInfoList;
    }
    @NonNull
    @Override
    public ConAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ConAdapter.MyViewHolder holder, int position) {
        holder.name.setText(contentInfoList.get(position).getContactName());
        holder.phone.setText(contentInfoList.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return contentInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone;
        ImageView iv_img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }
}
