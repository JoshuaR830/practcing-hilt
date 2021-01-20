package com.joshuarichardson.dependencyinjection;

import android.content.Context;

import com.joshuarichardson.dependencyinjection.Modules.AnalyticsModule;
import com.joshuarichardson.dependencyinjection.Modules.DatabaseEntity;
import com.joshuarichardson.dependencyinjection.Modules.DatabaseModule;
import com.joshuarichardson.dependencyinjection.Modules.NameDao;
import com.joshuarichardson.dependencyinjection.Modules.WellbeingDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
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

    // References:
    // Multiple rules - https://developer.android.com/training/dependency-injection/hilt-testing#multiple-testrules
    // How to chain rules and keep references to them - https://stackoverflow.com/a/27243450/13496270
    // Rule order matters https://dagger.dev/hilt/testing.html
    private ActivityScenarioRule<MainActivity> mainActivityScenario = new ActivityScenarioRule<>(MainActivity.class);
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Rule
    public RuleChain chain = RuleChain.outerRule(hiltRule).around(mainActivityScenario);

    // References:
    // Replace a binding https://developer.android.com/training/dependency-injection/hilt-testing#replace-binding
    // Finishing off the loose ends https://www.youtube.com/watch?v=nOp_CEP_EjM
    // The Hilt docs https://dagger.dev/hilt/testing.html
    @Module
    @InstallIn(ApplicationComponent.class)
    public static class TestDatabaseModule {
        @Provides
        public WellbeingDatabase provideDatabaseService(@ApplicationContext Context context) {
            WellbeingDatabase databaseMock = mock(WellbeingDatabase.class);
            NameDao nameDao = mock(NameDao.class);

            ArrayList<DatabaseEntity> array = new ArrayList<>();
            array.add(new DatabaseEntity(4));

            // References:
            // How to use any() - https://www.journaldev.com/21876/mockito-argument-matchers-any-eq
            // How to use thenReturn() - https://stackoverflow.com/a/60540072/13496270
            when(nameDao.insert(any(DatabaseEntity.class))).thenReturn(0L);
            when(nameDao.getEntities()).thenReturn(array);

            when(databaseMock.nameDao()).thenReturn(nameDao);

            return databaseMock;
        }
    }

    @Inject
    WellbeingDatabase database;

    @Inject
    AnalyticsService analyticsService;

    @Before
    public void setUp() {
        // References
        // Injecting everything - https://developer.android.com/training/dependency-injection/hilt-testing#testing-features
        hiltRule.inject();
    }

    @Test
    public void checkMockedDatabaseMocksTheDataInTheUI() {
        onView(withId(R.id.textView2)).check(ViewAssertions.matches(withText("4")));
    }
}