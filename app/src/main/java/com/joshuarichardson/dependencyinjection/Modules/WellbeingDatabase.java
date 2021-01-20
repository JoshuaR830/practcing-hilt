package com.joshuarichardson.dependencyinjection.Modules;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DatabaseEntity.class}, version = 1, exportSchema = false)
public abstract class WellbeingDatabase extends RoomDatabase {
    public abstract NameDao nameDao();
}
