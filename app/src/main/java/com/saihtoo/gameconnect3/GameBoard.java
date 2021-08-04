package com.saihtoo.gameconnect3;

import android.util.Log;

public class GameBoard {
    int player;
    boolean gameActive;
    int[][] board;

    public static final int max = 2;
    public static final int min = 1;

    public GameBoard() {
        board = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        player = (int)Math.floor(Math.random()*(max-min+1)+min);
        gameActive = true;
    }

    public boolean checkRow() {
        int rowCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int ii = 0; ii < 3; ii++) {
                if (board[i][ii] == player) {
                    rowCounter++;
                }
                if (rowCounter == 3) {
                    return true;
                }
            }
            rowCounter = 0;
        }
        return false;
    }

    public boolean checkColumn() {
        int collumCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int ii = 0; ii < 3; ii++) {
                if (board[ii][i] == player) {
                    collumCounter++;
                }
                if (collumCounter == 3) {
                    return true;
                }
            }
            collumCounter = 0;
        }
        return false;
    }

    public boolean checkDiagonal() {
        if (board[1][1] == player) {
            if (board[0][0] == player && board[2][2] == player) {
                if (board[0][2] == player && board[2][0] == player) {
                    gameActive = false;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinner() {
        if (checkDiagonal() || checkRow() || checkColumn()) {
            gameActive = false;
            return true;
        }
        return false;
    }

    public void changePlayer() {
        if (player == 1) {
            player = 2;
        } else if (player == 2) {
            player = 1;
        }
        Log.d("CURRENT PLAYER", "PLAYER IS " + player);
    }

    public void makeMove(int tag) {
        switch (tag) {
            case 1: board[0][0] = player; break;
            case 2: board[0][1] = player; break;
            case 3: board[0][2] = player; break;
            case 4: board[1][0] = player; break;
            case 5: board[1][1] = player; break;
            case 6: board[1][2] = player; break;
            case 7: board[2][0] = player; break;
            case 8: board[2][1] = player; break;
            case 9: board[2][2] = player; break;
        }
    }
}
