package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class widgetsittings extends AppCompatActivity {


    SharedPreferences shrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgetsittings);

        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);


    }

    public void one(View view) {
        SharedPreferences.Editor editor = shrd.edit();
      shrd.getString("widgetTheme" , "one") ;
      editor.apply();
        Toast.makeText(this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();


    }

    public void tow(View view) {
        SharedPreferences.Editor editor = shrd.edit();
        shrd.getString("widgetTheme" , "tow") ;
        editor.apply();
        Toast.makeText(this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();

    }

    public void three(View view) {
        SharedPreferences.Editor editor = shrd.edit();
        shrd.getString("widgetTheme" , "three") ;
        editor.apply();
        Toast.makeText(this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();

    }

    public void four(View view) {
        SharedPreferences.Editor editor = shrd.edit();
        shrd.getString("widgetTheme" , "four") ;
        editor.apply();
        Toast.makeText(this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();

    }

    public void five(View view) {
        SharedPreferences.Editor editor = shrd.edit();
        shrd.getString("widgetTheme" , "five") ;
        editor.apply();
        Toast.makeText(this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();

    }

    public void six(View view) {
        SharedPreferences.Editor editor = shrd.edit();
        shrd.getString("widgetTheme" , "six") ;
        editor.apply();

        Toast.makeText(this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();

    }
}