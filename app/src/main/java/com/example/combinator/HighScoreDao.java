package com.example.combinator;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HighScoreDao {
    @Insert
    void insert(HighScore highScore);

    @Query("SELECT * FROM highscore ORDER BY score DESC")
    List<HighScore> getAll();
}
