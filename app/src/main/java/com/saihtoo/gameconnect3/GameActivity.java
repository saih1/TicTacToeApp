package com.saihtoo.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {
    String player1_id, player2_id;
    TextView playerTextView;
    GameBoard myGame;

    @SuppressLint("SetTextI18n")
    public void playerChanger() {
        if (myGame.player == 1) {
            playerTextView.setText("Turn: " + player2_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.yellow));
            myGame.changePlayer();
        } else if (myGame.player == 2) {
            playerTextView.setText("Turn: " + player1_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.red));
            myGame.changePlayer();
        }
    }

    public int colorChecker() {
        if (myGame.player == 1) return R.drawable.red;
        else return R.drawable.yellow;
    }

    public void insertion(ImageView view) {
        int tappedView = Integer.parseInt(view.getTag().toString());
        myGame.makeMove(tappedView);
    }

    public String winnerID() {
        switch (myGame.player) {
            case 1: return player1_id;
            case 2: return player2_id;
            default: return null;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Objects.requireNonNull(getSupportActionBar()).hide();

        player1_id = getIntent().getStringExtra("Player 1 Name");
        player2_id = getIntent().getStringExtra("Player 2 Name");
        playerTextView = findViewById(R.id.playerName);

        // create a new game
        myGame = new GameBoard();
        if (myGame.player == 1) {
            playerTextView.setText("Turn: " + player1_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.red));
        }
        else if (myGame.player == 2) {
            playerTextView.setText("Turn: " + player2_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.yellow));
        }
    }

    @SuppressLint("SetTextI18n")
    public void viewClick(View view) {
        if (myGame.gameActive) {
            ImageView viewNum = (ImageView) view;
            viewNum.setTranslationY(-1500);
            viewNum.setImageResource(colorChecker());
            viewNum.animate().translationYBy(1500).setDuration(500);
            viewNum.setEnabled(false);
            insertion(viewNum);
            if (myGame.checkWinner()) {
                playerTextView.setText("The winner is " + winnerID() + "!!!");
            } else playerChanger();
        }
    }

    public void restart(View view) {
        finish();
        startActivity(getIntent());
    }

    public void exit(View view) {
        finishAffinity();
    }
}