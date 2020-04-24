package com.market.jokergame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerViewHolder>   {

    private ArrayList<PlayerClass> playerClass;

    public PlayerAdapter(ArrayList<PlayerClass> playerClass) {
        this.playerClass = playerClass;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_cardview,parent,false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.bindData(playerClass.get(position),position+1);
    }

    @Override
    public int getItemCount() {
        return playerClass.size();
    }
}
