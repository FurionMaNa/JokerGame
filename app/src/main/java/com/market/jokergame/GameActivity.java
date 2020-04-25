package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private Integer step=0;
    private Integer id=0;
    private Integer idRoom=0;
    private GameUserClass gameUserClass;
    private CardsPlayerAdapter cardsPlayerAdapter;
    private RecyclerView cardsUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intents = this.getIntent();
        id = Integer.parseInt( intents.getSerializableExtra("id").toString());
        idRoom = Integer.parseInt( intents.getSerializableExtra("idRoom").toString());
        try {
            String server_answer = new ThreadRequest().execute("next_step.php?id="+idRoom+"&Round="+step).get();
            setCardsUserTimer();
            //((TextView)findViewById(R.id.roomName)).setText(playerListClass.getName());
        } catch (Exception e) {
            Toast.makeText(GameActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void setCardsUserTimer() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String server_answer=new ThreadRequest().execute("get_cards.php?id="+idRoom).get();
                            cardsPlayerAdapter=null;
                            gameUserClass=deserializeGameCardsList(server_answer);

                            cardsUser=findViewById(R.id.cards_users);
                            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(GameActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            cardsUser.setLayoutManager(layoutManager1);
                            if(gameUserClass.getFirstId()==id){
                                cardsPlayerAdapter= new CardsPlayerAdapter(gameUserClass.getFirstPlayer());
                            }else{
                                if(gameUserClass.getSecondId()==id){
                                    cardsPlayerAdapter= new CardsPlayerAdapter(gameUserClass.getSecondPlayer());
                                }else{
                                    if(gameUserClass.getThirdId()==id){
                                        cardsPlayerAdapter= new CardsPlayerAdapter(gameUserClass.getThirdPlayer());
                                    }else{
                                        if(gameUserClass.getForthId()==id){
                                            cardsPlayerAdapter= new CardsPlayerAdapter(gameUserClass.getForthPlayer());
                                        }
                                    }
                                }
                            }
                            cardsUser.setAdapter(cardsPlayerAdapter);
                        } catch (Exception e) {
                            Toast.makeText(GameActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 600, 600);
    }

    private static GameUserClass deserializeGameCardsList(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString, GameUserClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
