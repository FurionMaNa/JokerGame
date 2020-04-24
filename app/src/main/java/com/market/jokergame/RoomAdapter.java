package com.market.jokergame;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder>  {


    private ArrayList<RoomClass> roomClasses;

    public RoomAdapter(ArrayList<RoomClass> roomClasses) {
        this.roomClasses = roomClasses;
    }


    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_cardview,parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.bindData(roomClasses.get(position));
    }

    @Override
    public int getItemCount() {
        return roomClasses.size();
    }
}
