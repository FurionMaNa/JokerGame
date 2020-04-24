package com.market.jokergame;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomViewHolder extends RecyclerView.ViewHolder  {

    private TextView name;
    private TextView count;

    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        count=itemView.findViewById(R.id.count);
    }

    public void bindData(RoomClass roomClass) {
        name.setText(roomClass.getName());
        count.setText(roomClass.getCount()+"/4");
    }


}
