package com.joshuarichardson.dependencyinjection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.widget.TextView;

import com.joshuarichardson.dependencyinjection.Modules.DatabaseEntity;
import com.joshuarichardson.dependencyinjection.Modules.DatabaseModule;
import com.joshuarichardson.dependencyinjection.Modules.NameDao;
import com.joshuarichardson.dependencyinjection.Modules.WellbeingDatabase;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    AnalyticsAdapter analytics;

    @Inject
    public WellbeingDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);

        textView.setText(analytics.getSomethingElse());

        NameDao dao = database.nameDao();

        DatabaseModule.databaseWriteExecutor.execute(() -> {
            dao.insert(new DatabaseEntity(1));
            List<DatabaseEntity> entities = dao.getEntities();
            int entity = entities.get(0).getId();
            runOnUiThread(() -> {
                textView2.setText(String.format(Locale.getDefault(), "%d", entities.get(0).getId()));
                textView3.setText(String.format(Locale.getDefault(), "%d", entities.size()));
            });
        });
    }
}