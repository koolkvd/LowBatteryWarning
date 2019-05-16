package com.katana.lowbatterywarning.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.katana.lowbatterywarning.utils.Const;

public class PowerConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)) {

                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

                notificationManager.cancel(Const.NOTIFICATION_ID);

            }

        } catch (Exception e) {

            Toast.makeText(context,"Exception in Low Battery Receiver",Toast.LENGTH_LONG).show();

        }

    }

}