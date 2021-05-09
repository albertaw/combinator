package com.example.combinator;

import android.util.Log;

import java.util.ArrayList;

public class IdleState  implements State {
    private String TAG = "IdleState";

    public void init(Game game) {
        Log.i(TAG, "current state is idle");
        game.setNumRounds(1);
        game.setFeedback("");
        game.getPlayer().setScore(0);
        game.getPlayer().setCurrentScore(0);
    }

    public void update(Game game) {
        Log.i(TAG, "switching to playing state");
        game.setState(game.getPlayingState());
    }
}
