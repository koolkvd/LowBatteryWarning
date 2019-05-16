package com.katana.lowbatterywarning.receivers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.katana.lowbatterywarning.R;
import com.katana.lowbatterywarning.utils.Const;

public class LowBatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_BATTERY_LOW)) {

                NotificationChannel notificationChannel = new NotificationChannel("LowBatteryAlert","Low Battery Alert", NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.enableLights(true);

                notificationChannel.setLightColor(Color.BLUE);

                notificationChannel.enableVibration(true);

                notificationChannel.setVibrationPattern(new long[]{0,100,100,100});

                notificationChannel.setDescription("Low battery alerts are shown on this channel");

                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this

                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

                notificationManager.createNotificationChannel(notificationChannel);

                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                try {

                    //Play sound for low battery

                    Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), defaultSoundUri);

                    r.play();

                } catch (Exception e) {
                    //Do nothing
                }

                //Setting ongoing true to prevent dismiss

                Notification notification = new Notification.Builder(context, "LowBatteryAlert")
                        .setContentTitle("Low Battery!!")
                        .setContentText("Phone is running on low battery!!")
                        .setSmallIcon(R.drawable.ic_notif_icon_low_battery)
                        .setAutoCancel(false)
                        .setColor(ContextCompat.getColor(context,R.color.red_indicator))
                        .setOngoing(true)
                        .build();

                notificationManager.notify(Const.NOTIFICATION_ID,notification);

            }else if(intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_BATTERY_OKAY)){

                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

                notificationManager.cancel(Const.NOTIFICATION_ID);

            }

        } catch (Exception e) {

            Toast.makeText(context,"Exception in Low Battery Receiver",Toast.LENGTH_LONG).show();

        }

    }

}
