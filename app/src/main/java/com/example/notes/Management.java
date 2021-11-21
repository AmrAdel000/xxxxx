package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.model.SssssS;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Management extends AppCompatActivity {

    EditText editText , editversion ;
    SharedPreferences shrd ;
    ArrayList<String>  notes , title , lock ;
    int z ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);


        getWindow().setStatusBarColor(ContextCompat.getColor(Management.this, R.color.white100));

        notes = new ArrayList<>();
        title = new ArrayList<>();
        lock  = new ArrayList<>();


        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);

        editText = findViewById(R.id.editTextTextPersonName5);
        editversion = findViewById( R.id.editTextPhone);

    }
    public void resume(View view) {

    DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("program_work").child("work_or_not");
    reference. setValue(editversion.getText().toString());
    Toast.makeText(this, "تم الغاء ايقاف البرنامج", Toast.LENGTH_SHORT).show();

    }

    public void stop(View view) {

        if(editversion.getText().toString().isEmpty() || editText.getText().toString().isEmpty()){

            Toast.makeText(this, "ضع رقم الاصدار الجديد و رابط التحديث", Toast.LENGTH_SHORT).show();

        }else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("program_work").child("work_or_not");
            reference.setValue(editversion.getText().toString());
            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("program_work").child("message");
            reference2.setValue(editText.getText().toString());
            Toast.makeText(this, "تم ارسال التحديث", Toast.LENGTH_SHORT).show();
        }
    }

    public void all_words(View view) {
        Intent intent2 = new Intent(this , firebas_words.class);
        startActivity(intent2);
    }

    public void Long_out(View view) {


        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("management_pass" , "no");
        editor.apply();
        finish();

    }

    public void savenotes(View view) {
        int z = Integer.parseInt(shrd.getString("count", "0"));

        for (int x = 0; x <= (z-1); ++x) {

            DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("Users").child("Amr");
            SssssS notss = new SssssS(shrd.getString("itemm" +(z-x) , "لا يوجد عنوان") ,shrd.getString("item" + (z-x), "hghghghgh") , shrd.getString( "lock" + (z-x) ,"no") , String.valueOf(x)) ;
            reference.push().setValue(notss);

        }
    }


    public void getnots(View view) {

        FirebaseDatabase.getInstance().getReference("Users").child("Amr").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                z = (int) snapshot.getChildrenCount();

                for(DataSnapshot snapshot1 : snapshot.getChildren()){

                    SssssS module = snapshot1.getValue(SssssS.class);
//
                    title.add(String.valueOf(module.getName()));
                    notes.add(String.valueOf(module.getTitle()));
                    lock.add(String.valueOf(module.getPost()));
                }
                showf();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    private void showf() {

        SharedPreferences.Editor editor = shrd.edit();

        for (int x = 0; x <= (z-1); ++x) {

            editor.putString("item" +  (z+1-(x+1)), notes.get(x));
            editor.putString( "itemm"+ (z+1-(x+1)), title.get(x));
            editor.putString( "lock" + (z+1-(x+1)), lock.get(x));
            editor.putString( "count", String.valueOf(z));

       }
        Toast.makeText(this, String.valueOf(title.size()) , Toast.LENGTH_SHORT).show();

        editor.apply();

    }
}