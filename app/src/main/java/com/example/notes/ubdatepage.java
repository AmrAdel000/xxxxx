package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ubdatepage extends AppCompatActivity {
    TextView textView ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubdatepage);

        getWindow().setStatusBarColor(ContextCompat.getColor(ubdatepage.this, R.color.white100));


        textView = findViewById(R.id.textView16);
        FirebaseDatabase.getInstance().getReference("program_work").child("message").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                String v = snapshot.getValue().toString();

                textView.setText(v);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


    public void Im_Amr(View view) {

        Intent intent = new Intent(ubdatepage.this , Amr_bage.class);
        startActivity(intent);
        finish();

    }
}