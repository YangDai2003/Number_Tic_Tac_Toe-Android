package com.example.numbertic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicColors.applyToActivityIfAvailable(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        setContentView(R.layout.activity_info);
    }
}