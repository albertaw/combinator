# Combinator

## Getting Started

The APK file is located in the app/release folder.
You can play the game on an emulator or your device.
First download [Android Studio](https://developer.android.com/studio).
Open this project in Android Studio. To use the emulator go to tools->AVD Manager.
Select the button to create a virtual device and follow the
prompts. To use your device, connect your phone to your computer
using a USB cable. Enable USB debugging on your phone.
In the toolbar, select the device you want to run the app
on then click the play button.

## Overview

You start the game by first entering your name,
which is saved to shared preferences. When you
click the start game button, a combination
will be generated and you can start guessing. You
enter your guess in the input field and the combination
you chose and the feedback delivered are printed at the
bottom of the screen. There is a show answer button that
is meant for testing purposes. At the end of a game,
your score is saved to the database. You have the option
to play again. If you have won the previous game,
your score will continue to accumulate. If you choose not
to play again you will be taken to the high score screen.

## Design

The key objects are the game, player, combo, guess, and high score.
The game manages the global state and updates the state every time  
the user submits a guess. The GameActivity manages the view
for the game. I use the observer pattern to notify the GameActivity
when the game is over. I do this so that I can keep the logic
for manipulating the view separate from the game class.

To generate the combos I use the Retrofit library to fetch
the combo using the provided URL. The WebService class turns the
API into an interface. The ComboRepository implements the
interface and provides the method to make the HTTP request.
The ComboViewModel calls the method and stores the data.
The code is organized this way to separate code for fetching
data from UI logic.

A guess is a combination and the feedback that goes with it.
To show a list of guesses on the screen I use the GuessAdapter
which binds the data (guesses) to the view.

High scores are persisted in the database using the Room library.
The HighScore class is an entity. The HighScoreDao provides
the methods to interact with the database. The HighScoreActivity
is the screen that displays the high scores. And the HighScoreAdapter
binds the high scores to the view.