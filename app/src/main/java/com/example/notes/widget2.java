package com.example.notes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class widget2 extends AppWidgetProvider {

    String number;
    SharedPreferences shrd;
    SharedPreferences.Editor editor;

    TextView textView  ;


    @Override
    public void onUpdate(Context context , AppWidgetManager appWidgetManager ,
                         int [] appWidgetIds){


                ComponentName thisWidget = new ComponentName(context ,
               widget2.class);
        int [] allWidgetIds = appWidgetManager .getAppWidgetIds(thisWidget);

        for (int widgetId :allWidgetIds  ){



            shrd = context.getSharedPreferences("save file", Context.MODE_PRIVATE);
            editor = shrd.edit();






            number = shrd.getString("widget2" ,"قم بتحديد نوته اولا او استخدم ال  widget الاخري");


            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.activity_widget2);


            remoteViews.setTextViewText(R.id.updatee , String.valueOf(number));


            remoteViews.setInt(R.id.layyyy , "setBackgroundResource", R.drawable.widgetwalldark);


            Intent intent = new Intent(context , widget2 .class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS , appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.updatee , pendingIntent);

            appWidgetManager.updateAppWidget(widgetId,remoteViews);


        }
    }
}