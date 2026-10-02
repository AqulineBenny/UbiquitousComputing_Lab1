package com.example.numbergame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private TextView tvGuesses;
    private EditText editGuess;
    private Button btnGuess;
    private Button btnPlayAgain;
    private int secretNumber;
    private int guessCount;
    private boolean gameOver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        tvGuesses = findViewById(R.id.tvGuesses);
        editGuess = findViewById(R.id.editGuess);
        btnGuess = findViewById(R.id.btnGuess);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        startNewGame();
    }

    private void startNewGame() {
        Random random = new Random();
        secretNumber = random.nextInt(30) + 1;
        guessCount = 0;
        gameOver = false;
        tvResult.setText("");
        tvGuesses.setText("Number of guesses = 0");
        editGuess.setText("");
        editGuess.setEnabled(true);
        btnGuess.setEnabled(true);
        btnPlayAgain.setVisibility(View.GONE);
    }

    public void onGuess(View view) {
        if (gameOver) {
            return;
        }

        String guessText = editGuess.getText().toString().trim();

        if (guessText.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show();
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (guess < 1 || guess > 30) {
            Toast.makeText(this, "Please enter a number between 1 and 30", Toast.LENGTH_SHORT).show();
            return;
        }
        guessCount++;
        tvGuesses.setText("Number of guesses = " + guessCount);

        if (guess == secretNumber) {
            tvResult.setText("Congrats you guessed the correct Number.");
            gameOver = true;
            editGuess.setEnabled(false);
            btnGuess.setEnabled(false);
            btnPlayAgain.setVisibility(View.VISIBLE);
        } else if (guess < secretNumber) {
            tvResult.setText("Too LOW try again");
            editGuess.setText("");
        } else {
            tvResult.setText("Too HIGHT try again");
            editGuess.setText("");
        }
    }
    public void onPlayAgain(View view){
        startNewGame();
    }
}