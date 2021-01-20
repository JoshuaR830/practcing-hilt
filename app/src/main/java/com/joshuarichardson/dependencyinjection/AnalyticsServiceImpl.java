package com.joshuarichardson.dependencyinjection;

import android.util.Log;

import javax.inject.Inject;

public class AnalyticsServiceImpl implements AnalyticsService {
    @Inject
    AnalyticsServiceImpl() {
        Log.d("Hello", "Hi");
    }

    @Override
    public String getName() {
        return "Real";
    }
}
