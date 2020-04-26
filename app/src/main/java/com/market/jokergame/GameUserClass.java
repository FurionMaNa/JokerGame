package com.market.jokergame;

import java.util.ArrayList;

public class GameUserClass {

    private Integer move;
    private Integer round;
    private CardsClass trump;
    private Integer firstId;
    private Integer secondId;
    private Integer thirdId;
    private Integer forthId;
    private ArrayList<CardsClass> firstPlayer;
    private ArrayList<CardsClass> secondPlayer;
    private ArrayList<CardsClass> thirdPlayer;
    private ArrayList<CardsClass> forthPlayer;

    public GameUserClass(Integer move, Integer round, CardsClass trump, Integer firstId, Integer secondId, Integer thirdId, Integer forthId, ArrayList<CardsClass> firstPlayer, ArrayList<CardsClass> secondPlayer, ArrayList<CardsClass> thirdPlayer, ArrayList<CardsClass> forthPlayer) {
        this.move = move;
        this.round = round;
        this.trump = trump;
        this.firstId = firstId;
        this.secondId = secondId;
        this.thirdId = thirdId;
        this.forthId = forthId;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.thirdPlayer = thirdPlayer;
        this.forthPlayer = forthPlayer;
    }

    public Integer getMove() {
        return move;
    }

    public void setMove(Integer move) {
        this.move = move;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public CardsClass getTrump() {
        return trump;
    }

    public void setTrump(CardsClass trump) {
        this.trump = trump;
    }

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public Integer getSecondId() {
        return secondId;
    }

    public void setSecondId(Integer secondId) {
        this.secondId = secondId;
    }

    public Integer getThirdId() {
        return thirdId;
    }

    public void setThirdId(Integer thirdId) {
        this.thirdId = thirdId;
    }

    public Integer getForthId() {
        return forthId;
    }

    public void setForthId(Integer forthId) {
        this.forthId = forthId;
    }

    public ArrayList<CardsClass> getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(ArrayList<CardsClass> firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public ArrayList<CardsClass> getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(ArrayList<CardsClass> secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public ArrayList<CardsClass> getThirdPlayer() {
        return thirdPlayer;
    }

    public void setThirdPlayer(ArrayList<CardsClass> thirdPlayer) {
        this.thirdPlayer = thirdPlayer;
    }

    public ArrayList<CardsClass> getForthPlayer() {
        return forthPlayer;
    }

    public void setForthPlayer(ArrayList<CardsClass> forthPlayer) {
        this.forthPlayer = forthPlayer;
    }
}
