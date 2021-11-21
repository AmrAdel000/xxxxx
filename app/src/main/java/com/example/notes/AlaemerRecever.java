package com.example.notes;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaParser;
import android.media.MediaPlayer;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class AlaemerRecever extends BroadcastReceiver {

    SharedPreferences shrd;
    SharedPreferences.Editor editor;
    MediaPlayer mediaPlayer ;

    @Override
    public void onReceive(Context context, Intent intent) {

        shrd = context.getSharedPreferences("save file", Context.MODE_PRIVATE);
        editor = shrd.edit();

        editor.putString("hour"  ,"no");
        editor.putString("minute","no");
        editor.putString("namec",  shrd.getString("alarm num", "k"));
        editor.putString("namecc", shrd.getString("alarm numm","k"));
        editor.apply();


        mediaPlayer = MediaPlayer.create(context, R.raw.ccccc);
        mediaPlayer.start();

        Intent ssssss = new  Intent (context , Book.class);
        ssssss.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(ssssss);

        Intent i = new Intent(context , Alarm2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent . FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context , 0, i , 0);

        @SuppressLint("ResourceAsColor") NotificationCompat.Builder builder = new NotificationCompat.Builder(context , "foxandroid")
                .setSmallIcon(R.drawable.alarmmm_24)
                .setContentTitle("انتهي الوقت")
                .setContentText("هناك مايجب عليك فعله")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123 , builder .build());
    }
}
