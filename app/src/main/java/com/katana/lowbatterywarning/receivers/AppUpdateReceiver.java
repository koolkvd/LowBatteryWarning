package com.katana.lowbatterywarning.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.katana.lowbatterywarning.services.PowerDetectionForegroundService;

public class AppUpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_MY_PACKAGE_REPLACED)) {

                Intent startIntent = new Intent(context, PowerDetectionForegroundService.class);

                context.startForegroundService(startIntent);

            }

        } catch (Exception e) {

            Toast.makeText(context,"Error starting the service after app update",Toast.LENGTH_LONG).show();

        }

    }

}