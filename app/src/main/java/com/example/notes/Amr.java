package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class Amr extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amr);


        getWindow().setStatusBarColor(ContextCompat.getColor(Amr.this, R.color.white));
        View vew = this.getWindow().getDecorView();
        vew.setBackgroundColor(ContextCompat.getColor(Amr.this, R.color.white));



    }
}