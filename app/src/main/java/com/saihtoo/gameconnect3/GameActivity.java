package com.saihtoo.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class GameActivity extends AppCompatActivity
{
    String player1_id, player2_id;
    TextView playerTextView;
    int player;
    boolean gameActive = true;
    int[][] gameBoard = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

    public boolean checkRow(int[][] myArray, int playerID) {
        int rowCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int ii = 0; ii < 3 ; ii++) {
                if (myArray[i][ii] == playerID)
                    rowCounter++;
                if (rowCounter == 3)
                    return true;
            } rowCounter = 0;
        } return false;
    }

    public boolean checkColumn(int[][] myArray, int playerID) {
        int collumCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int ii = 0; ii < 3 ; ii++) {
                if (myArray[ii][i] == playerID)
                    collumCounter++;
                if (collumCounter == 3)
                    return true;
            } collumCounter = 0;
        } return false;
    }

    public boolean checkDiagonal(int[][] myArray, int playerID) {
        if (myArray[1][1] == playerID)
            return (myArray[0][0] == playerID && myArray[2][2] == playerID)
                    || (myArray[0][2] == playerID && myArray[2][0] == playerID);
        else return false;
    }

    public boolean winnerChecker(int[][] myArray, int playerID) {
        return checkDiagonal(myArray, playerID) ||
                checkRow(myArray, playerID) ||
                checkColumn(myArray, playerID);
    }

    @SuppressLint("SetTextI18n")
    public void playerChanger() {
        if (player==1) {
            playerTextView.setText("Turn: " + player2_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.yellow));
            player = 2;
        } else if (player ==2) {
            playerTextView.setText("Turn: " + player1_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.red));
            player = 1;
        }
    }

    public int colorChecker() {
        if (player==1) return R.drawable.red;
        else return R.drawable.yellow;
    }

    public void insertion(ImageView view) {
        int tappedView = Integer.parseInt(view.getTag().toString());
        switch (tappedView) {
            case 1: gameBoard[0][0] = player; break;
            case 2: gameBoard[0][1] = player; break;
            case 3: gameBoard[0][2] = player; break;
            case 4: gameBoard[1][0] = player; break;
            case 5: gameBoard[1][1] = player; break;
            case 6: gameBoard[1][2] = player; break;
            case 7: gameBoard[2][0] = player; break;
            case 8: gameBoard[2][1] = player; break;
            case 9: gameBoard[2][2] = player; break;
        }
    }

    public String winnerID() {
        switch (player) {
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
        int max = 2; int min = 1;
        player = (int)Math.floor(Math.random()*(max-min+1)+min);;
        if (player == 1) {
            playerTextView.setText("Turn: " + player1_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.red));
        }
        else if (player == 2) {
            playerTextView.setText("Turn: " + player2_id);
            playerTextView.setBackgroundColor(getResources().getColor(R.color.yellow));
        }
    }

    @SuppressLint("SetTextI18n")
    public void viewClick(View view) {
        if (gameActive) {
            ImageView viewNum = (ImageView) view;
            viewNum.setTranslationY(-1500);
            viewNum.setImageResource(colorChecker());
            viewNum.animate().translationYBy(1500).setDuration(500);
            viewNum.setEnabled(false);
            insertion(viewNum);
            if (winnerChecker(gameBoard, player)) {
                playerTextView.setText("The winner is " + winnerID() + "!!!");
                gameActive = false;
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