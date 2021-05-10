package com.example.combinator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    private Game game;
    private TextView score;
    private TextView guessesLeft;
    private TextView feedback;
    private TextView digit1TextView;
    private TextView digit2TextView;
    private TextView digit3TextView;
    private TextView digit4TextView;
    private Button submit;
    private ComboViewModel comboViewModel;
    private List<Guess> guesses;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String name;
    private AppDatabase db;
    private HighScoreDao highScoreDao;
    private String TAG = "GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        name = getIntent().getStringExtra("name");

        game = new Game();
        game.registerObserver(this);

        score = findViewById(R.id.score);
        guessesLeft = findViewById(R.id.guesses_left);
        feedback = findViewById(R.id.feedback);
        digit1TextView = findViewById(R.id.digit_1);
        digit2TextView = findViewById(R.id.digit_2);
        digit3TextView = findViewById(R.id.digit_3);
        digit4TextView = findViewById(R.id.digit_4);

        recyclerView = findViewById(R.id.guesses_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        guesses = new ArrayList<>();
        adapter = new GuessAdapter(guesses);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(this);
        comboViewModel = new ViewModelProvider(this).get(ComboViewModel.class);
        db = AppDatabase.getInstance(this);
        highScoreDao = db.highScoreDao();
        startGame();
    }

    private void clearInput() {
        digit1TextView.setText("");
        digit2TextView.setText("");
        digit3TextView.setText("");
        digit4TextView.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                submitGuess();
                break;
        }
    }

    private void startGame() {
        guesses.clear();
        adapter.notifyDataSetChanged();
        comboViewModel.getCombo().observe(this, result -> {
            Log.i(TAG, result);
            String[] integers = result.split("\n");
            Combo combo = new Combo();
            for (String integer : integers) {
                combo.addDigit(Integer.parseInt(integer));
            }
            game.setCurrentCombo(combo);
        });
    }

    private void submitGuess() {
        Combo combo = new Combo();
        String digit1 = digit1TextView.getText().toString();
        String digit2 = digit2TextView.getText().toString();
        String digit3 = digit3TextView.getText().toString();
        String digit4 = digit4TextView.getText().toString();

        if (digit1.equals("") || digit2.equals("") || digit3.equals("") || digit4.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setTitle(R.string.text_error)
                    .setMessage(R.string.text_empty_string_warning);
            Dialog dialog = builder.create();
            dialog.show();
        } else {
            combo.addDigit(Integer.parseInt(digit1));
            combo.addDigit(Integer.parseInt(digit2));
            combo.addDigit(Integer.parseInt(digit3));
            combo.addDigit(Integer.parseInt(digit4));

            game.getPlayer().makeGuess(combo);
            game.update();
            feedback.setText(game.getFeedback());
            guessesLeft.setText(String.valueOf(game.getNumGuessesLeft()));
            Guess guess = new Guess();
            guess.setCombo(combo.toString());
            guess.setFeedback(game.getFeedback());
            guesses.add(guess);
            adapter.notifyItemInserted(guesses.size()-1);
            recyclerView.scrollToPosition(guesses.size()-1);
        }
    }

    @Override
    public void update() {

        HighScore highScore = new HighScore(name, game.getPlayer().getScore());
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

        if (game.getState().equals("win")) {
            builder.setTitle("You win!")
                    .setMessage("Points earned: " + game.getPlayer().getCurrentScore() + "\nWould you like to play again?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            game.setNumGuessesLeft(10);
                            game.setNumRounds(game.getNumRounds() + 1);
                            game.getPlayer().setGuesses(new ArrayList<>());
                            feedback.setText("");
                            guessesLeft.setText(String.valueOf(game.getNumGuessesLeft()));
                            score.setText(String.valueOf(game.getPlayer().getScore()));
                            clearInput();
                            comboViewModel.loadCombo();
                            startGame();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //highScoreViewModel.insert(highScore);
                            AppDatabase.databaseWriteExecutor.execute(() -> {
                                highScoreDao.insert(highScore);
                            });
                            Intent intent = new Intent(GameActivity.this, HighScoreActivity.class);
                            startActivity(intent);
                        }
                    });
        } else {
            AppDatabase.databaseWriteExecutor.execute(() -> {
                highScoreDao.insert(highScore);
            });
            builder.setTitle("You lose")
                    .setMessage("Total score: " + game.getPlayer().getScore() + "\nWould you like to play again?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            game = new Game();
                            feedback.setText("");
                            guessesLeft.setText(String.valueOf(game.getNumGuessesLeft()));
                            score.setText(String.valueOf(game.getPlayer().getScore()));
                            clearInput();
                            comboViewModel.loadCombo();
                            startGame();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(GameActivity.this, HighScoreActivity.class);
                            startActivity(intent);
                        }
                    });
        }
        Dialog dialog = builder.create();
        dialog.show();

    }
}