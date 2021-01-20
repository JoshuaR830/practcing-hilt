package com.joshuarichardson.dependencyinjection;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;
import dagger.hilt.android.testing.HiltTestApplication;

// https://waihein.medium.com/using-mockito-and-dagger-2-for-mocking-and-dependency-injection-in-android-kotlin-integration-ui-245b2230f77a
// https://developer.android.com/training/dependency-injection/hilt-testing
public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return super.newApplication(cl, HiltTestApplication.class.getName(), context);
    }
}
