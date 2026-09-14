package com.example.standup;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 0;
    private static final String CHANNEL_ID =
            "stand_up_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager =
                (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel(notificationManager);

        Intent contentIntent =
                new Intent(context, MainActivity.class);

        PendingIntent contentPendingIntent =
                PendingIntent.getActivity(
                        context,
                        NOTIFICATION_ID,
                        contentIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stand_up)
                        .setContentTitle(
                                context.getString(R.string.notification_title)
                        )
                        .setContentText(
                                context.getString(R.string.notification_text)
                        )
                        .setContentIntent(contentPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

        notificationManager.notify(
                NOTIFICATION_ID,
                builder.build()
        );
    }

    private void createNotificationChannel(
            NotificationManager notificationManager
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Stand Up Reminder",
                            NotificationManager.IMPORTANCE_HIGH
                    );

            channel.setDescription(
                    "Reminds you to stand up and walk around"
            );

            notificationManager.createNotificationChannel(channel);
        }
    }
}
