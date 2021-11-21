package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;

import org.jetbrains.annotations.NotNull;

import java.security.Permission;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Amr_bage extends AppCompatActivity {

    int code ;
    String strphonetype  ="" ;
    String info ;
    public final int PEARMITSION = 123 ;

    EditText editText ;
    SharedPreferences shrd;
    SharedPreferences.Editor editor ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amr_bage);

        getWindow().setStatusBarColor(ContextCompat.getColor(Amr_bage.this, R.color.white100));

        editText = findViewById(R.id.editTextTextPassword2);

        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        editor = shrd.edit();

        if (shrd.getString("management_pass" , "no") .equals("L2065189")){

            Intent i = new Intent(Amr_bage . this , Management.class);
            startActivity(i);

            finish();

        }
    }

    public void Amr_profile(View view) {

        if (editText.getText().toString().equals("L2065189")){

            editor.putString("management_pass" , "L2065189");
            editor.apply();

            Intent i = new Intent(Amr_bage . this , Management.class);
            startActivity(i);
            finish();

        }else {

            Toast.makeText(this, "هزا خاص فقط بالاداره", Toast.LENGTH_SHORT).show();

        }
    }

    public void gmail(View view) {
        int permisioncheek = ContextCompat.checkSelfPermission(this , Manifest.permission.READ_PHONE_STATE);

        if (permisioncheek == PackageManager.PERMISSION_GRANTED){

            mytelephonemanged();

        }else {

            ActivityCompat.requestPermissions(this ,
                        new String[] {Manifest.permission.READ_PHONE_STATE }  ,PEARMITSION );

       }
     }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PEARMITSION:
            {
             if(grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                 mytelephonemanged();

             }  else {

                 Toast.makeText(this, "wdwdwdwd", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private void mytelephonemanged() {


        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        int phonetype = manager.getPhoneType();

        switch (phonetype){

            case (TelephonyManager.PHONE_TYPE_CDMA) : strphonetype = "CDMA";break;
            case (TelephonyManager.PHONE_TYPE_GSM) : strphonetype = "GSM";break;
            case (TelephonyManager.PHONE_TYPE_NONE) : strphonetype = "NONE";break;


        }

        boolean isRoaming = manager.isNetworkRoaming();

        String getsimnumber = manager.getVoiceMailNumber();
        String getsimnumberr = manager.getSimSerialNumber();

        info+="\n Voice Mail Number: "+ getsimnumber ;


        Toast.makeText(this,info + getsimnumberr, Toast.LENGTH_SHORT).show();

    }
}