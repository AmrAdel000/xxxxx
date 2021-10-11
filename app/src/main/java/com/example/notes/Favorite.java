package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        array = new ArrayList<>(); araay2 = new ArrayList<>(); str = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nestedd);

      //  getWindow().setNavigationBarDividerColor(ContextCompat.getColor(Favorite.this, R.color.good5));

        Set_Activity_color("#7E91AF");



        SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);
        s = Integer.parseInt(shrd .getString("countedd" , ""));


        for (int x = 1 ; x <= s ; ++x){

            str . add(String.valueOf(x));
            array.add(shrd .getString("favorite"+x , "")) ;
            araay2.add(shrd .getString("favoritee"+x , "")) ;

        }
        send_to_Adapter();
}

    public void send_to_Adapter (){

        recyclerVieww.setLayoutManager(new LinearLayoutManager(this ));
        adapter2 = new MyAdapter2 (this , array , araay2 , str, new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String details) {

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

                    String  num3  = shrd .getString("favorite" +(c+1),"");
                    String  num4 = shrd .getString("favoritee"+(c+1),"");

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

                    String  num3  = shrd .getString("favorite" +(c+1),"");
                    String  num4 = shrd .getString("favoritee"+(c+1),"");

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
    @SuppressLint("ResourceAsColor")
    public void Set_Activity_color (String color){

        getWindow().setStatusBarColor(Color.parseColor(color));
        View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(Color.parseColor(color));

    }


}