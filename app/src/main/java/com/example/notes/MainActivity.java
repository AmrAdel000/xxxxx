package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String namec ;
    RecyclerView recyclerVieww ;
    ArrayList <String> str ;
    ArrayList<String> toks  ;
    ArrayList<String> toks2  ;
    ArrayList<String> toks3  ;
    RecyclerView.LayoutManager  layoutmangerr ;
    TextView itemtext ;
    ArrayList<String> namee ;
    int z , a ;
    TextView text1 ;
    SharedPreferences shrd ;
    String space ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        str = new ArrayList<>(); toks = new ArrayList<>(); toks2 = new ArrayList<>(); toks3 = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nested);
        itemtext=findViewById(R.id.mytext);
        text1=findViewById(R.id.text1);
        namee = new ArrayList<>();
        z=0 ;a=-1;

 getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

          shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);

            final String[] name = {shrd.getString("count", "ON_DATA")};
                z = Integer.parseInt(name[0]);

            if (shrd.getString("page", "").equals("1")) {
                Intent intent = new Intent(this, Translate.class);
                startActivity(intent);
                finish();
            }

            show();
            space = shrd.getString("space", "");

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////
      // يمكن الاستغناء عنه
      MyAdapter myAdapter = new MyAdapter(this, toks2, toks3, toks, str, new MyAdapter.ItemClickListener()  {
          @Override
          public void onItemClick( String details ) {

            namec  = "item"+details ;

              SharedPreferences shrd = getSharedPreferences("save file" , Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = shrd .edit();
              editor . putString("namec", namec );
              editor.apply();

              btn_good();
          }
      });
      recyclerVieww.setAdapter(myAdapter);
      //recyclerVieww.setLayoutManager(new LinearLayoutManager(this ));
      ////////////////////////////////////////////////////////////////
      layoutmangerr = new GridLayoutManager(this, 2);
      recyclerVieww.setLayoutManager(layoutmangerr);

    //  if (getIntent().getBooleanExtra("EXIT" , false)) {

      //    Toast.makeText(MainActivity.this , "تم حفظ الترجمه" , Toast.LENGTH_SHORT).show();
         // finish();
    //  }
                ////////////////////////////////////////////////////////////////////////////////////////////////
        text1.setText ( "الملاحظات | "+Integer.parseInt(String.valueOf(z))) ;

    }

    public void show (){

        for (int x = 0; x <= z; ++x) {

            toks.add("العنوان رقم : " + (x + 1));
            str.add(String.valueOf(x));
            String num = shrd.getString("item" + x, "");
            toks3.add(num);
        }

    }

    @Override
    protected void onResume() {

        final String[] name = {shrd.getString("count", "ON_DATA")};
        z = Integer.parseInt(name[0]);

        if (z > toks.size()) {

            for (int x = 2; x <= z; ++x) {
                toks.remove(0);
                str.remove(0);
                toks3.remove(0);
            }
            for (int x=1; x <= z; ++x) {
                toks.add("العنوان رقم : " + (x));
                str.add(String.valueOf(x));
                String  num = shrd .getString("item"+x , "");
                toks3.add(num);
            }
        }
   /////////////////////////////////////////////
if (z < toks.size()) {

    for (int x = 0; x <= z; ++x) {

        toks.remove(0);
        str.remove(0);
       toks3.remove(0);

    }

    for (int x=1; x <= z; ++x) {
        toks.add("العنوان رقم : " + (x));
        str.add(String.valueOf(x));
        String  num = shrd .getString("item"+x , "");
        toks3.add(num);
    }
}
 btn_save();
        super.onResume();
}

    public void btn_newww(View view) {

            z ++ ;
            SharedPreferences shrd = getSharedPreferences("save file" , Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shrd .edit();
            editor . putString("count",  String.valueOf(z));

        for (int x=0; x <= z ; ++x ) {

            toks2 .add(shrd .getString("item" + x , null))  ;
            editor . putString("namec",  "item"+x);
        }

        editor.apply();

        btn_save();

        btn_good();

        }

    public void btn_save(){

        MyAdapter myAdapter = new MyAdapter(this, toks2, toks3, toks, str, new MyAdapter.ItemClickListener()  {
            @Override
            public void onItemClick( String details ) {
                namec  = "item"+details ;
                SharedPreferences shrd = getSharedPreferences("save file" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd .edit();
                editor . putString("namec", namec);
                editor . putString("coco" , details );

                editor.apply();
                btn_good();
            }
        });

        recyclerVieww.setAdapter(myAdapter);
        layoutmangerr = new GridLayoutManager(this, 2);
        recyclerVieww.setLayoutManager(layoutmangerr);
        text1.setText ( "الملاحظات | "+Integer.parseInt(String.valueOf(z))) ;

    }

    public void btn_good() {
        Intent intent = new Intent(this, Book.class);
        startActivity(intent);
    }
    public void btn_goodd(String d) {

        Intent intent = null;
        try {
            intent = new Intent(this, Class.forName(d));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
    public void btn_translate(View view) {

        SharedPreferences shrd = getSharedPreferences("save file" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd .edit();
        editor . putString("page", "1");
        editor.apply();

        Intent intent = new Intent(this , Translate.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
}