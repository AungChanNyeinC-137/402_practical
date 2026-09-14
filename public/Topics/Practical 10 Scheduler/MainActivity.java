package com.example.standup;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 0;

    public static final String ACTION_NOTIFY =
            "com.example.standup.ACTION_NOTIFY";

    private ToggleButton alarmToggle;
    private AlarmManager alarmManager;
    private NotificationManager notificationManager;
    private PendingIntent notifyPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmToggle = findViewById(R.id.alarmToggle);

        alarmManager =
                (AlarmManager) getSystemService(ALARM_SERVICE);

        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent notifyIntent =
                new Intent(this, AlarmReceiver.class);

        notifyIntent.setAction(ACTION_NOTIFY);

        boolean alarmUp =
                PendingIntent.getBroadcast(
                        this,
                        NOTIFICATION_ID,
                        notifyIntent,
                        PendingIntent.FLAG_NO_CREATE | PendingIntent.FLAG_IMMUTABLE
                ) != null;

        alarmToggle.setChecked(alarmUp);

        notifyPendingIntent =
                PendingIntent.getBroadcast(
                        this,
                        NOTIFICATION_ID,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );

        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton compoundButton,
                            boolean isChecked
                    ) {
                        String toastMessage;

                        if (isChecked) {
                            long triggerTime =
                                    SystemClock.elapsedRealtime()
                                            + AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                            long repeatInterval =
                                    AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                            alarmManager.setInexactRepeating(
                                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                    triggerTime,
                                    repeatInterval,
                                    notifyPendingIntent
                            );

                            toastMessage = getString(R.string.alarm_on_toast);
                        } else {
                            alarmManager.cancel(notifyPendingIntent);
                            notificationManager.cancelAll();

                            toastMessage = getString(R.string.alarm_off_toast);
                        }

                        Toast.makeText(
                                MainActivity.this,
                                toastMessage,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );
    }
}
