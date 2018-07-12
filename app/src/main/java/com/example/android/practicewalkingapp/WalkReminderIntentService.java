package com.example.android.practicewalkingapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class WalkReminderIntentService extends IntentService {
    public WalkReminderIntentService() {
        super("WalkReminderIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        ReminderTasks.exectueTask(this, action);
    }
}
