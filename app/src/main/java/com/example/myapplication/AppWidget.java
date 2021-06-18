package com.example.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWigetid: appWidgetIds){
            Intent LaunchingIntent= new Intent(context,MainActivity.class);
            PendingIntent pendingIntent= PendingIntent.getActivity(context,0,LaunchingIntent,0);
            RemoteViews remoteViews= new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            remoteViews.setOnClickPendingIntent(R.id.tv_widget,pendingIntent);
            appWidgetManager.updateAppWidget(appWigetid,remoteViews);
        }
    }
}
