package com.example.servidores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class crearcuarto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearcuarto);
    }

    public void guardar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}