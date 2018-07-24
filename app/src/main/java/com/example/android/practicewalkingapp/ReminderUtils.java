package com.example.android.practicewalkingapp;

import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class ReminderUtils {

    private static boolean sInitialized = false;
    synchronized public static void scheduleChargingReminder(final Context context) {
        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job myJob = dispatcher.newJobBuilder()
                .setService(ReminderFirebaseJobService.class)
                .setRecurring(true)
                .setTag("reminder_tag")
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setReplaceCurrent(true)
                .setTrigger(Trigger.executionWindow(1, 2))
                .build();

        dispatcher.schedule(myJob);
        sInitialized = true;
    }

}
