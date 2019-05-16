package com.katana.lowbatterywarning.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

                //TODO start the service to receive boot events

            }

        } catch (Exception e) {

            Toast.makeText(context,"Error starting the service after boot",Toast.LENGTH_LONG).show();

        }

    }

}