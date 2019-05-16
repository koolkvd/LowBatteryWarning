package com.katana.lowbatterywarning.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            if (intent.getAction() != null && intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)) {

                //TODO dismiss low battery notification if any

            }

        } catch (Exception e) {

            Toast.makeText(context,"Error starting the service after boot",Toast.LENGTH_LONG).show();

        }

    }

}