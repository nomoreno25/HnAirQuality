package com.hailv.hnairquality.fcm;

import android.app.Notification;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hailv.hnairquality.R;
import com.hailv.hnairquality.mApplicationContext;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Notification notification = new NotificationCompat.Builder(mApplicationContext.getAppContext())
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSmallIcon(R.drawable.icon_airq)
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(mApplicationContext.getAppContext());
        manager.notify(123, notification);

    }

}
