package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.notes.model.User;

import java.util.ArrayList;
import java.util.List;


public class Book extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    List<User> userList =new ArrayList<>();
    private  long backpressedtime ;

    String namec , num ;
    ArrayList <String> str ;
    ArrayList<String> toks  ;
    ArrayList<String> toks2  ;

    TextView itemtext ;
    ArrayList<String> namee ;
    int z ;
    RecyclerView recyclerVieww ;
   TextView text_getitemname ;
   EditText taa ;
    SharedPreferences shrd;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        str = new ArrayList<>();
        toks = new ArrayList<>();
        toks2 = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nested);
        itemtext=findViewById(R.id.mytext);
        namee = new ArrayList<>();
        recyclerVieww = findViewById(R.id.nested);
        text_getitemname = findViewById(R.id.mytext);
        taa = findViewById(R.id.textt);

      //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        userList.add(new User(1,"mohamed",22));
        userList.add(new User(2,"Amr",22));
        userList.add(new User(3,"ahmed",22));
        userList.add(new User(4,"adel",22));


        shrd = getSharedPreferences("save file" , Context.MODE_PRIVATE);

        num = shrd .getString("namec" , "");
        taa .setText( shrd .getString( num , ""));

      //  taa.setMovementMethod(LinkMovementMethod.getInstance());

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        SharedPreferences.Editor editor = shrd .edit();
        editor . putString(num, String.valueOf(taa.getText()));

        editor.apply();

       // if(backpressedtime + 2000 >System .currentTimeMillis()) {
            super.onBackPressed();
            return;
      //  }
 //  backpressedtime = System.currentTimeMillis();
}


    public void btn_remove(View view) {

        editor = shrd .edit();
        editor . remove(num);
      int vv = Integer.parseInt(shrd .getString("coco" , ""));

        z = Integer.parseInt(shrd .getString("count" , ""));

        //////////////////////////////////////////////////
        editor.remove("world"+(vv+1));
        for (int n =(vv+1); n<= (z+1)  ; ++n ) {
            String  num3  = shrd .getString("item"+(n),"");
            editor . putString("item"+(n-1), num3);
        }

        z-- ;
        editor . putString("count",  String.valueOf(z));
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

        PopupMenu popupMenu = new PopupMenu(this , view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_book);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
       // case R.id.item : bbbbb = "Arabic" ;
     //   textViewvv.setText("ترجمه الي اللغة العربيه");
      //  return true ;
      //  case R.id.item2 : bbbbb = "";
      //  textViewvv.setText("ترجمه الي اللغة الانجليزيه");
      ///  return true ;
       // case R.id.btn_save9 : btn_save9();
      //  return true ;
      //  default :
        return false ;
    }
}