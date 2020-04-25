package com.market.jokergame;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardsPlayerAdapter extends RecyclerView.Adapter<CardsPlayerViewHolder>  {


    private ArrayList<CardsClass> cardsClasses;

    public CardsPlayerAdapter(ArrayList<CardsClass> cardsClasses) {
        this.cardsClasses = cardsClasses;
    }


    @NonNull
    @Override
    public CardsPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cardview,parent,false);
        return new CardsPlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsPlayerViewHolder holder, int position) {
        holder.bindData(cardsClasses.get(position));
    }

    @Override
    public int getItemCount() {
        return this.cardsClasses.size();
    }
}
