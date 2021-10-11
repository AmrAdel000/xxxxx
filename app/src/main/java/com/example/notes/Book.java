package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.model.User;

import java.util.ArrayList;
import java.util.List;


public class Book extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {



//    List<User> userList = new ArrayList<>();
    private long backpressedtime;
    EditText taa , title;
    String numm, num;
    int z , S , r;

    SharedPreferences shrd;
    SharedPreferences.Editor editor;

    ClipboardManager clipboardManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        taa = findViewById(R.id.textt); title = findViewById(R.id.textView2);
       clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        userList.add(new User(1, "mohamed", 22));
//        userList.add(new User(2, "Amr", 22));
//        userList.add(new User(3, "ahmed", 22));
//        userList.add(new User(4, "adel", 22));

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                if (String.valueOf(title.getText()).equals("")){

                    r =6 ;
                }else {
                    r = 5 ;
                }

            }
        });


        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);

        num  = shrd.getString("namec", "");
        numm = shrd.getString("namecc", "");

        taa.  setText(shrd.getString(num , ""));


        if (!shrd.getString(numm, "").equals("") && !shrd.getString(numm, "").equals("لا يوجد عنوان")) {

            title.setText(shrd.getString(numm, ""));

        }else if (shrd.getString(numm, "").equals("لا يوجد عنوان")){

            title.setHint( "العنوان");

        } else{
            title.setHint( "العنوان");
        }

        //  taa.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    protected void onStop() {
        save();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        save();
        // if(backpressedtime + 2000 >System .currentTimeMillis()) {
        super.onBackPressed();
        return;//  }//  backpressedtime = System.currentTimeMillis();

    }

    public void btn_remove() {

        editor = shrd.edit();
        editor.remove(num);
        editor.remove(numm);

        int vv = Integer.parseInt(shrd.getString("coco", ""));

        z = Integer.parseInt(shrd.getString("count", ""));


        if (S == 0) {

            for (int n = (vv + 1); n <= (z + 1); ++n) {
                String num3 = shrd.getString("item" + (n), "");
                editor.putString("item" + (n - 1), num3);

                String num4 = shrd.getString("itemm" + (n), "");
                editor.putString("itemm" + (n - 1), num4);

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

    public void menuee_book(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_book);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item1:
                ;
                return true;
            case R.id.item2:
                return true;
            case R.id.item3:
                return true;
            case R.id.item4:
                return true;
            case R.id.item5:btn_remove();
                return true;
            default:
                return false;
        }
    }

    public void copy(View view) {

        ClipData clip = ClipData.newPlainText("Edit text" , taa.getText().toString() );
        clipboardManager .setPrimaryClip(clip);
        clip.getDescription();
        Toast.makeText(Book.this ,"تم الحفظ", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        onBackPressed();
    }

    public void save() {
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString(num, String.valueOf(taa.getText()));
        if (r == 5 ){
            editor.putString(numm, String.valueOf(title.getText()));
        }else {
            editor.putString(numm, "لا يوجد عنوان");
        }
        editor.apply();
        if (shrd.getString(num, "").equals("")){ S = 1 ; btn_remove();}
    }

}