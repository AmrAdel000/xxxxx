package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class theme extends AppCompatActivity {

    String s ;
    SharedPreferences shrd ;
    SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);

        swith();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public void swith  (){


        s = shrd .getString("theme_number" , "a");

        switch (s){

            case "one"   : Set_Activity_color("#7E91AF"); break;
            case "tow"   : Set_Activity_color("#141E37"); break;
            case "three" : Set_Activity_color("#4563C7"); break;
            case "materal" :
                getWindow().setStatusBarColor(ContextCompat.getColor(theme.this, R.color.good));
                View vew = this.getWindow().getDecorView();
                vew.setBackground(ContextCompat.getDrawable(theme.this, R.drawable.packgroundwall)); break;
            case "image" :
                getWindow().setStatusBarColor(ContextCompat.getColor(theme.this, R.color.good));
                View veww = this.getWindow().getDecorView();
                veww.setBackground(ContextCompat.getDrawable(theme.this, R.drawable.p)); break;
            case "normal" :
                getWindow().setStatusBarColor(ContextCompat.getColor(theme.this, R.color.white100));
                View vewww = this.getWindow().getDecorView();
                vewww.setBackgroundColor(ContextCompat.getColor(theme.this, R.color.white100));
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    public void Set_Activity_color (String color){

        getWindow().setStatusBarColor(Color.parseColor(color));
        View vew = this.getWindow().getDecorView();
        vew.setBackgroundColor(Color.parseColor(color));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void frist_color(View view) {

        vvvvvvv("one");
        swith();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void soucnd_color(View view) {
        vvvvvvv("tow");
        swith();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void three_color(View view) {
        vvvvvvv("three");
        swith();
    }

    public void vvvvvvv (String number){

        SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd .edit();
        editor . putString("theme_number" ,number);
        editor.apply();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void normar(View view) {
        vvvvvvv("normal");
        swith();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void material_color(View view) {

        vvvvvvv("materal");
        swith();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void image(View view) {

        vvvvvvv("image");
        swith();
    }
}