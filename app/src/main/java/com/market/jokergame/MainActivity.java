package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences myPrefereces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intents = this.getIntent();
        String s = (String) intents.getSerializableExtra("Param");
        myPrefereces = this.getSharedPreferences("uses", Context.MODE_PRIVATE);
        String password = myPrefereces.getString("password", "");
        String login = myPrefereces.getString("login", "");
        if((!login.equals(""))&&(!password.equals(""))&&(s==null)){
            try {
                String server_answer = new ThreadRequest().execute("authorization.php?L="+login+"&P="+password).get();
                if((server_answer.equals("error"))||(server_answer.equals(""))){
                    Toast.makeText(MainActivity.this,"Видимо ваш пароль изменился!!!",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, RoomListActivity.class);
                    intent.putExtra("id", server_answer);
                    startActivity(intent);
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }else{
            if(s!=null){
                myPrefereces.edit().remove("login").apply();
                myPrefereces.edit().remove("password").apply();
            }
        }

        ((Button)findViewById(R.id.authBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText log=findViewById(R.id.login_et);
                EditText pass=findViewById(R.id.password_et);
                if((!log.getText().toString().equals(""))&&(!pass.getText().toString().equals(""))){
                    try {
                        String server_answer = new ThreadRequest().execute("authorization.php?L="+log.getText().toString()+"&P="+pass.getText().toString()).get();
                        if((server_answer.equals("error"))||(server_answer.equals(""))){
                            Toast.makeText(MainActivity.this,"Не верный логин или пароль!!!",Toast.LENGTH_SHORT).show();
                        }else{
                            SharedPreferences.Editor editor=myPrefereces.edit();
                            editor.putString("login",log.getText().toString());
                            editor.putString("password",pass.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(MainActivity.this, RoomListActivity.class);
                            intent.putExtra("id", server_answer);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Вы не ввели логин или пароль!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((Button)findViewById(R.id.regBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}
