package com.example.combinator;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int score;
    private int currentScore;
    private Combo currentGuess;
    private List<Combo> guesses;

    public Player() {
        score = 0;
        currentScore = 0;
        guesses = new ArrayList<>();
    }

    public void makeGuess(Combo guess) {
        currentGuess = guess;
        guesses.add(guess);
//        if (game.getCurrentCombo().equals(guess)) {
//            game.setState("win");
//            return "You win";
//        } else if (game.getNumGuessesLeft() == 0) {
//            game.setState("lose");
//            return "Game over, you lose";
//        } else if (game.getCurrentCombo().compareTo(guess) == 0){
//            return "You guessed a number in the correct position";
//        } else if (game.getCurrentCombo().compareTo(guess) == 1) {
//            return "You guessed a correct number";
//        } else {
//            return "Incorrect guess";
//        }
    }

    public Combo getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(Combo combo) {
        currentGuess = combo;
    }

    public List<Combo> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<Combo> guesses) {
        this.guesses = guesses;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
