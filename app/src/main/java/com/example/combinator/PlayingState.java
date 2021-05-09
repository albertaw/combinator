package com.example.combinator;

import android.util.Log;

import java.util.ArrayList;

public class PlayingState implements State {
    private String TAG = "PlayingState";

    public void init(Game game) {
        game.setFeedback("");
        game.setNumGuessesLeft(10);
        game.getPlayer().setGuesses(new ArrayList<>());
        game.getPlayer().setCurrentScore(0);
    }

    public void update(Game game) {
        game.setNumGuessesLeft(game.getNumGuessesLeft() - 1);
        if (game.hasWon()) {
            Log.i(TAG, "switching to win state");
            //game.setFeedback("You win!");
            game.setState(game.getWinningState());
            game.init();
            game.notifyObserver();
        } else if (game.getNumGuessesLeft() == 0) {
            Log.i(TAG, "switching to game over state");
            //game.setFeedback("Game over");
            game.setState(game.getIdleState());
            game.init();
            game.notifyObserver();
        } else if (game.getCurrentCombo().compareTo(game.getPlayer().getCurrentGuess()) == 0){
            game.setFeedback("You guessed a number in the correct position");
        } else if (game.getCurrentCombo().compareTo(game.getPlayer().getCurrentGuess()) == 1) {
            game.setFeedback("You guessed a correct number");
        } else {
            game.setFeedback("Incorrect guess");
        }
    }
}
