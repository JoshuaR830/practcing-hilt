package com.joshuarichardson.dependencyinjection;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class TestAnalyticsModule {
    @Binds
//    @Named("test_analytics")
    public abstract AnalyticsService bindAnalyticsService(AnalyticsServiceTestImpl analyticsServiceImpl);
}
