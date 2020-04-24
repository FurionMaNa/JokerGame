package com.market.jokergame;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView num;

    public PlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        num=itemView.findViewById(R.id.num);
    }

    public void bindData(PlayerClass playerClass,Integer pos) {
        name.setText(playerClass.getNick());
        num.setText(pos.toString());
    }
}
