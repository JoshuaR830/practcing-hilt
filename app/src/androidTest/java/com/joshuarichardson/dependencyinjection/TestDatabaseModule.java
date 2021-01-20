package com.joshuarichardson.dependencyinjection;

import android.content.Context;

import com.joshuarichardson.dependencyinjection.Modules.DatabaseEntity;
import com.joshuarichardson.dependencyinjection.Modules.NameDao;
import com.joshuarichardson.dependencyinjection.Modules.WellbeingDatabase;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@Module
//@InstallIn(ApplicationComponent.class)
//public class TestDatabaseModule {
//    @Provides
//    public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
//        WellbeingDatabase databaseMock = mock(WellbeingDatabase.class);
//        NameDao nameDao = mock(NameDao.class);
//
//        ArrayList<DatabaseEntity> array = new ArrayList<>();
//        array.add(new DatabaseEntity(4));
//
//        when(nameDao.insert(any(DatabaseEntity.class))).thenReturn(0L);
//        when(nameDao.getEntities()).thenReturn(array);
//
//        when(databaseMock.nameDao()).thenReturn(nameDao);
//
//        return databaseMock;
//    }
//}
