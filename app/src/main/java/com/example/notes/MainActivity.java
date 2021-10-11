package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.CornerFamily;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private DrawerLayout drawerLayout ;

    CollapsingToolbarLayout collapsingToolbarLayout ;
    MyAdapter myAdapter ;
    ArrayList<String> str , toks ,toks2 , toks3 , namee ;
    RecyclerView.LayoutManager layoutmangerr;
    RecyclerView recyclerVieww;
    TextView itemtext , text1;
    String  ba , namec , namec2 ;
    SharedPreferences shrd;
    int z, a;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);

        drawerLayout = findViewById(R.id.drwerr);


        str   = new ArrayList<>(); toks  = new ArrayList<>();
        toks2 = new ArrayList<>(); toks3 = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nested);
        itemtext = findViewById(R.id.mytext);
        text1 = findViewById(R.id.text1);
        namee = new ArrayList<>();
        z = 0; a = -1;


      //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);

        z = Integer.parseInt(shrd.getString("count", "0"));

        if (shrd.getString("page", "").equals("1")) {
            Intent intent = new Intent(this, Translate.class);
            startActivity(intent);
            finish();
        }
        text1.setText("الملاحظات | " + Integer.parseInt(String.valueOf(z)));
        show();
    }
    public void show() {

        for (int x = 0; x <= z; ++x) {

            toks.add(shrd.getString("itemm" + x, "لا يوجد عنوان"));
            toks3.add(shrd.getString("item" + x, ""));
            str.add(String.valueOf(x));
        }
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {

        swith();

        z = Integer.parseInt(shrd.getString("count", "ON_DATA"));

        if (z > toks.size()) {

            for (int x = 2; x <= z; ++x) {
                toks.remove(0);
                str.remove(0);
                toks3.remove(0);
            }
            for (int x = 1; x <= z; ++x) {

                toks.add(shrd.getString("itemm" + x, "لا يوجد عنوان"));
                str.add(String.valueOf(x));
                String num = shrd.getString("item" + x, "");
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

            for (int x = 1; x <= z; ++x) {

               toks.add(shrd.getString("itemm" + x, "لا يوجد عنوان"));
               toks3.add(shrd.getString("item" + x, ""));
               str.add(String.valueOf(x));

            }
        }

        // من هنا لازم نضيف التغيرات علي ال arays من جديد


        for (int x = 0; x <= (z-1); ++x) {

            toks.remove(0);
            str.remove(0);
            toks3.remove(0);

        }

        for (int x = 1; x <= (z); ++x) {

            toks.add(shrd.getString("itemm" + x, "لا يوجد عنوان"));
            toks3.add(shrd.getString("item" + x, ""));
            str.add(String.valueOf(x));

        }



      //  toks.remove(7);
        btn_save();
        super.onResume();
    }

    public void btn_newww(View view) {

        z++;
        SharedPreferences shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("count", String.valueOf(z));

        for (int x = 0; x <= z; ++x) {

            toks2.add(shrd.getString("item"  + x, null));
            editor.putString("namec", "item" + x);
            editor.putString("namecc" ,"itemm" +x);

        }

        editor.apply();
        btn_save();
       btn_good();

    }

    public void btn_save() {

        myAdapter = new MyAdapter(this, toks2, toks3, toks, str, new MyAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String details) {

                namec  = "item" + details;
                namec2 = "itemm" + details;

                SharedPreferences shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();
                editor.putString("namec", namec);
                editor.putString("namecc", namec2);
                editor.putString("coco", details);
                editor.apply();
                btn_good();
            }
        });
        recyclerVieww.setAdapter(myAdapter);
        layoutmangerr = new GridLayoutManager(this, 2);
        recyclerVieww.setLayoutManager(layoutmangerr);

        text1.setText("الملاحظات | " + Integer.parseInt(String.valueOf(z)));

    //   toks.remove(7);


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

        SharedPreferences shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("page", "1");
        editor.apply();

        Intent intent = new Intent(this, Translate.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void main_menue(View view) {
        PopupMenu popupMenu = new PopupMenu(this , view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.main_menue);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.item1 : theme_activity();
                return true ;
            case R.id.item2 :
                return true ;
            case R.id.item3:
                return true ;
            default :
                return false ;
        }

    }
    public void theme_activity() {

        Intent intent = new Intent(this, theme.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public void swith  (){

        SharedPreferences   shrdd = getSharedPreferences("trans file", Context.MODE_PRIVATE);

        ba = shrdd .getString("theme_number" , "a");

        switch (ba){

            case "one"   : Set_Activity_color("#7E91AF"); break;
            case "tow"   : Set_Activity_color("#141E37"); break;
            case "three" : Set_Activity_color("#4563C7"); break;
            case "normal" : Set_Activity_color("normal"); break;
            case "materal" : Set_Activity_color("materal"); break;
            case "image" : Set_Activity_color("image"); break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    public void Set_Activity_color (String color){

        if (color.equals("normal")) {

           getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(MainActivity.this, R.color.white100));
         //   vew.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back2));

        }else if (color.equals("materal")){

            getWindow().setStatusBarColor(Color.parseColor("#7E91AF"));
            View vew = this.getWindow().getDecorView();
            vew.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.packgroundwall));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(MainActivity.this, R.color.good));
        } else if (color.equals("image")){

        getWindow().setStatusBarColor(Color.parseColor("#7B8DAB"));
        View vew = this.getWindow().getDecorView();
        vew.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.p));
        collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#7B8DAB"));

    }
        else{

            getWindow().setStatusBarColor(Color.parseColor(color));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(Color.parseColor(color));
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor(color));
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void main_menuer(View view) {

        NavigationView navigationView = findViewById(R.id.drawer2);
      //  getWindow().setStatusBarColor(Color.parseColor("#616161"));
        drawerLayout.openDrawer(Gravity.LEFT);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home0:  theme_activity(); drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return true;
            }
        });
       // drawerLayout.openDrawer(GravityCompat.START);
    }
}