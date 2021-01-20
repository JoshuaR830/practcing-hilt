package com.joshuarichardson.dependencyinjection;

import android.content.Context;

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

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.assertion.ViewAssertions;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@HiltAndroidTest
@UninstallModules({AnalyticsModule.class, DatabaseModule.class})
public class HiltDatabaseTest {
    private HiltAndroidRule hiltRule = new HiltAndroidRule(this);
    private ActivityScenario<MainActivity> mainActivityScenario;

    @Module
    @InstallIn(ApplicationComponent.class)
    public static class TestDatabaseModule {
        @Provides
        public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
            WellbeingDatabase databaseMock = mock(WellbeingDatabase.class);
            NameDao nameDao = mock(NameDao.class);

            ArrayList<DatabaseEntity> array = new ArrayList<>();
            array.add(new DatabaseEntity(4));

            when(nameDao.insert(any(DatabaseEntity.class))).thenReturn(0L);
            when(nameDao.getEntities()).thenReturn(array);

            when(databaseMock.nameDao()).thenReturn(nameDao);

            return databaseMock;
        }
    }

    @Rule
    public RuleChain chain = RuleChain.outerRule(hiltRule);//.around(mainActivityScenario);

    @Inject
    WellbeingDatabase database;

    @Inject
    AnalyticsService analyticsService;

    @After
    public void tearDown() {
        mainActivityScenario.close();
    }

    @Before
    public void setUp() {
        hiltRule.inject();
    }

    @Test
    public void checkMockedDatabaseMocksTheDataInTheUI() {
        mainActivityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.textView2)).check(ViewAssertions.matches(withText("4")));
    }
}