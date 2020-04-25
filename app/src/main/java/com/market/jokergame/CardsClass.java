package com.market.jokergame;

public class CardsClass {
    private String suit;
    private Object weight;

    public CardsClass(String suit, Object weight) {
        this.suit = suit;
        this.weight = weight;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

}
