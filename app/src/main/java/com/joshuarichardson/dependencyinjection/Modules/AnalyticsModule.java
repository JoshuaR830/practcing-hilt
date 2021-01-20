package com.joshuarichardson.dependencyinjection.Modules;

import com.joshuarichardson.dependencyinjection.AnalyticsService;
import com.joshuarichardson.dependencyinjection.AnalyticsServiceImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class) // To inject into activity
public abstract class AnalyticsModule {
    @Binds
    public abstract AnalyticsService bindAnalyticsService(AnalyticsServiceImpl analyticsServiceImpl);
}
