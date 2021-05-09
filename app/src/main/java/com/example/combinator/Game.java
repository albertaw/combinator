package com.example.combinator;

import android.util.Log;

import java.util.ArrayList;

public class Game extends Subject {
    private State idleState;
    private State playingState;
    private State winningState;
    private State currentState;
    private Player player;
    private Combo currentCombo;
    private int numGuessesLeft;
    private int numRounds;
    private String feedback;
    private String state;

    public Game() {
        numGuessesLeft = 10;
        numRounds = 1;
        player = new Player();
        idleState = new IdleState();
        playingState = new PlayingState();
        winningState = new WinningState();
        currentState = idleState;
    }

    public void init() {
        currentState.init(this);
    }

    public void update() {
        numGuessesLeft -= 1;
        if (hasWon()) {
            state = "win";
//            numGuessesLeft = 10;
//            numRounds += 1;
            int currentScore = numGuessesLeft * 10 + 10;
            player.setCurrentScore(currentScore);
            player.setScore(currentScore + player.getScore());
//            player.setGuesses(new ArrayList<>());
            notifyObserver();
        } else if (numGuessesLeft == 0) {
            state = "game over";
//            numGuessesLeft = 10;
//            numRounds = 1;
//            player.setScore(0);
//            player.setCurrentScore(0);
//            player.setGuesses(new ArrayList<>());
            notifyObserver();
        } else if (currentCombo.compareTo(player.getCurrentGuess()) == 0){
            feedback = "You guessed a number in the correct position";
        } else if (currentCombo.compareTo(player.getCurrentGuess()) == 1) {
            feedback = "You guessed a correct number";
        } else {
            feedback = "Incorrect guess";
        }
    }
//    public void update() {
//        currentState.update(this);
//    }

//    public State getState() {
//        return currentState;
//    }
//
    public void setState(State state) {
        currentState = state;
    }

    public State getIdleState() {
        return idleState;
    }

    public State getPlayingState() {
        return playingState;
    }

    public State getWinningState() {
        return winningState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean hasWon() {
        if (currentCombo.equals(player.getCurrentGuess())) {
            return true;
        }
        return false;
    }

    public int getNumGuessesLeft() {
        return numGuessesLeft;
    }

    public void setNumGuessesLeft(int numGuessesLeft) {
        this.numGuessesLeft = numGuessesLeft;
    }

    public Combo getCurrentCombo() {
        return currentCombo;
    }

    public void setCurrentCombo(Combo combo) {
        this.currentCombo = combo;
    }

    public int getNumRounds() {
        return numRounds;
    }

    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getState() {
        return state;
    }
}
