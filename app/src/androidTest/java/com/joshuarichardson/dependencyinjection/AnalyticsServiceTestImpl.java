package com.joshuarichardson.dependencyinjection;

import android.util.Log;

import javax.inject.Inject;

public class AnalyticsServiceTestImpl implements AnalyticsService {
    @Inject
    AnalyticsServiceTestImpl() {
        Log.d("Roar", "Grr");
    }

    @Override
    public String getName() {
        return "Test";
    }
}
