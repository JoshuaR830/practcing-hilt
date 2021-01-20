package com.joshuarichardson.dependencyinjection.Modules;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface NameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(DatabaseEntity activityRecord);

    @Query("SELECT * FROM `table`")
    List<DatabaseEntity> getEntities();
}
