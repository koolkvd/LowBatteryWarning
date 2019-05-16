package com.katana.lowbatterywarning.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.katana.lowbatterywarning.R;
import com.katana.lowbatterywarning.services.PowerDetectionForegroundService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.title);

        TextView startService = findViewById(R.id.startService);

        TextView stopService = findViewById(R.id.stopService);

        Typeface pacifico = Typeface.createFromAsset(getAssets(),"fonts/Pacifico-Regular.ttf");

        title.setTypeface(pacifico);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(MainActivity.this, PowerDetectionForegroundService.class);

                startForegroundService(startIntent);

            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent stopIntent = new Intent(MainActivity.this, PowerDetectionForegroundService.class);

                stopService(stopIntent);

            }
        });

    }
}
