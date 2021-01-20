package com.joshuarichardson.dependencyinjection;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class TestAnalyticsModule {
    @Binds
    public abstract AnalyticsService bindAnalyticsService(AnalyticsServiceTestImpl analyticsServiceImpl);
}
