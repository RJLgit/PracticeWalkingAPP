package com.example.android.practicewalkingapp;

import android.content.Context;
import com.example.android.practicewalkingapp.NotificationUtils;

public class ReminderTasks {
    public static final String ACTION_DISMISS_NOT = "dismiss-notification";
    public static final String ACTION_CHARGING_REMINDER = "charging-reminder";

    public static void exectueTask(Context context, String action) {
        if (action.equals(ACTION_DISMISS_NOT)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (action.equals(ACTION_CHARGING_REMINDER)) {
            issueChargingReminder(context);
        }
    }
    private static void issueChargingReminder(Context context) {
        NotificationUtils.remindUser(context);
    }

}
