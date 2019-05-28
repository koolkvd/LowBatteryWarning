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
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.katana.lowbatterywarning.R;
import com.katana.lowbatterywarning.activities.MainActivity;
import com.katana.lowbatterywarning.utils.Const;

public class LowBatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_BATTERY_LOW)) {

                NotificationChannel notificationChannel = new NotificationChannel("LowBatteryAlertChannel","Low Battery Alert", NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.enableLights(true);

                notificationChannel.setLightColor(Color.BLUE);

                notificationChannel.setVibrationPattern(new long[]{0,100,100,100});

                notificationChannel.enableVibration(true);

                notificationChannel.setDescription("Low battery alerts are shown on this channel");

                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this

                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

                notificationManager.createNotificationChannel(notificationChannel);

                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                try {

                    //Play sound for low battery

                    Ringtone r = RingtoneManager.getRingtone(context, defaultSoundUri);

                    r.play();

                } catch (Exception e) {
                    //Do nothing
                }

                try {

                    Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 2000 milliseconds
                    v.vibrate(2000);

                }catch (Exception e){
                    //Do nothing
                }

                //Setting ongoing true to prevent dismiss

                Notification notification = new Notification.Builder(context, "LowBatteryAlertChannel")
                        .setContentTitle("Low Battery!!")
                        .setContentText("Phone is running on low battery!!")
                        .setSmallIcon(R.drawable.ic_notif_icon_low_battery)
                        .setAutoCancel(false)
                        .setColor(ContextCompat.getColor(context,R.color.red_indicator))
                        .setVibrate(new long[]{0,100,100,100})
                        .setOngoing(true)
                        .build();

                notificationManager.notify(Const.NOTIFICATION_ID,notification);

            }

        } catch (Exception e) {

            Toast.makeText(context,"Exception in Low Battery Receiver "+e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }

}
