package com.example.servsec.servidores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servidores.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 4000; // 4 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (hasToken()) {

                    Intent mainIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(mainIntent);
                } else {

                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }

                finish();
            }
        }, SPLASH_TIMEOUT);
    }

    private boolean hasToken() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("token", null);
        return token != null && !token.isEmpty();
    }
}
