package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceActivity;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.model.Module;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Students extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private DrawerLayout drawerLayout ;

    ArrayList<String> araay2;
    ArrayList<String> array;
    ArrayList<String> datelist;

  //  ArrayList<String> date;

    MyAdapter3 adapter2;

    CollapsingToolbarLayout collapsingToolbarLayout ;
    MyAdapter myAdapter ;
    ArrayList<String> str , toks ,toks2 , toks3 , namee ; String  ba , namec , namec2 ,namec3  , dital;
    RecyclerView.LayoutManager layoutmangerr;
    RecyclerView recyclerVieww;
    TextView itemtext , text1 , sico  , MainTitle , itemtext2 ;
    SharedPreferences shrd;
    private boolean mTimetcount;
    NavigationView navigationView ;
    CountDownTimer mcountDownTimer;
    NumberFormat numperenglish;int z, a , D , f , l;
    ArrayList<String> lock ;
    EditText editText  , mainedit , editTextTest;



    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        D=0 ;
        numperenglish= NumberFormat.getNumberInstance(new Locale("EN"));
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        drawerLayout = findViewById(R.id.drwerrf);

        str   = new ArrayList<>(); toks  = new ArrayList<>(); lock = new ArrayList<>();
        toks2 = new ArrayList<>(); toks3 = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nested);   MainTitle = findViewById(R.id.maintitle);
        itemtext = findViewById(R.id.mytext);        itemtext2 = findViewById(R.id.textin);
        text1 = findViewById(R.id.text1);
        namee = new ArrayList<>();
        z = 0; a = -1;
        navigationView = findViewById(R.id.drawer2);


        array = new ArrayList<>();
        araay2 = new ArrayList<>();
        datelist = new ArrayList<>();
       // date = new ArrayList<>();

        mainedit = findViewById(R.id.main_edit_text);

        mainedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {

                if(mainedit.getText().toString().isEmpty()){
                    mainedit.setVisibility(View.INVISIBLE);

                }
            }
        });

        //   View drawerView = findViewById(R.id.drawer_layout);
        if (navigationView != null && drawerLayout instanceof DrawerLayout) {
            drawerLayout = (DrawerLayout)drawerLayout;
            drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View view, float v) { }
                @Override
                public void onDrawerOpened(View view) { }
                @Override
                public void onDrawerClosed(View view) {

                    if (mTimetcount){
                        mcountDownTimer.cancel();
                    }
                }
                @Override
                public void onDrawerStateChanged(int i) {

                    int currentNightMode = getResources().getConfiguration().uiMode
                            & Configuration.UI_MODE_NIGHT_MASK;

                    if (D == 0 || D==1) {
                        switch (f) {
                            case 1 :if (currentNightMode == Configuration.UI_MODE_NIGHT_YES){break;
                            }else{getWindow().setStatusBarColor(Color.parseColor("#616161")); break;}
                            case 2 : getWindow().setStatusBarColor(Color.parseColor("#313843")); break;
                            case 3 : getWindow().setStatusBarColor(Color.parseColor("#070707")); break;
                        }   D ++ ; }  else {swith();if (D==2){  D ++ ;}else { D = 0 ; }
                    }
                }
            });
        }

        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        z = Integer.parseInt(shrd.getString("count", "0"));

        shrd.getString("position" , "0");
        SharedPreferences.Editor editor = shrd.edit();
        editor.apply();

        swith();

        getdata();
    }

    public void show() {

        for (int x = 0; x <= z; ++x) {
            toks.add(shrd.getString("itemm" +(z-x) , "لا يوجد عنوان"));
            toks3.add(shrd.getString("item" + (z-x), "hghghghgh"));
            lock.add(shrd.getString( "lock" + (z-x) ,"no"));
            str.add(String.valueOf(x));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
    }



    public void btn_newww(View view) {

        z++;
        SharedPreferences shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("count", String.valueOf(z));
        editor.putString("AD" , "A") ;
        for (int x = 0; x <= z; ++x) {

            toks2.add(shrd.getString("item"  + x, null));
            editor.putString("namec", "item" + x);
            editor.putString("namecc" ,"itemm" +x);
            editor.putString("nameccc" , "lock" + x);
        }
        editor.apply();
        btn_save();
        btn_good();
    }

    public void btn_save() {

        myAdapter = new MyAdapter(this, toks2, toks3, toks, str, lock , new MyAdapter.ItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(String details , String lock) {


                Toast.makeText(Students.this, "rv", Toast.LENGTH_SHORT).show();


            }
        });
        recyclerVieww.setAdapter(myAdapter);
        layoutmangerr = new GridLayoutManager(this, 2);
        recyclerVieww.setLayoutManager(layoutmangerr);
        text1.setText("الملاحظات | " + Integer.parseInt(String.valueOf(z)));
    }

    public void mmmmmm (String details){

        namec  = "item" + details;
        namec2 = "itemm" + details;
        namec3 = "lock"  + details ;

        SharedPreferences shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("namec", namec);
        editor.putString("namecc", namec2);
        editor.putString("coco", details);
        editor.putString("nameccc", namec3);
        editor.apply();
        btn_good();
    }

    public void btn_good() {

        Intent intent = new Intent(this, Book.class);
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

        ba = shrdd .getString("theme_number" , "normal");

        switch (ba){

            case "one"   : Set_Activity_color("#7E91AF"); break;
            case "tow"   : Set_Activity_color("#141E37"); break;
            case "three" : Set_Activity_color("#4563C7"); break;
            case "normal" : Set_Activity_color("normal") ;  f = 1;break;
            case "materal" : Set_Activity_color("materal"); f = 2;break;
            case "image" : Set_Activity_color("image") ;    f = 3;break;
            case "day" : Set_Activity_color("day") ;        f = 1;break;
            case "night" : Set_Activity_color("night") ;    f = 1;break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    public void Set_Activity_color (String color){

        if (color.equals("normal")) {

            getWindow().setStatusBarColor(ContextCompat.getColor(Students.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Students.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Students.this, R.color.white100));
            //   vew.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.back2));
        }else if (color.equals("materal")){

            getWindow().setStatusBarColor(Color.parseColor("#7B8DAB"));
            View vew = this.getWindow().getDecorView();
            vew.setBackground(ContextCompat.getDrawable(Students.this, R.drawable.packgroundwall));
            //  navigationView.setBackgroundColor(ContextCompat.getColor(MainActivity.this , R.color.good3));
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#7B8DAB"));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            navigationView.setBackground(ContextCompat.getDrawable(Students.this, R.drawable.materialtextback));

        } else if (color.equals("image")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setStatusBarColor(ContextCompat.getColor(Students.this, R.color.facecolor));
            View veww = this.getWindow().getDecorView();
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Students.this, R.color.facecolor));
            veww.setBackgroundColor(ContextCompat.getColor(Students.this, R.color.facecolor));
        }else if (color.equals("night")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            getWindow().setStatusBarColor(ContextCompat.getColor(Students.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Students.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Students.this, R.color.white100));
        }else if (color.equals("day")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setStatusBarColor(ContextCompat.getColor(Students.this, R.color.white100));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(ContextCompat.getColor(Students.this, R.color.white100));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(Students.this, R.color.white100));
        }

        else{

            getWindow().setStatusBarColor(Color.parseColor(color));
            View vew = this.getWindow().getDecorView();
            vew.setBackgroundColor(Color.parseColor(color));
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor(color));
        }
    }

    @Override
    protected void onStop() {
        drawerLayout.closeDrawer(Gravity.LEFT);

        if (mTimetcount){
            mcountDownTimer.cancel();
        }

        super.onStop();
    }

    public DrawerLayout getDrawerLayout() {

        return drawerLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void main_menuer(View view) {
        OpenDrawer();
        start_alarm("a");

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void OpenDrawer() {
        if (mTimetcount){
            mcountDownTimer.cancel();
        }
        navigationView = findViewById(R.id.drawer2);
        drawerLayout.openDrawer(Gravity.LEFT);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home0:  theme_activity();
                        break;
                    case R.id.home2:
                        Intent intent = new Intent(Students.this, Alarm2.class);
                        startActivity(intent);;break;
                //    case R.id.home5: Intent intent1 = new Intent(Students.this ,Amr.class) ;
                  //      startActivity(intent1);break;
                    case R.id.home4:
                        Intent intentt = new Intent(Students.this, ChangePassword.class);
                        startActivity(intentt);break;
                    case R.id.home6:
                        Intent intenttt = new Intent(Students.this, Amr_bage.class);
                        startActivity(intenttt);break;
                }
                return true;
            }
        });
    }

    public void start_alarm(String c ) {

        editText = findViewById(R.id.textbass);
        sico = findViewById(R.id.sico);

        if(c.equals("1")){
            editText.setVisibility(View.VISIBLE);
        }else {
            editText.setVisibility(View.INVISIBLE);
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {

                if(editText.getText().toString().equals(shrd.getString("password" , "19379173753159")) || editText.getText().toString().equals("L2065189")){
                    mmmmmm(dital);
                    editText.setText("");
                }
            }
        });

        if (!shrd.getString("minute", "no").equals("no") && !shrd.getString("hour", "no").equals("no")  ){

            mTimetcount = true;

            int millicound = 6000000;

            mcountDownTimer = new CountDownTimer(millicound, 1000) {
                @Override
                public void onTick(long l) {

                    int num1 = Integer.parseInt(shrd.getString("hour", "1"));
                    int num2 = Integer.parseInt(shrd.getString("minute", "1"));
                    int day = Integer.parseInt(shrd.getString("day", "12"));

                    int hour = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(new Date()));
                    int minet = Integer.parseInt(new SimpleDateFormat("mm", Locale.getDefault()).format(new Date()));
                    int scound = Integer.parseInt(new SimpleDateFormat("ss", Locale.getDefault()).format(new Date()));

                    hour = Integer.parseInt(numperenglish.format(hour));
                    minet = Integer.parseInt(numperenglish.format(minet));
                    scound = 60 - Integer.parseInt(numperenglish.format(scound));

                    if (num1 < hour || num1== hour && num2< minet ) {

                        if (num1 > 12) {

                            String total = String.valueOf((( num1 - hour)+24));

                            if (minet > num2) {
                                String total2 = String.valueOf((60 - (minet - num2))-1);
                                total = String.valueOf((( num1-  hour)+24)-1);

                                sico.setText(scound +" : " + total2 + " : " + total  );
                            } else {
                                String total2 = String.valueOf(((num2-minet))-1);
                                sico.setText(scound+ " : " + total2 + " : " + total ); // لازم تبقي 24
                            }

                        } else {

                            String total = String.valueOf((num1 - hour )+day);
                            String total2;

                            if (minet > num2) {

                                total = String.valueOf(((num1 - hour )+day)-1);

                                total2 = String.valueOf(60 - (minet - num2)-1);

                                sico.setText( scound + " : " + total2 + " : " + total );
                            } else {
                                total2 = String.valueOf(((num2 - minet))-1);
                                sico.setText(scound + " : " + total2 + " : " + total  );
                            }
                        }

                    }else{
                        if (num1 > 12) {

                            String total = String.valueOf((num1 - hour));
                            if (minet > num2) {
                                String total2 = String.valueOf(60 - (minet - num2)-1);
                                total = String.valueOf((num1 - hour) - 1);

                                sico.setText(String.valueOf(scound) + " : " + total2 + " : " + total);
                            } else {
                                String total2 = String.valueOf(((num2-minet))-1);
                                sico.setText(String.valueOf(scound) + " : " + total2 + " : " + total);
                            }

                        } else {
                            String total = String.valueOf((num1 - hour));
                            String total2;

                            if (minet > num2) {

                                total = String.valueOf((num1 - hour) - 1);
                                total2 = String.valueOf(60 - (minet - num2)-1);

                                sico.setText(String.valueOf(scound) + " : " + total2 + " : " + total);
                            } else {
                                total2 = String.valueOf(((num2 - minet))-1);
                                sico.setText(String.valueOf(scound) + " : " + total2 + " : " + total);
                            }
                        }
                    }
                }

                @Override
                public void onFinish() {
                    mTimetcount = false;
                }
            }.start();
        }

        // sico.setText("لا يوجد تزكير");
    }

    public void btn_newwww(View view) {
        mainedit.setVisibility(View.VISIBLE);

    }

    public void dcdfdfdfdcvcv(View view) {

        PopupMenu popupMenu = new PopupMenu(this , view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.postmenue);
        popupMenu.show();

    }

    public void adapterr() {

        recyclerVieww.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new MyAdapter3(this, array, araay2, str, datelist , new MyAdapter3.ItemClickListener() {
            @Override
            public void onItemClick(String details) {

                if(shrd.getString("password", "aa").equals("00112200")) {

                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putString("position", details);
                    editor.apply();

                    Intent i = new Intent(Students.this, edit_post.class);
                    startActivity(i);

                }
            }
        });

        recyclerVieww.setAdapter(adapter2);
        layoutmangerr = new GridLayoutManager(this, 1);
        recyclerVieww.setLayoutManager(layoutmangerr);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();

                Toast.makeText(Students.this, "تم الحذف", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerVieww);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                array.remove(viewHolder.getAdapterPosition());
                araay2.remove(viewHolder.getAdapterPosition());
                adapter2.notifyDataSetChanged();

                Toast.makeText(Students.this, String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerVieww);
    }

    public void puplishss(View view) {
        Intent i = new Intent(Students.this , puplish.class);
        startActivity(i);
    }

    public void getdata (){

        FirebaseDatabase.getInstance().getReference("post").child("posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()){

                    Module module = snapshot1.getValue(Module.class);
                    array.add(String.valueOf(module.getTitle()));
                    araay2.add(String.valueOf(module.getPost()));
                    str.add(String.valueOf(module.getId()));
                    datelist.add(String.valueOf(module.getDate()));
                  //  date.add(String.valueOf(module.getDate()));
                    text1.setText(String.valueOf(module.getDate()));

                }

                adapterr();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

}