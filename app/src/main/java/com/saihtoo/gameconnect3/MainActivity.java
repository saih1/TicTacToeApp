package com.saihtoo.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText player1, player2;
    String player1_id, player2_id;
    ImageView redImage, yellowImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = findViewById(R.id.player1EditText);
        player2 = findViewById(R.id.player2EditText);

        redImage = findViewById(R.id.redImageView);
        yellowImage = findViewById(R.id.yellowImageView);

        redImage.setTranslationY(-1500);
        redImage.animate().translationYBy(1500).setDuration(500);

        yellowImage.setTranslationY(-1500);
        yellowImage.animate().translationYBy(1500).setDuration(500);
    }

    public void clickStart(View view) {
        player1_id = player1.getText().toString();
        player2_id = player2.getText().toString();
        if (player1_id.isEmpty() || player2_id.isEmpty())
            Toast.makeText(this, "Enter the player names",Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("Player 1 Name", player1_id);
            intent.putExtra("Player 2 Name", player2_id);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }
}