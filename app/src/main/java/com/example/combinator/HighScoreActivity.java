package com.example.combinator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class HighScoreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private AppDatabase db;
    private HighScoreDao highScoreDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Toolbar toolbar = (Toolbar)findViewById(R.id.high_score_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        db = AppDatabase.getInstance(this);
        highScoreDao = db.highScoreDao();

        recyclerView = findViewById(R.id.high_score_recycerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AppDatabase.databaseWriteExecutor.execute(() -> {
            adapter = new HighScoreAdapter(highScoreDao.getAll());
            recyclerView.setAdapter(adapter);
        });

    }
}