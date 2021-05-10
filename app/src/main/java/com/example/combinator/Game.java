package com.example.combinator;

public class Game extends Subject {
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
    }

    public void update() {
        numGuessesLeft -= 1;
        if (hasWon()) {
            state = "win";
            int currentScore = numGuessesLeft * 10 + 10;
            player.setCurrentScore(currentScore);
            player.setScore(currentScore + player.getScore());
            feedback = "correct";
            notifyObserver();
        } else if (numGuessesLeft == 0) {
            state = "game over";
            notifyObserver();
        } else if (currentCombo.compareTo(player.getCurrentGuess()) == 0){
            feedback = "You guessed a number in the correct position";
        } else if (currentCombo.compareTo(player.getCurrentGuess()) == 1) {
            feedback = "You guessed a correct number";
        } else {
            feedback = "Incorrect guess";
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
