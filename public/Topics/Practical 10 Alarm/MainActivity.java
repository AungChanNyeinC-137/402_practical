package com.example.notifyme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private Button mNotifyButton;
    private Button mUpdateButton;
    private Button mCancelButton;

    private NotificationManager mNotifyManager;

    private static final int NOTIFICATION_ID = 0;
    private static final String CHANNEL_ID = "primary_notification_channel";

    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.notifyme.ACTION_UPDATE_NOTIFICATION";

    private static final String NOTIFICATION_GUIDE_URL =
            "https://developer.android.com/develop/ui/views/notifications";

    private NotificationReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotifyButton = findViewById(R.id.notify);
        mUpdateButton = findViewById(R.id.update);
        mCancelButton = findViewById(R.id.cancel);

        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        createNotificationChannel();

        mNotifyButton.setEnabled(true);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(false);

        mReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter(ACTION_UPDATE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(mReceiver, filter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(mReceiver, filter);
        }

        mNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });
    }

    public void sendNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent notificationPendingIntent =
                PendingIntent.getActivity(
                        this,
                        NOTIFICATION_ID,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );

        Intent learnMoreIntent =
                new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));

        PendingIntent learnMorePendingIntent =
                PendingIntent.getActivity(
                        this,
                        1,
                        learnMoreIntent,
                        PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
                );

        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        updateIntent.setPackage(getPackageName());

        PendingIntent updatePendingIntent =
                PendingIntent.getBroadcast(
                        this,
                        2,
                        updateIntent,
                        PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
                );

        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("You've been notified!")
                        .setContentText("This is your notification text.")
                        .setSmallIcon(R.drawable.ic_android)
                        .setContentIntent(notificationPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setAutoCancel(true)
                        .addAction(R.drawable.ic_learn_more, "Learn More", learnMorePendingIntent)
                        .addAction(R.drawable.ic_update, "Update", updatePendingIntent);

        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);

        mNotifyButton.setEnabled(false);
        mUpdateButton.setEnabled(true);
        mCancelButton.setEnabled(true);
    }

    public void updateNotification() {
        Bitmap androidImage =
                BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);

        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent notificationPendingIntent =
                PendingIntent.getActivity(
                        this,
                        NOTIFICATION_ID,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );

        Intent learnMoreIntent =
                new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));

        PendingIntent learnMorePendingIntent =
                PendingIntent.getActivity(
                        this,
                        1,
                        learnMoreIntent,
                        PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
                );

        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("You've been notified!")
                        .setContentText("This is your notification text.")
                        .setSmallIcon(R.drawable.ic_android)
                        .setContentIntent(notificationPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setAutoCancel(true)
                        .setStyle(
                                new NotificationCompat.BigPictureStyle()
                                        .bigPicture(androidImage)
                                        .setBigContentTitle("Notification Updated!")
                        )
                        .addAction(R.drawable.ic_learn_more, "Learn More", learnMorePendingIntent);

        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        mNotifyButton.setEnabled(false);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(true);
    }

    public void cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID);

        mNotifyButton.setEnabled(true);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(false);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Notify Me",
                            NotificationManager.IMPORTANCE_HIGH
                    );

            channel.setDescription("Notifications from Notify Me");
            mNotifyManager.createNotificationChannel(channel);
        }
    }

    public class NotificationReceiver extends BroadcastReceiver {
        public NotificationReceiver() {}

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_UPDATE_NOTIFICATION.equals(intent.getAction())) {
                updateNotification();
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
