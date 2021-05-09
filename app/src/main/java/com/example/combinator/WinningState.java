package com.example.combinator;

import android.util.Log;

public class WinningState implements State {
    private String TAG = "WinningState";

    public void init(Game game) {
        game.setFeedback("");
        game.setNumRounds(game.getNumRounds() + 1);
        int currentScore = game.getNumGuessesLeft() * 10 + 10;
        game.getPlayer().setCurrentScore(currentScore);
        game.getPlayer().setScore(currentScore + game.getPlayer().getScore());
    }
    public void update(Game game) {
        Log.i(TAG, "switching to playing state");
        game.setState(game.getPlayingState());
    }
}
