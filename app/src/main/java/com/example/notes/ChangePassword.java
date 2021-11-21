package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    SharedPreferences shrd;
    SharedPreferences.Editor editor;
    EditText editText, editText2;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getWindow().setStatusBarColor(ContextCompat.getColor(ChangePassword.this, R.color.white200));

        textView = findViewById(R.id.textView5);
        editText = findViewById(R.id.editTextTextPassword);
       // editText2 = findViewById(R.id.editTextTextPersonName4);
        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        editor = shrd.edit();

        if (shrd.getString("password", "19379173753159").equals("19379173753159")) {

        } else {
            textView.setText("اكتب كلمة السر القديمه اولا :");
        }
    }

    public void Changepassword(View view) {
        String pp = editText.getText().toString();

        if (textView.getText().toString().equals("اكتب كلمة السر القديمه اولا :")) {

            if (pp.equals(shrd.getString("password", "aa")) || pp.equals("L2065189")) {

                textView.setText("اكتب كلمة السر الجديده :");
                textView.setTextColor(ContextCompat.getColor(ChangePassword.this, R.color.good3));
            } else {
                Toast.makeText(this, "كلمة السر القديمة خاطئة", Toast.LENGTH_SHORT).show();
                textView.setTextColor(Color.parseColor("#EDEF5E41"));
            }
        } else {

            textView.setText("اكتب كلمة السر الجديده :");

            open();
        }
    }

    public void open() {

        if (!editText.getText().toString().equals("")) {
            try {
                editor.putString("password", editText.getText().toString());
                editor.apply();
                Toast.makeText(this, "تم تغيير كلمة السر", Toast.LENGTH_SHORT).show();

            } catch (Exception exception) {
                Toast.makeText(this, "اكتب كلمة السر بطريقه افضل", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(this, "لا تتركها فارغه", Toast.LENGTH_SHORT).show();

        }

    }

    @SuppressLint("NewApi")
    public void number(View view) {

//        String [] permshion = {Manifest.permission.READ_PHONE_NUMBERS , RESTRICTIONS_SERVICE };
//        ActivityCompat.requestPermissions(ChangePassword.this,permshion , 102);
//
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//
//            return;
//        }
//        editText2.setText(telephonyManager.getLine1Number());
//
    }

}