package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Alarrm3 extends AppCompatActivity {

    NumberPicker picker1 , picker2 , picker3 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarrm3);

        Toast.makeText(Alarrm3.this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();


        picker1=findViewById(R.id.numpicker1);picker2=findViewById(R.id.numpicker2);picker3=findViewById(R.id.numpicker3);

        picker1.setMinValue(0);  picker2.setMinValue(0);  picker3.setMinValue(0);
        picker1.setMaxValue(59); picker2.setMaxValue(59); picker3.setMaxValue(59);
        picker1.setValue(10);   picker2.setValue(11);   picker3.setValue(12);


    }
}