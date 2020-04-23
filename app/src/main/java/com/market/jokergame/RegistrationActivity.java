package com.market.jokergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ((Button)findViewById(R.id.regBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick=((TextView)findViewById(R.id.nick_et)).getText().toString();
                String log=((TextView)findViewById(R.id.login_et)).getText().toString();
                String pass=((TextView)findViewById(R.id.password_et)).getText().toString();
                String pass2=((TextView)findViewById(R.id.password_et2)).getText().toString();
                if((!nick.equals(""))&&(!log.equals(""))&&(!pass.equals(""))&&(!pass2.equals(""))){
                    if(pass.equals(pass2)){
                        try {
                            String server_answer = new ThreadRequest().execute("registration.php?N="+nick+"&L="+log+"&P="+pass).get();
                            if(server_answer.equals("error")){
                                Toast.makeText(RegistrationActivity.this,"Такой логин или имя уже заняты!",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegistrationActivity.this, "Регистрация прошла успешно!)", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                intent.putExtra("Param","1");
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            Toast.makeText(RegistrationActivity.this,"Проблема при подключении к серверу!!!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(RegistrationActivity.this,"Пароли должны совпадать!!!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistrationActivity.this,"Все поля дожны быть заполнены!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
