package com.joshuarichardson.dependencyinjection;

import com.joshuarichardson.dependencyinjection.Modules.WellbeingDatabase;

import javax.inject.Inject;

public class AnalyticsAdapter {

    private final String somethingCool;
    private final WellbeingDatabase database;
    private final String somethingElse;
    private AnalyticsService value;

    @Inject
    public AnalyticsAdapter(AnalyticsService somethingCool, WellbeingDatabase database) {
        this.value = somethingCool;
        this.somethingCool = "Hello";
        this.database = database;
        this.somethingElse = somethingCool.getName();
    }

    public String getSomethingCool() {
        return this.somethingCool;
    }

    public String getSomethingElse() {
        return this.somethingElse;
    }
}