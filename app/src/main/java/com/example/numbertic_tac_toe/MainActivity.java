package com.example.numbertic_tac_toe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.color.DynamicColors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicColors.applyToActivityIfAvailable(this);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);
    }

    public void createPvP(View view) {
        Intent intent = new Intent(this, PvpActivity.class);
        startActivity(intent);
    }

    public void createPvE(View view) {
        Intent intent = new Intent(this, PveActivity.class);
        startActivity(intent);
    }

    public void createInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}