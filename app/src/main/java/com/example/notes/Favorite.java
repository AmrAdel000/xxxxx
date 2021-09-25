package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {

    Button button ;
    String namec ;
    RecyclerView recyclerVieww ;
    ArrayList<String> str ;
    ArrayList<String> araay2 ;
    ArrayList<String> array  ;

    EditText textset ;
    EditText textget ;
    MyAdapter2 adapter2 ;
    int s;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);


        button = findViewById(R.id.button);
        array = new ArrayList<>(); araay2 = new ArrayList<>(); str = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nestedd);

        textget=findViewById(R.id.texevew1);
        textset=findViewById(R.id.text11);

      //  getWindow().setStatusBarColor(ContextCompat.getColor(Favorite.this, R.color.good5));
      //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        s = 0 ;

        SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);
        s = Integer.parseInt(shrd .getString("countedd" , ""));



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslateAPI translateAPI = new TranslateAPI(
                        Language.AUTO_DETECT,
                        Language.ARABIC,
                        textget.getText().toString()
                );

                translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                    @Override
                    public void onSuccess(String translatedText) {

                        textset.setText(translatedText);
                    }
                    @Override
                    public void onFailure(String ErrorText) {
                        Log.d("Error" , ErrorText);
                    }
                });
            }
        });

        for (int x = 1 ; x <= s ; ++x){

            str . add(String.valueOf(x));

            array.add(shrd .getString("Favorite"+x , "")) ;
            araay2.add(shrd .getString("favoritee"+x , "")) ;

        }
        send_to_Adapter();

}
    public void btn_savee(View view) {

        s ++;

        SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shrd .edit();
        editor . putString("countedd" , String.valueOf(s));

        editor . putString("world"+ s ,textget.getText().toString());
        editor . putString("worldd"+s ,textset.getText().toString());

        editor.apply();

        if (s > array.size()) {

            for (int x = 2; x <= s; ++x) {

                array.remove(0);
                araay2.remove(0);
            }

            for (int x=1; x <= s; ++x) {

                String  num = shrd .getString("Favorite"+x ,"");
                String  num2 = shrd .getString("favoritee"+x ,"");

                array.add(num);
                araay2.add(num2);
            }
            send_to_Adapter();
        }
    }

    public void send_to_Adapter (){

        recyclerVieww.setLayoutManager(new LinearLayoutManager(this ));
        adapter2 = new MyAdapter2 (this , array , araay2 , str, new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String details) {
                namec = "note" + details;
                SharedPreferences shrd = getSharedPreferences("trans file", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();
                editor.putString("namec", namec);
                editor.apply();
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

                editor.remove("Favorite"+(vv+1));
                editor.remove("favoritee"+(vv+1));

                for (int c=(vv+1); c<= (s+1)  ; ++c ) {

                    String  num3  = shrd .getString("Favorite" +(c+1),"");
                    String  num4 = shrd .getString("favoritee"+(c+1),"");

                    editor . putString("Favorite" +(c), num3);
                    editor . putString("favoritee"+(c), num4);

                }

                editor.apply();

                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();
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

                editor.remove("Favorite"+(vv+1));
                editor.remove("favoritee"+(vv+1));

                for (int c=(vv+1); c<= (s+1)  ; ++c ) {

                    String  num3  = shrd .getString("Favorite" +(c+1),"");
                    String  num4 = shrd .getString("favoritee"+(c+1),"");

                    editor . putString("Favorite" +(c), num3);
                    editor . putString("favoritee"+(c), num4);

                }

                editor.apply();

                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();

            }
        }).attachToRecyclerView(recyclerVieww);

    }

}