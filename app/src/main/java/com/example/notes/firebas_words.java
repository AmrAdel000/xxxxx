package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class firebas_words extends AppCompatActivity {

    RecyclerView recyclerVieww;
    ArrayList<String> str;
    ArrayList<String> araay2;
    ArrayList<String> array;
    MyAdapter2 adapter2;
    RecyclerView.LayoutManager layoutmangerr ;
    int s;
    String ba;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebas_words);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbarr);

        getWindow().setStatusBarColor(ContextCompat.getColor(firebas_words.this, R.color.white200));

        array = new ArrayList<>();
        araay2 = new ArrayList<>();
        str = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nesteddfdf);
        recyclerVieww.setHasFixedSize(true);

        get_firebase_words_1();
        get_firebase_words_2();

    }
    public void get_firebase_words_1 (){

        FirebaseDatabase.getInstance().getReference("Words").child("English").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    array.add(snapshot1.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    public void get_firebase_words_2 (){

        FirebaseDatabase.getInstance().getReference("Words").child("Arabic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    araay2.add(snapshot1.getValue().toString());
                }
                adapterr();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void adapterr() {

        recyclerVieww.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new MyAdapter2(this, array, araay2, str, new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String details, String lock) {

                Toast.makeText(firebas_words.this, "عايز اي", Toast.LENGTH_SHORT).show();

            }
        });

        recyclerVieww.setAdapter(adapter2);
        layoutmangerr = new GridLayoutManager(this, 2);
        recyclerVieww.setLayoutManager(layoutmangerr);
    }
}