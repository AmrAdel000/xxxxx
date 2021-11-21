package com.example.notes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class Note_widget extends AppWidgetProvider {

    int s  , v , m; String number;
    SharedPreferences shrd;
    SharedPreferences.Editor editor;
    ArrayList<String> araay2 ;    ArrayList<String> str ;   ArrayList<String> array  ;


    @Override
    public void onUpdate(Context context , AppWidgetManager appWidgetManager ,
                         int [] appWidgetIds){


        ComponentName thisWidget = new ComponentName(context ,
                Note_widget.class);
        int [] allWidgetIds = appWidgetManager .getAppWidgetIds(thisWidget);

        for (int widgetId :allWidgetIds  ){


            array = new ArrayList<>();araay2 = new ArrayList<>();str = new ArrayList<>();

            //int number = (new Random().nextInt(100));
              shrd = context.getSharedPreferences("trans file", Context.MODE_PRIVATE);
              editor = shrd.edit();

              s = Integer.parseInt(shrd.getString("counted", ""));
              v = Integer.parseInt(shrd.getString("widget", "0"));

            for (int x = 1 ; x <= s ; ++x){

                str . add(String.valueOf(x));
                array.add(shrd .getString("world"+x , "")) ;
                araay2.add(shrd .getString("worldd"+x , "")) ;
            }

            if (v <= s) {
                number = array.get(v) + "     =     " + araay2.get(v);  v++;
                if (v == s){ v=0;
                    Toast.makeText(context, "تم اكمال دوره كامله", Toast.LENGTH_SHORT).show();
                }
            }

                editor.putString("widget" , String.valueOf(v));  editor.apply();

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.note_widget);
            Log.w("widgetttt" , String.valueOf(number));
            remoteViews.setTextViewText(R.id.update , String.valueOf(number));

            Intent intent = new Intent(context , Note_widget .class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS , appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update , pendingIntent);
            appWidgetManager.updateAppWidget(widgetId,remoteViews);


        }



    }

}