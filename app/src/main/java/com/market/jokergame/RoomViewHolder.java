package com.market.jokergame;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RoomViewHolder extends RecyclerView.ViewHolder  {

    private TextView name;
    private TextView count;
    private CardView elem;

    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        count=itemView.findViewById(R.id.count);
        elem=itemView.findViewById(R.id.elem);
    }

    public void bindData(RoomClass roomClass) {
        name.setText(roomClass.getName());
        count.setText(roomClass.getCount()+"/4");
        elem.setTag(roomClass.getId());
        elem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomListActivity.ConnectRoom(v);
            }
        });
    }


}
