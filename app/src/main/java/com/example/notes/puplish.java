package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.model.Module;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class puplish extends AppCompatActivity {
    SharedPreferences shrd;
    EditText title ;EditText published ;
    String dated ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puplish);

        getWindow().setStatusBarColor(ContextCompat.getColor(puplish.this, R.color.white200));

        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);

        title = findViewById(R.id.editTextTextPersonName7);
        published = findViewById(R.id.editTextTextPersonName6);

        String date = new SimpleDateFormat("hh" +":"+ "mm", Locale.getDefault()).format(new Date());
        SimpleDateFormat ss = new SimpleDateFormat("EEE" );
        Date D = new Date();
        String vv = ss.format(D);

         dated = vv +" الساعه " + date ;


    }

    public void puplish(View view) {
//        DatabaseReference root = FirebaseDatabase.getInstance().getReference("Students").child("title");
//        DatabaseReference root2 = FirebaseDatabase.getInstance().getReference("Students").child("pupleshed");
//
//        root.push().setValue(title.getText().toString());
//        root2.push().setValue(published.getText().toString());
        ///////////////////////////////////////////////////

        if(shrd.getString("password", "aa").equals("00112200")) {
            SharedPreferences shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shrd.edit();

            if (!shrd.getString("position", "0").equals("0")) {

                editt(shrd.getString("position", "0"));

            } else {


                if (published.getText().toString().isEmpty() || title.getText().toString().isEmpty()) {

                    Toast.makeText(this, "ادخل العنوان و المنشور معا", Toast.LENGTH_SHORT).show();

                } else {


                    DatabaseReference mood = FirebaseDatabase.getInstance().getReference("post").child("posts").push();

                    Module module = new Module("Amr", title.getText().toString(), published.getText().toString(), mood.getKey().toString(), dated);

                    mood.setValue(module);

                    Toast.makeText(this, "تم نشر المنشور", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        }else {
            Toast.makeText(this, "يمكن للمندوبين فقط النشر", Toast.LENGTH_SHORT).show();
        }
    }

    public void editt (String position){
        SharedPreferences shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("post").child("posts").orderByChild("id").equalTo(position);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Module module = new Module("Amr", title.getText().toString(), published.getText().toString(), shrd.getString("position", "0") , dated);

                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().setValue(module);
                }

                Toast.makeText(puplish.this, "تم تعديل المنشور", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(puplish.this, "scscsc", Toast.LENGTH_SHORT).show();
            }
        });
    }


}