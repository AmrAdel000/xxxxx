package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Test extends AppCompatActivity {

    View vv ;
    EditText textheight;
    EditText textwidth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        vv = findViewById(R.id.Myvew);
        textheight = findViewById(R.id.hight);
        textwidth  = findViewById(R.id.width);

       textheight.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
         vvvvvvv();
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });


       textwidth.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            vvvvvvv();
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

    }

    public void vvvvvvv (){

        if (textheight.getText().toString().equals("")|| textwidth.getText().toString().equals("")){
            Toast.makeText(Test.this , "فارغ" , Toast.LENGTH_SHORT).show();
        }else {

            int hight = Integer.parseInt(String.valueOf(textheight.getText()));
            int width = Integer.parseInt(String.valueOf(textwidth.getText()));
            all(width, hight);

        }
    }

    public int all (int width , int hight) {

       vv.setLayoutParams( new LinearLayout.LayoutParams( width , hight));
//
//        LinearLayout.LayoutParams icon = (LinearLayout.LayoutParams) vv.getLayoutParams();
//      icon.setMargins(100 ,100,100,100);
//      vv.setLayoutParams(icon);

        return width+hight;

    }


}



