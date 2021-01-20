package com.joshuarichardson.dependencyinjection.Modules;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table")
public class DatabaseEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    public DatabaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
