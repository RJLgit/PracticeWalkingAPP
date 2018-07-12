package com.example.android.practicewalkingapp;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class ReminderFirebaseJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        NotificationUtils.remindUser(ReminderFirebaseJobService.this);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
