package com.example.android.practicewalkingapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationUtils {
    private final static int REMINDER_ID = 123;
    private final static String CHANNEL_ID = "Cool channel";
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, REMINDER_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    public static void remindUser(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, context.getString(R.string.main_notification_channel_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Remember to Walk")
                .setContentText("Walking is good for you")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(NotificationUtils.contentIntent(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(REMINDER_ID, builder.build());
    }
    public static void clearAllNotifications(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    nm.cancelAll();

    }
    private static NotificationCompat.Action ignoreReminderAction(Context context){
       Intent ignoreReminderIntent = new Intent(context, WalkReminderIntentService.class);
       ignoreReminderIntent.setAction(ReminderTasks.ACTION_DISMISS_NOT);
       PendingIntent ignorePendingIntent = PendingIntent.getService(context, ACTION_IGNORE_PENDING_INTENT_ID, ignoreReminderIntent, PendingIntent.FLAG_CANCEL_CURRENT);
       NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px, "No", ignorePendingIntent);
       return ignoreReminderAction;
    }

}
