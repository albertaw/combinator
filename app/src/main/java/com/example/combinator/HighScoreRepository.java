package com.example.combinator;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HighScoreRepository {
    private HighScoreDao highScoreDao;
    private LiveData<List<HighScore>> highScores;

    public HighScoreRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        highScoreDao = db.highScoreDao();
        //highScores = highScoreDao.getAll();
    }

    public LiveData<List<HighScore>> getAllHighScores() {
        return highScores;
    }

    public void insert(HighScore highScore) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            highScoreDao.insert(highScore);
        });
    }
}
