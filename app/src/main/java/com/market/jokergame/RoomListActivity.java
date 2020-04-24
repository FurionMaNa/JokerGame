package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RoomListActivity extends AppCompatActivity {

    private Integer id=-1;
    private RoomListClass roomListClass;
    private RecyclerView room;
    private RoomAdapter roomAdapter;
    private Boolean f=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Intent intents = this.getIntent();
        id = Integer.parseInt( intents.getSerializableExtra("id").toString());
        setRoomTimer();
        ((Button)findViewById(R.id.createRoom)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RoomListActivity.this);
                alert.setTitle("Создание комнаты");
                alert.setMessage("Введите название");
                final EditText input = new EditText (RoomListActivity.this);
                alert.setView(input);

                alert.setPositiveButton("Создать", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {
                            String server_answer = new ThreadRequest().execute("create_room.php?N="+input.getText().toString()+"&U="+id.toString()).get();
                            if((server_answer.equals("error"))||(server_answer.equals(""))){
                                Toast.makeText(RoomListActivity.this,"Такая комната существует!!!",Toast.LENGTH_SHORT).show();
                            }else{
                                //Вход в комнату
                            }
                        } catch (Exception e) {
                            Toast.makeText(RoomListActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
                alert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) { }
                });
                alert.show();

            }
        });
    }

    private void setRoomTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String server_answer = new ThreadRequest().execute("room_list.php").get();
                            roomAdapter=null;
                            roomListClass=deserializeRoomList(server_answer);
                            room=findViewById(R.id.ListRoom);
                            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(RoomListActivity.this, LinearLayoutManager.VERTICAL, false);
                            room.setLayoutManager(layoutManager1);
                            roomAdapter= new RoomAdapter(roomListClass.getData());
                            room.setAdapter(roomAdapter);
                            if((roomListClass!=null)&&(roomListClass.getData().size()>0)){
                                f=true;
                            }else{
                                if(f) {
                                    Toast.makeText(RoomListActivity.this, "Комнат нет, создайте свою!", Toast.LENGTH_SHORT).show();
                                    f = false;
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(RoomListActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

    private static RoomListClass deserializeRoomList(String JSonString)  {
        Gson gson =new Gson();
        try {
            return gson.fromJson(JSonString, RoomListClass.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
