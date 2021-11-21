package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class Translate extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button button; Button buttonn;
    RecyclerView      recyclerVieww ;
    ArrayList<String> str ;
    ArrayList<String> araay2 ;
    ArrayList<String> array  ;
    public static String bbbbb ;
    EditText  textset ;
    EditText  textget ;
    MyAdapter2 adapter2 ;
    RecyclerView.LayoutManager layoutmangerr ;
    int s , a ;
    TextView textViewvv;
    TextToSpeech mtts ;
    String ba ;
    CollapsingToolbarLayout collapsingToolbarLayout ;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        bbbbb = "";

        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbarr);

        button = findViewById(R.id.button);   buttonn= findViewById(R.id.buttoncv);
        button.setVisibility(View.INVISIBLE); buttonn.setVisibility(View.INVISIBLE);

        array = new ArrayList<>();araay2 = new ArrayList<>();str = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nested);
        recyclerVieww.setHasFixedSize(true);
        textget=findViewById(R.id.texevew1);  textset=findViewById(R.id.text11);

        //        android:layoutDirection="ltr" بتعدل الريسيكلرفيو من اليمين الي الشمال
        //   getWindow().setStatusBarColor(ContextCompat.getColor(Translate.this, R.color.good5));
        //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        swith();
        SharedPreferences shrd = getSharedPreferences("trans file", Context.MODE_PRIVATE);
        s = Integer.parseInt(shrd.getString("counted", "0"));

        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if (i == TextToSpeech.SUCCESS){
                    int result =   mtts.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                        Log.e("TTS" , "Language not supported");
                    }else {

                    }
                }else {
                    Log.e("TTS" , "Initialization Filed");
                }
            }
        });

        textViewvv = findViewById(R.id.texevew22);
        registerForContextMenu(textViewvv);

        textget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String n = String.valueOf(textget.getText());
                if (n.equals("")){

                    button.setVisibility(View.INVISIBLE);

                }else {
                    button.setVisibility(View.VISIBLE);
                    buttonn.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bbbbb.equals("Arabic")) {
                    Translate_method2();
                } else {
                    Translate_method1();
                }
                button.setVisibility(view.INVISIBLE);
                buttonn.setVisibility(View.VISIBLE);
            }
        });

        for (int x = 1 ; x <= s ; ++x){

            str . add(String.valueOf(x));
            array.add(shrd .getString("world"+  x , "play")) ;
            araay2.add(shrd .getString("worldd"+x , "يلعب")) ;

        }



        send_to_Adapter();
    }

    public void btn_savee(View view) {
        s ++;
        buttonn.setVisibility(View.INVISIBLE);
        SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shrd .edit();
        editor . putString("counted" , String.valueOf(s));

        if (bbbbb.equals("")){

            editor . putString("world"+ s ,textset.getText().toString());
            editor . putString("worldd"+s ,textget.getText().toString());

        }else {

            editor.putString("world" + s, textget.getText().toString());
            editor.putString("worldd" + s, textset.getText().toString());
        }

        editor.apply();

        if (s > array.size()) {

            for (int x = 2; x <= s; ++x) {

                array.remove(0);
                araay2.remove(0);
                str . remove(0);
            }

            for (int x=1; x <= s; ++x) {

                String  num = shrd .getString("world"+x ,"play");
                String  num2 = shrd .getString("worldd"+x ,"يلعب");

                array.add(num);
                araay2.add(num2);
                str . add(String.valueOf(x));
            }
            textset .setText("");
            textget .setText("");
            Toast.makeText(Translate.this , "تم حفظ الترجمه" , Toast.LENGTH_SHORT).show();

            send_to_Adapter();

        }
    }

    public void send_to_Adapter (){

        recyclerVieww.setLayoutManager(new LinearLayoutManager(this ));
        adapter2 = new MyAdapter2 (this , array , araay2 , str, new MyAdapter.ItemClickListener() {

            @Override
            public void onItemClick(String details , String lock) {

                speak(details);
            }
        });

        recyclerVieww.setAdapter(adapter2);
        layoutmangerr = new GridLayoutManager(this, 2);
        recyclerVieww.setLayoutManager(layoutmangerr);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull  RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull  RecyclerView.ViewHolder viewHolder, int direction) {
                s -- ;
                SharedPreferences shrd = getSharedPreferences("trans file" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd .edit();
                editor . putString("counted" , String.valueOf(s));

                int vv = viewHolder.getAdapterPosition();

                editor.remove("world"+(vv+1));
                editor.remove("worldd"+(vv+1));

                for (int c=(vv+1); c<= (s+1)  ; ++c ) {

                    String  num3  = shrd .getString("world" +(c+1),"play");
                    String  num4 = shrd .getString("worldd"+(c+1),"يلعب");

                    editor . putString("world" +(c), num3);
                    editor . putString("worldd"+(c), num4);

                }

                editor.apply();

                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();
                Toast.makeText(Translate.this , "تم الحذف" , Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerVieww);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull  RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                SharedPreferences shrd = getSharedPreferences("trans file", Context.MODE_PRIVATE);

                s--;  a = Integer.parseInt(shrd.getString("countedd", "0")); a++;

                SharedPreferences.Editor editor = shrd .edit();

                editor . putString("counted" , String.valueOf(s));
                editor . putString("countedd" , String.valueOf(a));

                int vv = viewHolder.getAdapterPosition();
                String favorite1 =  shrd.getString("world"+(vv+1) , "play");
                String favorite2 =  shrd.getString("worldd"+(vv+1) , "يلعب");

                editor . putString("favorite"+  a , favorite1);
                editor . putString("favoritee"+ a , favorite2);

                editor.remove("world"+(vv+1));
                editor.remove("worldd"+(vv+1));

                for (int c=(vv+1); c<= (s+1)  ; ++c ) {
                    String  num3  = shrd .getString("world" +(c+1),"play");
                    String  num4 = shrd .getString("worldd"+(c+1),"يلعب");

                    editor . putString("world" +(c), num3);
                    editor . putString("worldd"+(c), num4);
                }

                editor.apply();

                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();

                Toast.makeText(Translate.this , "تم نقلها الي الكلمات التي تم حفظها" , Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerVieww);

    }

    public void btn_main(View view) {

        SharedPreferences shrdd = getSharedPreferences("save file" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrdd .edit();
        editor . putString("page", "2");
        editor.apply();

        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

        finish();

    }

    public void btn_save9() {
        Intent intent = new Intent(this ,Favorite.class);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        startActivity(intent);
    }


    public void Translate_method1 (){

        String one = textget.getText().toString();

        TranslateAPI translateAPI = new TranslateAPI( Language.AUTO_DETECT, Language.ENGLISH,one);

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                textset.setText(translatedText);
                String tow = translatedText;

                if (one.equals(tow)){
                    Toast.makeText(Translate.this, "حدد لغة الترجه", Toast.LENGTH_SHORT).show();

                }else {
                   tow = translatedText.toLowerCase() ;
                    firebase_words_1(one , tow);
                }

            }
            @Override
            public void onFailure(String ErrorText) {
                Log.d("Error" , ErrorText);
            }
        });
    }

    public void Translate_method2 (){

        String vz = "ar";

        String one = textget.getText().toString();

        TranslateAPI translateAPI = new TranslateAPI(Language.AUTO_DETECT,vz,one);
        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {

                textset.setText(translatedText);
                String tow = translatedText ;

                if(one.equals(tow)){
                    Toast.makeText(Translate.this, "حدد لغة الترجه", Toast.LENGTH_SHORT).show();
                }else {
                    firebase_words_2(one , tow);
                }
            }
            @Override
            public void onFailure(String ErrorText) {
                Log.d("Error" , ErrorText);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void menuee(View view) {
        PopupMenu popupMenu = new PopupMenu(this , view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_test);
        popupMenu.show();
    }

    public void menuee2(View view) {

        PopupMenu popupMenu = new PopupMenu(this , view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_test2);
        popupMenu.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item : bbbbb = "Arabic" ;textViewvv.setText("ترجمه الي اللغة العربيه");return true ;
            case R.id.item2 : bbbbb = "";textViewvv.setText("ترجمه الي اللغة الانجليزيه");return true ;
            case R.id.btn_save9 : btn_save9();   return true ;
            case R.id.btn_firebase_words:
                Toast.makeText(this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();
                return true ;
            default :
                return false ;
        }
    }

    private void speak( String v) {

        SharedPreferences shrd = getSharedPreferences("trans file", Context.MODE_PRIVATE);
        String vb  = shrd .getString("world"+v , "") ;

        //  mtts.setPitch(10);
        //   mtts.setSpeechRate(10);
        mtts.speak(vb, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {

        if (mtts!= null){
            mtts.stop();
            mtts.shutdown();
        }
        super.onDestroy();
    }

    public void data (){
        Intent intent = new Intent(this , MyDataBase.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public void swith  () {

        SharedPreferences shrdd = getSharedPreferences("trans file", Context.MODE_PRIVATE);

        ba = shrdd.getString("theme_number", "normal");
        switch (ba) {
            case "one":Set_Activity_color("#7E91AF");break;
            case "tow":Set_Activity_color("#141E37");break;
            case "three":Set_Activity_color("#4563C7"); break;
            case "normal": Set_Activity_color("normal");break;
            case "materal": Set_Activity_color("materal");   break;
            case "image": Set_Activity_color("image");
                break;case "day":Set_Activity_color("day");
                break;case "night":Set_Activity_color("night"); break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    public void Set_Activity_color (String color){

        if (color.equals("normal")) {

            getWindow().setStatusBarColor(ContextCompat.getColor(Translate.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Translate.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Translate.this, R.color.white100));
            //   vew.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back2));
        }else if (color.equals("materal")){

            getWindow().setStatusBarColor(Color.parseColor("#7B8DAB"));
            View vew = this.getWindow().getDecorView();
            vew.setBackground(ContextCompat.getDrawable(Translate.this, R.drawable.packgroundwall));
            //  navigationView.setBackgroundColor(ContextCompat.getColor(MainActivity.this , R.color.good3));
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#7B8DAB"));
            //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        } else if (color.equals("image")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setStatusBarColor(ContextCompat.getColor(Translate.this, R.color.facecolor));
            View veww = this.getWindow().getDecorView();
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Translate.this, R.color.facecolor));
            veww.setBackgroundColor(ContextCompat.getColor(Translate.this, R.color.facecolor));
        }else if (color.equals("night")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            getWindow().setStatusBarColor(ContextCompat.getColor(Translate.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Translate.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Translate.this, R.color.white100));
        }else if (color.equals("day")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setStatusBarColor(ContextCompat.getColor(Translate.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Translate.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Translate.this, R.color.white100));
        }
        else{
            getWindow().setStatusBarColor(Color.parseColor(color));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(Color.parseColor(color));
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor(color));
        }
    }

    public void toastt(View view) {
        Toast.makeText(this, "سيتوفر في التحديث القام", Toast.LENGTH_SHORT).show();
    }

    public void firebase_words_1(String one , String tow ){

            DatabaseReference root = FirebaseDatabase.getInstance().getReference("Words").child("Arabic");
            DatabaseReference root2 = FirebaseDatabase.getInstance().getReference("Words").child("English");
            //  root.push().setValue(ddddd);
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String c = "0";
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        if (data.getValue().toString().equals(one) ) {
                            c = "0";
                            break;
                        } else {
                            c = "1";
                        }
                    }
                    if (!c.equals("0")) {
                        root.push().setValue(one);
                        root2.push().setValue(tow);
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
             }
        });
    }


    public void firebase_words_2(String one , String tow ) {

        DatabaseReference root = FirebaseDatabase.getInstance().getReference("Words").child("English");
        DatabaseReference root2 = FirebaseDatabase.getInstance().getReference("Words").child("Arabic");
        //  root.push().setValue(ddddd);
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String c = "0";
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.getValue().toString().equals(one)) {
                        c = "0";
                        break;
                    } else {
                        c = "1";
                    }
                }
                if (!c.equals("0")) {
                    root.push().setValue(one);
                    root2.push().setValue(tow);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
         });
      }
}