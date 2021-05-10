package com.example.combinator;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HighScoreViewModel extends AndroidViewModel {
    private HighScoreRepository repository;
    private final LiveData<List<HighScore>> highScores;

    public HighScoreViewModel(Application application) {
        super(application);
        repository = new HighScoreRepository(application);
        highScores = repository.getAllHighScores();
    }

    LiveData<List<HighScore>> getHighScores() {
        return highScores;
    }

    public void insert(HighScore highScore) {
        repository.insert(highScore);
    }
}
