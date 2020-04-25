package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Integer step=0;
    private Integer id=0;
    private Integer idRoom=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intents = this.getIntent();
        id = Integer.parseInt( intents.getSerializableExtra("id").toString());
        idRoom = Integer.parseInt( intents.getSerializableExtra("idRoom").toString());
        try {
            String server_answer = new ThreadRequest().execute("next_step.php?id="+idRoom+"&Round="+step).get();
            server_answer=new ThreadRequest().execute("get_cards.php?id="+idRoom).get();
            //playerAdapter=null;
            //playerListClass=deserializePlayerList(server_answer);
            //players=findViewById(R.id.ListPlayer);
            //RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(GameActivity.this, LinearLayoutManager.VERTICAL, false);
            //players.setLayoutManager(layoutManager1);
            //playerAdapter= new PlayerAdapter(playerListClass.getNick());
            //players.setAdapter(playerAdapter);
            //((TextView)findViewById(R.id.roomName)).setText(playerListClass.getName());
        } catch (Exception e) {
            Toast.makeText(GameActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
