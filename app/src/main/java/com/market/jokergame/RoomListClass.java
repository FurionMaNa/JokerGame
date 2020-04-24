package com.market.jokergame;

import java.util.ArrayList;

public class RoomListClass {
    private ArrayList<RoomClass> data;

    public RoomListClass(ArrayList<RoomClass> data) {
        this.data = data;
    }

    public ArrayList<RoomClass> getData() {
        return data;
    }

    public void setData(ArrayList<RoomClass> data) {
        this.data = data;
    }
}
