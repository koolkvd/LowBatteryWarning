package com.katana.lowbatterywarning.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.katana.lowbatterywarning.R;
import com.katana.lowbatterywarning.activities.MainActivity;
import com.katana.lowbatterywarning.receivers.LowBatteryReceiver;
import com.katana.lowbatterywarning.utils.Const;

public class PowerDetectionForegroundService extends Service {

    @Override
    public void onCreate(){

        LowBatteryReceiver lowBatteryReceiver = new LowBatteryReceiver();

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_LOW);

        registerReceiver(lowBatteryReceiver,ifilter);

        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationChannel notificationChannel = new NotificationChannel("LowBatteryForegroundService","Foreground Service", NotificationManager.IMPORTANCE_DEFAULT);

        notificationChannel.enableLights(true);

        notificationChannel.setLightColor(Color.BLUE);

        notificationChannel.setDescription("Sticky foreground service notification is shown via this channel");

        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this

        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        notificationManager.createNotificationChannel(notificationChannel);

        Notification notification = new Notification.Builder(this, "LowBatteryForegroundService")
                        .setContentTitle("Low Battery Detector")
                        .setContentText("Low battery detection service is running")
                        .setSmallIcon(R.drawable.ic_notif_icon)
                        .setColor(ContextCompat.getColor(this,R.color.colorAccent))
                        .setContentIntent(pendingIntent)
                        .build();

        startForeground(Const.NOTIFICATION_ID_SERVICE,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
