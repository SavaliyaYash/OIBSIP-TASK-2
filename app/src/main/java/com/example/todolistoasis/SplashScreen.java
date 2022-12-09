package com.example.todolistoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Half;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag", false);

                Intent inext;
                if(check){
                    inext = new Intent(SplashScreen.this, MainActivity.class);
                }else {
                    inext = new Intent(SplashScreen.this, LoginAct.class);
                }
                startActivity(inext);
                finish();
            }
        },3000);
    }
}