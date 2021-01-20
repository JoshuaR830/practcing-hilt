package com.joshuarichardson.dependencyinjection.Modules;

import android.content.Context;

import com.joshuarichardson.dependencyinjection.DatabaseService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    @Provides
    @Singleton
    public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, WellbeingDatabase.class, "database_name")
                .fallbackToDestructiveMigration()
                .build();
    }
}
