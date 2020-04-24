package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

public class RoomActivity extends AppCompatActivity {

    private PlayerListClass playerListClass;
    private RecyclerView players;
    private PlayerAdapter playerAdapter;
    private Integer id;
    private Integer idRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Intent intents = this.getIntent();
        id = Integer.parseInt( intents.getSerializableExtra("id").toString());
        idRoom = Integer.parseInt( intents.getSerializableExtra("idRoom").toString());
        setRoomInfoTimer();
        ((Button)findViewById(R.id.leaveRoom)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String server_answer = new ThreadRequest().execute("leave_room.php?id="+playerListClass.getId().toString()+"&U="+id.toString()).get();
                    Intent intent = new Intent(RoomActivity.this, RoomListActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(RoomActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void setRoomInfoTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String server_answer = new ThreadRequest().execute("player_in_room_list.php?id="+idRoom).get();
                            playerAdapter=null;
                            playerListClass=deserializePlayerList(server_answer);
                            players=findViewById(R.id.ListPlayer);
                            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(RoomActivity.this, LinearLayoutManager.VERTICAL, false);
                            players.setLayoutManager(layoutManager1);
                            playerAdapter= new PlayerAdapter(playerListClass.getNick());
                            players.setAdapter(playerAdapter);
                            ((TextView)findViewById(R.id.roomName)).setText(playerListClass.getName());
                        } catch (Exception e) {
                            Toast.makeText(RoomActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 600, 600);
    }

    private static PlayerListClass deserializePlayerList(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString, PlayerListClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
