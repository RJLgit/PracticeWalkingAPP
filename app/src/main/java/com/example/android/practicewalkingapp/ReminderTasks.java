package com.example.android.practicewalkingapp;

import android.content.Context;
import com.example.android.practicewalkingapp.NotificationUtils;

public class ReminderTasks {
    public static final String ACTION_DISMISS_NOT = "dismiss-notification";
    public static final String ACTION_CHARGING_REMINDER = "charging-reminder";

    public static void exectueTask(Context context, String action) {
        if (ACTION_DISMISS_NOT.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_CHARGING_REMINDER.equals(action)) {
            issueChargingReminder(context);
        }
    }
    private static void issueChargingReminder(Context context) {
        NotificationUtils.remindUser(context);
    }

}
