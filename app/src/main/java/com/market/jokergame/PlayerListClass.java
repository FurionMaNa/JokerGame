package com.market.jokergame;

import java.util.ArrayList;

public class PlayerListClass {

    private String name;
    private Integer id;
    private ArrayList<PlayerClass> nick;

    public PlayerListClass(String name, Integer id, ArrayList<PlayerClass> nick) {
        this.name = name;
        this.id = id;
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<PlayerClass> getNick() {
        return nick;
    }

    public void setNick(ArrayList<PlayerClass> nick) {
        this.nick = nick;
    }
    
}
