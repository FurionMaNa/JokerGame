package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private Integer step=2;
    private static Integer id=0;
    private static Integer idRoom=0;
    private GameUserClass gameUserClass;
    private CardsPlayerAdapter cardsPlayerAdapter;
    private RecyclerView cardsUser;
    private static Context context;
    private static ImageView imgBottom;
    private static ImageView imgLeft;
    private static ImageView imgRight;
    private static ImageView imgTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        context=GameActivity.this;
        imgBottom=findViewById(R.id.bottom_card);
        imgLeft=findViewById(R.id.left_card);
        imgRight=findViewById(R.id.right_card);
        imgTop=findViewById(R.id.top_card);
        Intent intents = this.getIntent();
        id = Integer.parseInt( intents.getSerializableExtra("id").toString());
        idRoom = Integer.parseInt( intents.getSerializableExtra("idRoom").toString());
        try {
            String server_answer = new ThreadRequest().execute("next_step.php?id="+idRoom+"&Round="+step).get();
            setCardsUserTimer();
            setCardsTableTimer();
            //((TextView)findViewById(R.id.roomName)).setText(playerListClass.getName());
        } catch (Exception e) {
            Toast.makeText(GameActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private static void CardsLoad(ImageView img,CardsClass cardsClass){
        switch (cardsClass.getSuit()+String.valueOf(cardsClass.getWeight().toString())){
            case "d6.0": img.setImageResource(R.drawable.d6);break;
            case "d7.0": img.setImageResource(R.drawable.d7);break;
            case "d8.0": img.setImageResource(R.drawable.d8);break;
            case "d9.0": img.setImageResource(R.drawable.d9);break;
            case "d10.0":img.setImageResource(R.drawable.d10);break;
            case "dj":   img.setImageResource(R.drawable.dj);break;
            case "dq":   img.setImageResource(R.drawable.dq);break;
            case "dk":   img.setImageResource(R.drawable.dk);break;
            case "da":   img.setImageResource(R.drawable.da);break;
            case "c6.0": img.setImageResource(R.drawable.c6);break;
            case "c7.0": img.setImageResource(R.drawable.c7);break;
            case "c8.0": img.setImageResource(R.drawable.c8);break;
            case "c9.0": img.setImageResource(R.drawable.c9);break;
            case "c10.0":img.setImageResource(R.drawable.c10);break;
            case "cj":   img.setImageResource(R.drawable.cj);break;
            case "cq":   img.setImageResource(R.drawable.cq);break;
            case "ck":   img.setImageResource(R.drawable.ck);break;
            case "ca":   img.setImageResource(R.drawable.ca);break;
            case "h6.0": img.setImageResource(R.drawable.h6);break;
            case "h7.0": img.setImageResource(R.drawable.h7);break;
            case "h8.0": img.setImageResource(R.drawable.h8);break;
            case "h9.0": img.setImageResource(R.drawable.h9);break;
            case "h10.0":img.setImageResource(R.drawable.h10);break;
            case "hj":   img.setImageResource(R.drawable.hj);break;
            case "hq":   img.setImageResource(R.drawable.hq);break;
            case "hk":   img.setImageResource(R.drawable.hk);break;
            case "ha":   img.setImageResource(R.drawable.ha);break;
            case "s6.0": img.setImageResource(R.drawable.s6);break;
            case "s7.0": img.setImageResource(R.drawable.s7);break;
            case "s8.0": img.setImageResource(R.drawable.s8);break;
            case "s9.0": img.setImageResource(R.drawable.s9);break;
            case "s10.0":img.setImageResource(R.drawable.s10);break;
            case "sj":   img.setImageResource(R.drawable.sj);break;
            case "sq":   img.setImageResource(R.drawable.sq);break;
            case "sk":   img.setImageResource(R.drawable.sk);break;
            case "sa":   img.setImageResource(R.drawable.sa);break;
        }
    }

    private void setCardsTableTimer() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String server_answer = new ThreadRequest().execute("get_table.php?id="+idRoom).get();
                            GameUserClass gameUserClass2=deserializeGameCardsList(server_answer);
                            if(gameUserClass2.getFirstId()==id){
                                if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                                    CardsLoad(imgBottom,gameUserClass2.getFirstPlayer().get(0));
                                }
                                if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                                    CardsLoad(imgLeft,gameUserClass2.getSecondPlayer().get(0));
                                }
                                if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                                    CardsLoad(imgTop,gameUserClass2.getThirdPlayer().get(0));
                                }
                                if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                                    CardsLoad(imgRight,gameUserClass2.getForthPlayer().get(0));
                                }
                            }else{
                                if(gameUserClass2.getSecondId()==id){
                                    if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                                        CardsLoad(imgRight,gameUserClass2.getFirstPlayer().get(0));
                                    }
                                    if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                                        CardsLoad(imgBottom,gameUserClass2.getSecondPlayer().get(0));
                                    }
                                    if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                                        CardsLoad(imgLeft,gameUserClass2.getThirdPlayer().get(0));
                                    }
                                    if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                                        CardsLoad(imgTop,gameUserClass2.getForthPlayer().get(0));
                                    }
                                }else{
                                    if(gameUserClass2.getThirdId()==id){
                                        if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                                            CardsLoad(imgTop,gameUserClass2.getFirstPlayer().get(0));
                                        }
                                        if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                                            CardsLoad(imgRight,gameUserClass2.getSecondPlayer().get(0));
                                        }
                                        if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                                            CardsLoad(imgBottom,gameUserClass2.getThirdPlayer().get(0));
                                        }
                                        if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                                            CardsLoad(imgLeft,gameUserClass2.getForthPlayer().get(0));
                                        }
                                    }else{
                                        if(gameUserClass2.getForthId()==id){
                                            if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                                                CardsLoad(imgLeft,gameUserClass2.getFirstPlayer().get(0));
                                            }
                                            if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                                                CardsLoad(imgTop,gameUserClass2.getSecondPlayer().get(0));
                                            }
                                            if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                                                CardsLoad(imgRight,gameUserClass2.getThirdPlayer().get(0));
                                            }
                                            if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                                                CardsLoad(imgBottom,gameUserClass2.getForthPlayer().get(0));
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(GameActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 600, 600);
    }

    public static void CardClick(View v) {
        try {
            String server_answer = new ThreadRequest().execute("get_table.php?id="+idRoom).get();
            GameUserClass gameUserClass2=deserializeGameCardsList(server_answer);
            if(gameUserClass2.getFirstId()==id){
                if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                    CardsLoad(imgBottom,gameUserClass2.getFirstPlayer().get(0));
                }
                if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                    CardsLoad(imgLeft,gameUserClass2.getSecondPlayer().get(0));
                }
                if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                    CardsLoad(imgTop,gameUserClass2.getThirdPlayer().get(0));
                }
                if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                    CardsLoad(imgRight,gameUserClass2.getForthPlayer().get(0));
                }
            }else{
                if(gameUserClass2.getSecondId()==id){
                    if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                        CardsLoad(imgRight,gameUserClass2.getFirstPlayer().get(0));
                    }
                    if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                        CardsLoad(imgBottom,gameUserClass2.getSecondPlayer().get(0));
                    }
                    if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                        CardsLoad(imgLeft,gameUserClass2.getThirdPlayer().get(0));
                    }
                    if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                        CardsLoad(imgTop,gameUserClass2.getForthPlayer().get(0));
                    }
                }else{
                    if(gameUserClass2.getThirdId()==id){
                        if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                            CardsLoad(imgTop,gameUserClass2.getFirstPlayer().get(0));
                        }
                        if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                            CardsLoad(imgRight,gameUserClass2.getSecondPlayer().get(0));
                        }
                        if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                            CardsLoad(imgBottom,gameUserClass2.getThirdPlayer().get(0));
                        }
                        if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                            CardsLoad(imgLeft,gameUserClass2.getForthPlayer().get(0));
                        }
                    }else{
                        if(gameUserClass2.getForthId()==id){
                            if((gameUserClass2.getFirstPlayer()!=null)&&(gameUserClass2.getFirstPlayer().size()>0)){
                                CardsLoad(imgLeft,gameUserClass2.getFirstPlayer().get(0));
                            }
                            if((gameUserClass2.getSecondPlayer()!=null)&&(gameUserClass2.getSecondPlayer().size()>0)){
                                CardsLoad(imgTop,gameUserClass2.getSecondPlayer().get(0));
                            }
                            if((gameUserClass2.getThirdPlayer()!=null)&&(gameUserClass2.getThirdPlayer().size()>0)){
                                CardsLoad(imgRight,gameUserClass2.getThirdPlayer().get(0));
                            }
                            if((gameUserClass2.getForthPlayer()!=null)&&(gameUserClass2.getForthPlayer().size()>0)){
                                CardsLoad(imgBottom,gameUserClass2.getForthPlayer().get(0));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(context,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
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
