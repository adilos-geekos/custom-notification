package com.elmansouri.ccna.customnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;

import java.net.MalformedURLException;

public class AdilNotification {
    static public PendingIntent getPending(Context context){
        Intent main = new Intent(context,CalledNotification.class);
        return PendingIntent.getActivity(context,2019,main,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void setNoti(Context context) throws MalformedURLException {
        RemoteViews viewTop = new RemoteViews(context.getPackageName(),R.layout.notification_top);
        RemoteViews viewBas = new RemoteViews(context.getPackageName(),R.layout.notification_bas);
        viewBas.setTextColor(R.id.play,context.getResources().getColor(R.color.colorPrimary));
        viewBas.setImageViewResource(R.id.image,R.drawable.notification);
        Intent I = new Intent(context,AdilBroadcaster.class);
        PendingIntent P = PendingIntent.getBroadcast(context,20,I,PendingIntent.FLAG_UPDATE_CURRENT);
        viewBas.setOnClickPendingIntent(R.id.play,P);
        viewBas.setOnClickPendingIntent(R.id.annuler,P);
        NotificationManager n = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // in oreo you cannot use notification without channle
            NotificationChannel nc = new NotificationChannel(
                    "12",
                    "channel name is Adil",
                    NotificationManager.IMPORTANCE_HIGH
            );
            n.createNotificationChannel(nc);
        }

        // to use notification vibrate add this in permission
        //<uses-permission android:name="android.permission.VIBRATE"/>
        Bitmap largeIc = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_ondemand_video_black_24dp);
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_video_call_black_24dp);
        NotificationCompat.Builder NB = new NotificationCompat.Builder(context,"12")
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_video_call_black_24dp)
                .setLargeIcon(largeIc)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(getPending(context))
                .setCustomContentView(viewTop)
                .setCustomBigContentView(viewBas)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                // this to make sure that the notification goes away when i click on it.
                .setAutoCancel(true);


        // we set priotiy in Oreo using channel but devices not using oreo don't use channel so do this for devices < Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            NB.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        // notification ID is 2018 it is not 12 which id of channel
        n.notify(2018,NB.build());

    }


}
