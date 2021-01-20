package com.joshuarichardson.dependencyinjection;

import android.content.Context;

import com.joshuarichardson.dependencyinjection.Modules.DatabaseEntity;
import com.joshuarichardson.dependencyinjection.Modules.NameDao;
import com.joshuarichardson.dependencyinjection.Modules.WellbeingDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
@InstallIn(ApplicationComponent.class)
public class TestDatabaseModule {
//    @Provides
////    @Named("test_db")
//    public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
//        return Room.inMemoryDatabaseBuilder(context, WellbeingDatabase.class)
//                .allowMainThreadQueries()
//                .build();
//    }
//

    @Provides
//    @Named("test_db")
    public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
        WellbeingDatabase databaseMock = mock(WellbeingDatabase.class);
        NameDao nameDao = mock(NameDao.class);
        
        ArrayList<DatabaseEntity> array = new ArrayList<DatabaseEntity>();
        array.add(new DatabaseEntity(4));

        when(nameDao.insert(any(DatabaseEntity.class))).thenReturn(0L);
        when(nameDao.getEntities()).thenReturn(array);

        when(databaseMock.nameDao()).thenReturn(nameDao);

//        when(databaseMock.nameDao()).thenReturn(new NameDao() {
//            @Override
//            public long insert(DatabaseEntity activityRecord) {
//                return 0;
//            }
//
//            @Override
//            public List<DatabaseEntity> getEntities() {
//                return array;
//            }
//        });


//        when(databaseMock.nameDao().insert(any(DatabaseEntity.class))).thenReturn(0L);
//
//        when(databaseMock.nameDao().getEntities()).thenReturn(array);

        return databaseMock;
    }
//    @Provides
//    @Named("test_db")
//    public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
//        return Room.databaseBuilder(context, WellbeingDatabase.class, "database_name")
//                .fallbackToDestructiveMigration()
//                .build();
//    }
}
