package com.joshuarichardson.dependencyinjection;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.android.dx.command.Main;
import com.joshuarichardson.dependencyinjection.Modules.AnalyticsModule;
import com.joshuarichardson.dependencyinjection.Modules.DatabaseEntity;
import com.joshuarichardson.dependencyinjection.Modules.DatabaseModule;
import com.joshuarichardson.dependencyinjection.Modules.NameDao;
import com.joshuarichardson.dependencyinjection.Modules.WellbeingDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static kotlin.text.Typography.times;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import androidx.test.rule.ActivityTestRule;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.testing.BindValue;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@HiltAndroidTest
@UninstallModules({AnalyticsModule.class, DatabaseModule.class})
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.joshuarichardson.dependencyinjection", appContext.getPackageName());
    }

//
//    @Module
//    @InstallIn(ApplicationComponent.class)
//    public class TestDatabaseModule {
////        @Provides
////        @Named("test_db")
////        public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
////            return Room.inMemoryDatabaseBuilder(context, WellbeingDatabase.class).build();
////        }
//        @Provides
//        @Named("test_db")
//        public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
//            return Room.databaseBuilder(context, WellbeingDatabase.class, "database_name")
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//    }


//    @Rule
    private HiltAndroidRule hiltRule = new HiltAndroidRule(this);

//    @Inject
//    WellbeingDatabase wellbeingDatabase;
//    @BindValue WellbeingDatabase wellbeingDatabase = provideDatabaseService();


//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//

//    @Rule
//    private ActivityScenarioRule<MainActivity> mainActivityScenario = new ActivityScenarioRule<>(MainActivity.class);
    private ActivityScenario<MainActivity> mainActivityScenario;
//    private ActivityTestRule<MainActivity> answerSurveyActivity = new ActivityTestRule(MainActivity.class, true, false);

    @Rule
    public RuleChain chain = RuleChain.outerRule(hiltRule);//.around(mainActivityScenario);

    @Inject
//    @Named("test_db")
    WellbeingDatabase database;

    @Inject
//    @Named("test_analytics")
    AnalyticsService analyticsService;


//    @Mock
//    WellbeingDatabase databaseMock;
//
//    @After
//    public void tearDown() {
//        mainActivityScenario.close();
//    }

    @Before
    public void setUp() {
        hiltRule.inject();
        database.nameDao().insert(new DatabaseEntity(4));
        database.nameDao().insert(new DatabaseEntity(2));


//        databaseMock = mock(WellbeingDatabase.class);
//
//        ArrayList<DatabaseEntity> array = new ArrayList<DatabaseEntity>();
//        array.add(new DatabaseEntity(4));
//
//
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

//        MockitoAnnotations.openMocks(this);
    }
//
//    @InjectMocks
//    MainActivity activity = new MainActivity();

    @Test
    public void checkMockedDatabaseMocksTheDataInTheUI() {

//        launchActivity<MainActivity>();
        mainActivityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.textView2)).check(ViewAssertions.matches(withText("4")));
        assertEquals(1, 1);

//        MainActivity activity = new MainActivity();
//        activity.database = databaseMock;



//        when(database.nameDao().getEntities()).thenReturn(array);

//        ActivityScenario<MainActivity> mainActivityScenario = ActivityScenario.launch(MainActivity.class);

//        Intent intent = new Intent();
//        answerSurveyActivity.launchActivity(intent);
//        verify(activity, times(1)).database.nameDao();


    }
}