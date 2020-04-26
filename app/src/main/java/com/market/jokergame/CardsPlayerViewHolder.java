package com.market.jokergame;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CardsPlayerViewHolder extends RecyclerView.ViewHolder  {

    private ImageView card;

    public CardsPlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        card=itemView.findViewById(R.id.card);
    }

    public void bindData(CardsClass cardsClasses) {
        switch (cardsClasses.getSuit()+String.valueOf(cardsClasses.getWeight().toString())){
            case "d6.0": card.setImageResource(R.drawable.d6);break;
            case "d7.0": card.setImageResource(R.drawable.d7);break;
            case "d8.0": card.setImageResource(R.drawable.d8);break;
            case "d9.0": card.setImageResource(R.drawable.d9);break;
            case "d10.0":card.setImageResource(R.drawable.d10);break;
            case "dj": card.setImageResource(R.drawable.dj);break;
            case "dq": card.setImageResource(R.drawable.dq);break;
            case "dk": card.setImageResource(R.drawable.dk);break;
            case "da": card.setImageResource(R.drawable.da);break;

            case "j": card.setImageResource(R.drawable.j);break;
            case "c7.0": card.setImageResource(R.drawable.c7);break;
            case "c8.0": card.setImageResource(R.drawable.c8);break;
            case "c9.0": card.setImageResource(R.drawable.c9);break;
            case "c10.0":card.setImageResource(R.drawable.c10);break;
            case "cj": card.setImageResource(R.drawable.cj);break;
            case "cq": card.setImageResource(R.drawable.cq);break;
            case "ck": card.setImageResource(R.drawable.ck);break;
            case "ca": card.setImageResource(R.drawable.ca);break;

            case "h6.0": card.setImageResource(R.drawable.h6);break;
            case "h7.0": card.setImageResource(R.drawable.h7);break;
            case "h8.0": card.setImageResource(R.drawable.h8);break;
            case "h9.0": card.setImageResource(R.drawable.h9);break;
            case "h10.0":card.setImageResource(R.drawable.h10);break;
            case "hj": card.setImageResource(R.drawable.hj);break;
            case "hq": card.setImageResource(R.drawable.hq);break;
            case "hk": card.setImageResource(R.drawable.hk);break;
            case "ha": card.setImageResource(R.drawable.ha);break;

            case "s6.0": card.setImageResource(R.drawable.s6);break;
            case "s7.0": card.setImageResource(R.drawable.s7);break;
            case "s8.0": card.setImageResource(R.drawable.s8);break;
            case "s9.0": card.setImageResource(R.drawable.s9);break;
            case "s10.0":card.setImageResource(R.drawable.s10);break;
            case "sj": card.setImageResource(R.drawable.sj);break;
            case "sq": card.setImageResource(R.drawable.sq);break;
            case "sk": card.setImageResource(R.drawable.sk);break;
            case "sa": card.setImageResource(R.drawable.sa);break;
        }
        card.setTag(cardsClasses);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.CardClick(v);
            }
        });
    }


}
