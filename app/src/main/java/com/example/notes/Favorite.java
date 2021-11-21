package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {

    RecyclerView recyclerVieww ;
    ArrayList<String> str ;
    ArrayList<String> araay2 ;
    ArrayList<String> array  ;
    MyAdapter2 adapter2 ;
    int s;
    String ba ;
    CollapsingToolbarLayout collapsingToolbarLayout ;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbarrrrrr);

        array = new ArrayList<>(); araay2 = new ArrayList<>(); str = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nestedd);

      //  getWindow().setNavigationBarDividerColor(ContextCompat.getColor(Favorite.this, R.color.good5));

        SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);
        s = Integer.parseInt(shrd .getString("countedd" , "0"));

        for (int x = 1 ; x <= s ; ++x){
            str . add(String.valueOf(x));
            array.add(shrd .getString("favorite"+x , "aa")) ;
            araay2.add(shrd .getString("favoritee"+x , "aa")) ;
        }
        send_to_Adapter();

        swith();
}

    public void send_to_Adapter (){

        recyclerVieww.setLayoutManager(new LinearLayoutManager(this ));
        adapter2 = new MyAdapter2 (this , array , araay2 , str, new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String details , String lock) {
            }
        });

        recyclerVieww.setAdapter(adapter2);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull  RecyclerView.ViewHolder viewHolder, int direction) {
                s -- ;
                SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd .edit();
                editor . putString("countedd" , String.valueOf(s));

                int vv = viewHolder.getAdapterPosition();

                editor.remove("favorite"+(vv+1));
                editor.remove("favoritee"+(vv+1));

                for (int c=(vv+1); c<= (s+1)  ; ++c ) {

                    String  num3  = shrd .getString("favorite" +(c+1),"aa");
                    String  num4 = shrd .getString("favoritee"+(c+1),"aa");

                    editor . putString("favorite" +(c), num3);
                    editor . putString("favoritee"+(c), num4);

                }

                editor.apply();

                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();

                Toast.makeText(Favorite.this , "تم الحزف" , Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerVieww);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull  RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                s -- ;

                SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd .edit();
                editor . putString("countedd" , String.valueOf(s));

                int vv = viewHolder.getAdapterPosition();

                editor.remove("favorite"+(vv+1));
                editor.remove("favoritee"+(vv+1));

                for (int c=(vv+1); c<= (s+1)  ; ++c ) {

                    String  num3  = shrd .getString("favorite" +(c+1),"aa");
                    String  num4 = shrd .getString("favoritee"+(c+1),"aa");

                    editor . putString("favorite" +(c), num3);
                    editor . putString("favoritee"+(c), num4);

                }

                editor.apply();
                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();

            }
        }).attachToRecyclerView(recyclerVieww);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void swith  (){

        SharedPreferences   shrdd = getSharedPreferences("trans file", Context.MODE_PRIVATE);

        ba = shrdd .getString("theme_number" , "normal");

        switch (ba){

            case "one"     : Set_Activity_color("#7E91AF"); break;
            case "tow"     : Set_Activity_color("#141E37"); break;
            case "three"   : Set_Activity_color("#4563C7"); break;
            case "normal"  : Set_Activity_color("normal") ; break;
            case "materal" : Set_Activity_color("materal"); break;
            case "image"   : Set_Activity_color("image")  ; break;
            case "day"     : Set_Activity_color("day")    ; break;
            case "night"   : Set_Activity_color("night")  ; break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    public void Set_Activity_color (String color){

        if (color.equals("normal")) {

            getWindow().setStatusBarColor(ContextCompat.getColor(Favorite.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Favorite.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Favorite.this, R.color.white100));
            //   vew.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back2));
        }else if (color.equals("materal")){

            getWindow().setStatusBarColor(Color.parseColor("#7B8DAB"));
            View vew = this.getWindow().getDecorView();
            vew.setBackground(ContextCompat.getDrawable(Favorite.this, R.drawable.packgroundwall));
            //  navigationView.setBackgroundColor(ContextCompat.getColor(MainActivity.this , R.color.good3));
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#7B8DAB"));
            //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        } else if (color.equals("image")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setStatusBarColor(ContextCompat.getColor(Favorite.this, R.color.facecolor));
            View veww = this.getWindow().getDecorView();
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Favorite.this, R.color.facecolor));
            veww.setBackgroundColor(ContextCompat.getColor(Favorite.this, R.color.facecolor));
        }else if (color.equals("night")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            getWindow().setStatusBarColor(ContextCompat.getColor(Favorite.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Favorite.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Favorite.this, R.color.white100));
        }else if (color.equals("day")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setStatusBarColor(ContextCompat.getColor(Favorite.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Favorite.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Favorite.this, R.color.white100));
        }

        else{

            getWindow().setStatusBarColor(Color.parseColor(color));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(Color.parseColor(color));
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor(color));
        }
    }






}