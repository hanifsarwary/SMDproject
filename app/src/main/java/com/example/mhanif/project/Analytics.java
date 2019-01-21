package com.example.mhanif.project;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by M.hanif on 4/27/2018.
 */

public class Analytics extends Application {
    public final static String track_id = "UA-118312343-1";
    public static GoogleAnalytics googleAnalytics;
    public Tracker sTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        googleAnalytics = GoogleAnalytics.getInstance(this);

    }

    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = googleAnalytics.newTracker(R.string.ga_trackingid);
        }

        return sTracker;

    }
}