package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomListActivity extends AppCompatActivity {

    private static Integer id=-1;
    private RoomListClass roomListClass;
    private RecyclerView room;
    private RoomAdapter roomAdapter;
    private Boolean f=true;
    private static Context context;
    private static Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        context=RoomListActivity.this;
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
                                timer.cancel();
                                Intent intent = new Intent(RoomListActivity.this, RoomActivity.class);
                                intent.putExtra("idRoom", server_answer);
                                intent.putExtra("id", id);
                                startActivity(intent);
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
        ((EditText)findViewById(R.id.findEt)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0) {
                    timer.cancel();
                    int i = 0;
                    while (i < roomListClass.getData().size()) {
                        Pattern pattern = Pattern.compile("(\\w*)"+s.toString().toLowerCase()+"(\\w*)");
                        Matcher matcher = pattern.matcher(roomListClass.getData().get(i).getName().toLowerCase());
                        if (!matcher.find()) {
                            roomListClass.getData().remove(i);
                            roomAdapter.notifyDataSetChanged();
                        } else {
                            i++;
                        }
                    }
                }else{
                    setRoomTimer();
                }
            }
        });
    }

    public static void ConnectRoom(View v) {
        try {
            String server_answer = new ThreadRequest().execute("connect_room.php?id="+v.getTag().toString()+"&U="+id.toString()).get();
            if(server_answer.equals("error")){
                Toast.makeText(RoomListActivity.context,"Такая комната больше не существует!!!",Toast.LENGTH_SHORT).show();
            }else{
                timer.cancel();
                Intent intent = new Intent(RoomListActivity.context, RoomActivity.class);
                intent.putExtra("idRoom", v.getTag().toString());
                intent.putExtra("id", id);
                RoomListActivity.context.startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(RoomListActivity.context,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void setRoomTimer() {
        timer = new Timer();
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
                            if((roomListClass!=null)&&(roomListClass.getData().size()>0)){
                                f=true;
                                for(int i=0;i<roomListClass.getData().size();) {
                                    if (roomListClass.getData().get(i).getCount()>=4){
                                        roomListClass.getData().remove(i);
                                    }else {
                                        i++;
                                    }
                                }
                            }else{
                                if(f) {
                                    Toast.makeText(RoomListActivity.this, "Комнат нет, создайте свою!", Toast.LENGTH_SHORT).show();
                                    f = false;
                                }
                            }
                            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(RoomListActivity.this, LinearLayoutManager.VERTICAL, false);
                            room.setLayoutManager(layoutManager1);
                            roomAdapter= new RoomAdapter(roomListClass.getData());
                            room.setAdapter(roomAdapter);
                        } catch (Exception e) {
                            Toast.makeText(RoomListActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 200, 1000);
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
