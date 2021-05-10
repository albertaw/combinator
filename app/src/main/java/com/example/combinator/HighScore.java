package com.example.combinator;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HighScore {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public HighScore() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
