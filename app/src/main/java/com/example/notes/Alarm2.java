package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.notes.databinding.ActivityAlarm2Binding;
import com.example.notes.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Alarm2 extends AppCompatActivity {

    private ActivityAlarm2Binding binding ;
    private MaterialTimePicker picker ;
    private Calendar calendar ;
    private AlarmManager alarmManager ;
    PendingIntent pendingIntent ;
    String num1 , num2 ;
    int  minut , hour , day;

    SharedPreferences shrd;
    SharedPreferences.Editor editor;
    NumberFormat numperenglish;
    String [] Dayes ;
    int days ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarm2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        numperenglish=NumberFormat.getNumberInstance(new Locale("EN"));

        day = 12;
        Dayes = getResources().getStringArray(R.array.dayes);

        binding.numppp.setMinValue(0);
        binding.numppp.setMaxValue(6);
        binding.numppp.setDisplayedValues(Dayes);

        binding.AlarmText.setText(Dayes[binding.numppp.getValue()]);

        binding.numppp.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
binding.AlarmText.setText(Dayes [i1]);

            switch (Dayes[i1]){

                case  "السبت"   : days = 7 ; break;
                case  "الاحد"    : days = 1 ; break;
                case  "الاثنين"  : days = 2 ; break;
                case  "الثلاثاء" : days = 3 ; break;
                case  "الاربعاء" : days = 4 ; break;
                case  "الخميس"  : days = 5 ; break;
                case  "الجمعة"  : days = 6 ; break;
            }
           }
        });

        creatNotificationchanel();

        binding.textView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtimepicker();
            }
        });

        binding.butStart22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });

binding.botStop2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        cancelAlarm ();
    }
});
    }

    private void cancelAlarm() {

        Intent i = new Intent(this , AlaemerRecever.class);

        pendingIntent = PendingIntent.getBroadcast(this , 0 , i ,0);

        if (alarmManager == null){

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Toast.makeText(this, "تم الغاء التزكير", Toast.LENGTH_SHORT).show();
        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "تم الغاء التزكير", Toast.LENGTH_SHORT).show();

        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        editor = shrd.edit();

        editor.putString("hour"  ,"no");
        editor.putString("minute","no");
        editor.apply();
    }

    private void setAlarm() {



        shrd = getSharedPreferences("save file", Context.MODE_PRIVATE);
        editor = shrd.edit();

        num1  = shrd.getString("namec", "Empty");
        num2  = shrd.getString("namecc","Empty");

        editor.putString("alarm num", num1);
        editor.putString("alarm numm", num2);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this , AlaemerRecever.class);

        pendingIntent = PendingIntent.getBroadcast(this , 0 , i ,0);

        alarmManager .setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
        AlarmManager.INTERVAL_DAY,pendingIntent);
                  // ELAPSED_REALTIME_WAKEUP    جرب دي
        hour  = Integer.parseInt(numperenglish.format(picker.getHour()  ));
        minut = Integer.parseInt(numperenglish.format(picker.getMinute()));

        editor.putString("hour"  ,String.valueOf(hour));
        editor.putString("minute",String.valueOf(minut));
        editor.putString("second",new SimpleDateFormat("ss", Locale.getDefault()).format(new Date()));

        editor.putString("day", String.valueOf(day));
        editor.apply();
        Toast.makeText(this, "تم ضبط التذكير", Toast.LENGTH_SHORT).show();
        toast();}


    private void toast() {

        int num1  = picker.getHour();
        int num2  = picker.getMinute();
        int num3  = Integer.parseInt(shrd.getString("second","1"));

        int hour   = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(new Date()));
        int minet  = Integer.parseInt(new SimpleDateFormat("mm", Locale.getDefault()).format(new Date()));
        int scound = Integer.parseInt(new SimpleDateFormat("ss", Locale.getDefault()).format(new Date()));

        hour   = Integer.parseInt(numperenglish.format(hour));
        minet  = Integer.parseInt(numperenglish.format(minet));
        scound  = Integer.parseInt(numperenglish.format(scound));

        if (num1 > 12 ){

            String total  =  String.valueOf(num1 - hour ) ;
            if (minet > num2) {
                String total2 = String.valueOf( minet- num2);
                String total3 = String.valueOf( scound- num3);
                Toast.makeText(this, String.valueOf(scound) + " : " + total2 + " : " + total, Toast.LENGTH_SHORT).show();

            }else{
                String total2 = String.valueOf(num2 - minet);
                Toast.makeText(this, String.valueOf(scound) + " : " + total2 + " : " + total, Toast.LENGTH_SHORT).show();
            }

        }else {

            String total  =  String.valueOf(num1- hour ) ;
            String total2 =  String.valueOf(num2- minet) ;
            if (minet > num2) {
                total2 = String.valueOf( minet- num2);
                Toast.makeText(this, String.valueOf(scound) + " : " + total2 + " : " + total, Toast.LENGTH_SHORT).show();

            }else{
                total2 = String.valueOf(num2 - minet);
                Toast.makeText(this, String.valueOf(scound) + " : " + total2 + " : " + total, Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void showtimepicker() {

        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)

                .setTitleText("Set Alarm Time")
                .build();
 // هنا هتعمل نفس الي تحت
        picker.show(getSupportFragmentManager(),"foxandroid");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (picker.getHour() > 12) {
//
//                         day = 24 ;
//                    Toast.makeText(Alarm2.this, "اكبر من 12", Toast.LENGTH_SHORT).show();
//                    binding.textView32.setText("م" + " : " + picker.getMinute() + " : " + (picker.getHour()-12));
//                } else {
//                    day = 24 ;
//                    binding.textView32.setText("ص" + " : " + picker.getMinute() + " : " + picker.getHour()); }
//
//                 int  Mainhour   = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(new Date()));
//                 int  Mainminute = Integer.parseInt(new SimpleDateFormat("mm", Locale.getDefault()).format(new Date()));
//
//                if (picker.getHour() < Mainhour || picker.getHour()== Mainhour && picker.getMinute()<Mainminute ) {
//                    calendar = Calendar.getInstance();
//                    calendar.set(calendar.HOUR_OF_DAY, picker.getHour() + day);
//                    calendar.set(Calendar.MINUTE, picker.getMinute());
//                    calendar.set(Calendar.SECOND, 0);
//                    calendar.set(Calendar.MILLISECOND, 0);
//                    Toast.makeText(Alarm2.this, String.valueOf(picker.getHour()+day), Toast.LENGTH_LONG).show();
//
//                } else {
                    calendar = Calendar.getInstance();
                    calendar.set(calendar.HOUR_OF_DAY, picker.getHour());
                    calendar.set(Calendar.MINUTE, picker.getMinute());
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    calendar.set(Calendar.DAY_OF_WEEK , days);
               // }
            }
        });
    }


    private void creatNotificationchanel() {
    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
        CharSequence name = "foxandroidRimindrChanel  amresssst";
        String description = "هناك مايجب عليك فعله";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        //  هنا هتعمل حالة اف من غير فور مثلا لو ال z = 2 اعمل واحد جديد اسمه  name + z

        NotificationChannel channel = new NotificationChannel("foxandroid" , name , importance  );
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
       }
    }

    public void Alarrm3(View view) {
         Intent i = new Intent(Alarm2.this , Alarrm3.class);
         startActivity(i);
    }
    public void menuealarm(View view) {
        Toast.makeText(Alarm2.this, "سيتوفر في التحديث القادم", Toast.LENGTH_SHORT).show();
    }
}