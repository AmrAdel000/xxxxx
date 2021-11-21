package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class edit_post extends AppCompatActivity {
    SharedPreferences shrd  ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        editor = shrd .edit();

    }


    public void delete_post(View view) {


        String position =  shrd.getString("position" , "0");

        remove(position);

    }

    public void edit_post(View view) {

        Intent i = new Intent(edit_post.this , puplish.class);
        startActivity(i);
        finish();
    }

    public void remove (String position ){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("post").child("posts").orderByChild("id").equalTo(position);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {

                    appleSnapshot.getRef().removeValue();
                    // appleSnapshot.getRef().setValue("axa");
                }
                editor.putString("position" , "0");
                editor.apply();
                Toast.makeText(edit_post.this, "تم الحذف", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    @Override
    public void onBackPressed() {
        editor.putString("position" , "0");
        editor.apply();
        super.onBackPressed();
    }
}