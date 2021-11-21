package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;


public class Book extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    EditText Book_Text , title;
    String nummm,numm, num , coped , ll;
    int z , S , r , mm ;
    ArrayList<String> vv , cc ;
    SharedPreferences shrd;
    SharedPreferences.Editor editor;
    ClipboardManager clipboardManager ;
    ScrollView scrollView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);



        scrollView = findViewById(R.id.scrollView2);

        vv = new ArrayList<>();   cc = new ArrayList<>();
        Book_Text = findViewById(R.id.textt); title = findViewById(R.id.textView2);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

// getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        userList.add(new User(1, "mohamed", 22));
//        userList.add(new User(2, "Amr", 22));
//        userList.add(new User(3, "ahmed", 22));
//        userList.add(new User(4, "adel", 22));

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (String.valueOf(title.getText()).equals("")){
                    r =6 ;  }else { r = 5 ; }

                editor = shrd.edit();
                if (r == 5 ){  editor.putString(numm, String.valueOf(title.getText()));
                    if (!Book_Text.getText().toString().equals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")) {

                        editor.putString("Title", String.valueOf(title.getText()));
                    }
                }else {
                    editor.putString(numm, "لا يوجد عنوان");
                    editor.putString("Title" ,"لا يوجد عنوان");
                }
                    editor.apply();} });
        oncreat();
        Book_Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {

                SharedPreferences.Editor editor = shrd.edit();
                editor.putString(num, String.valueOf(Book_Text.getText()));
                if (!Book_Text.getText().toString().equals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")) {
                    editor.putString("bookk", Book_Text.getText().toString());
                }
                editor.apply();

            }});
    }

    public void oncreat(){

        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();

        num   = shrd.getString("namec", "فارغ");
        numm  = shrd.getString("namecc", "فارغ");
        nummm = shrd.getString("nameccc", "فارغ");

        coped = shrd.getString(num , "");
//الشرط مش هيتحقق لانه مش بعد رال التزويد

        editor.putString("Title" ,shrd .getString(numm , "dc"));
        editor.putString("locckk" ,shrd.getString( nummm , "no"));
        editor.apply();

     //  String text =Book_Text.getText().toString().replace("\n", "").trim();

        Book_Text. setText(shrd.getString(num , ""));
        Book_Text. getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            String b  ; String n = "" ;
            @Override
            public boolean onPreDraw() {
                // Remove listener because we don't want this called before _every_ frame
                Book_Text.getViewTreeObserver().removeOnPreDrawListener(this);
                // Drawing happens after layout so we can assume getLineCount() returns the correct value
                if(Book_Text.getLineCount() < 30) {
                    for(int c=0; c <= (30-Book_Text.getLineCount()) ;++c ){
                        vv.add( c ,"\n");
                       b = n + vv.get(c);
                       n = b ;
                    }
                    Book_Text.  setText(shrd.getString(num  , "") +n );
                }
                return true; // true because we don't want to skip this frame
            }
        });


        if (!shrd.getString(numm, "").equals("") && !shrd.getString(numm, "").equals("لا يوجد عنوان")) {

            title.setText(shrd.getString(numm, ""));

        }else if (shrd.getString(numm, "").equals("لا يوجد عنوان")){

            title.setHint( "العنوان");

        } else{
            title.setHint( "العنوان");
        }
    }

    public void save() {

        Book_Text.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            String b  ; String n = "" ;
            @Override
            public boolean onPreDraw() {
                Book_Text.getViewTreeObserver().removeOnPreDrawListener(this);
                if(Book_Text.getLineCount() < 30) {
                    for(int c=0; c <= (30-Book_Text.getLineCount()) ;++c ){
                        vv.add( c ,"\n");
                        b = n + vv.get(c);
                        n = b ;
                    }
                    Book_Text.  setText(shrd.getString(num  , "") +n );
                }
                return true;
            }
        });

        SharedPreferences.Editor editor = shrd.edit();
        editor.putString(num, String.valueOf(Book_Text.getText()));
        if (r == 5 ){
            editor.putString(numm, String.valueOf(title.getText()));  }else { editor.putString(numm, "لا يوجد عنوان"); }
            editor.apply();

        String alltext  = Book_Text.getText().toString();
        String b  ; String n = "" ;

        for (int v=0 ; v< 60 ; ++v ){
            cc.add( v ,"\n");
            b = n + cc.get(v);
            n = b ;
            ll = b ;

            if (alltext.equals(b)){
                mm = 5 ; btn_remove(); n = "5" ;
          break;

            }
        }

        if (!n.equals("5")) {
            if (shrd.getString(num, "").equals("") || Book_Text.getText().toString().equals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")) {
                mm = 5 ;
                btn_remove();
            }
        }
    }

    public void btn_remove() {

        editor = shrd.edit();
        editor.remove(num);
        editor.remove(numm);
        editor.remove(nummm);

        int vv = Integer.parseInt(shrd.getString("coco", ""));

        z = Integer.parseInt(shrd.getString("count", "0"));

        if (S == 0) {

            for (int n = (vv + 1); n <= (z + 1); ++n) {
                String num3 = shrd.getString("item" + (n), "");
                editor.putString("item" + (n - 1), num3);

                String num4 = shrd.getString("itemm" + (n), "");
                editor.putString("itemm" + (n - 1), num4);

                String num5 = shrd.getString("lock" +(n) ,"");
                editor.putString("lock"+(n-1) , num5);
            }
        }


        z--;
        editor.putString("count", String.valueOf(z));
        editor.apply();

        //  Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
        //  intent2 .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //  intent2.putExtra("EXIT" , true);
        //  //  //  intent2.putStringArrayListExtra("aaaa" , toks );
        //  startActivity(intent2);
        //   System.exit(0);

        finish();
    }

    public void copy(View view) {

        ClipData clip = ClipData.newPlainText("Edit text" , Book_Text.getText().toString() );
        clipboardManager .setPrimaryClip(clip);
        clip.getDescription();
        Toast.makeText(Book.this ,"تم الحفظ", Toast.LENGTH_SHORT).show();
    }

    public void Alarm(View view) {
        save();
        Intent intent = new Intent(Book.this , Alarm2.class);
        startActivity(intent);

    }

    public void btn_lock (){

        SharedPreferences.Editor editor = shrd.edit();

        if (shrd.getString("password" , "19379173753159").equals("19379173753159")){

            Toast.makeText(this, "عين كلمة السر من شريط الاعدادات اولا", Toast.LENGTH_SHORT).show();

        }else {

        if (shrd.getString(nummm , "").equals("yes")){

            editor.putString(nummm, "no");
            editor.putString("locckk" ,shrd.getString( nummm , "no"));

            Toast.makeText(this, "تم الغاء قفل الملاحظة", Toast.LENGTH_SHORT).show();
        }else {
            editor.putString(nummm, "yes");
            editor.putString("locckk" ,shrd.getString( nummm , "yes"));
            Toast.makeText(this, "تم قفل الملاحظه", Toast.LENGTH_SHORT).show();
        }
        editor.apply();
        }
    }

    public void menuee_book(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_book);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item:
                save();
                Intent intent = new Intent(Book.this, Alarm2.class);
                startActivity(intent);
                return true;
            case R.id.item2:Toast.makeText(Book.this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:Toast.makeText(Book.this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show(); return true;

            case R.id.item4: btn_lock();
                return true;
            case R.id.item5:
                if (shrd.getString(num, "").equals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")){ S = 1 ; btn_remove();
                }else {btn_remove();}
                return true;
            case R.id.item6:Book_Text.setText(coped);
                return true;
            case R.id.item7: AddToWidget();
                return true;
            default:
                return false;
        }
    }

    private void AddToWidget() {
       SharedPreferences.Editor editor = shrd.edit();
       editor.putString("widget2" , title.getText().toString() +"\n"+ Book_Text.getText().toString());
       editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void back(View view) {
        onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed() {



        save();
        SharedPreferences.Editor editor = shrd.edit();

        if (shrd.getString("AD" , "D").equals("A")){
            editor.putString("AD" , "D" );
            editor.apply();

        }else {

        if(!Book_Text.getText().toString().equals(coped) && mm != 5){

                editor.putString("changed", "1");
                editor.apply();
                btn_remove();
          }
        }
        // if(backpressedtime + 2000 >System .currentTimeMillis()) {
        super.onBackPressed();
        return;//  }//  backpressedtime = System.currentTimeMillis();
    }
}