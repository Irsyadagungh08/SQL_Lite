package com.example.sqllite;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {


    List<Data> data ;
    OnUserClickListener listener;
    public RecyclerviewAdapter(List<Data> list, home home) {
        this.data = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }
    public interface OnUserClickListener{
        void onUserClick(Data data, String action);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final Data currentPerson = data.get(position);
        holder.tjudul.setText(currentPerson.getJudul());
        holder.tdesk.setText(currentPerson.getDeskripsi());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson,"Edit");
               }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson,"Delete");
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tdesk, tjudul;
        ImageView imgDelete,imgEdit;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tdesk=itemView.findViewById(R.id.tdesk);
            tjudul=itemView.findViewById(R.id.tdesk);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgEdit=itemView.findViewById(R.id.imgEdit);
        }
    }
}