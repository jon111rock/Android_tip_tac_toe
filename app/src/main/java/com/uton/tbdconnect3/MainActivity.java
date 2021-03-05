package com.uton.tbdconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    //initialize players
    boolean player1Turn = false;
    //initialize imgView array
    ImageView[][] imgArray = new ImageView[3][3];
    TextView dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dashboard = findViewById(R.id.textView_dashborad);
        dashboard.setText("Player 1 turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String imgId = "imageView_" + i + j;
                imgArray[i][j] = findViewById(getResources().getIdentifier(imgId, "id", getPackageName()));
                imgArray[i][j].setOnClickListener(this);

            }
        }


    }

    @Override
    public void onClick(View v) {
        if (player1Turn) {
            dashboard.setText("Player 1 turn");
            ((ImageView) v).setImageResource(R.drawable.circle);
            v.setTag("o");

            if (checkForWin()) {
                dashboard.setText("Player 1 win !!");
                pauseGame();
            }

        } else {
            dashboard.setText("Player 2 turn");
            ((ImageView) v).setImageResource(R.drawable.cross);
            v.setTag("x");

            if (checkForWin()) {
                dashboard.setText("Player 2 win !!");
                pauseGame();
            }
        }
        player1Turn = !player1Turn;
    }

    public void reStart(View view) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imgArray[i][j].setOnClickListener(this);
                imgArray[i][j].setImageResource(0);
                imgArray[i][j].setTag("");
            }
        }
        dashboard.setText("Player 1 turn");
        player1Turn = false;

    }

    private void pauseGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imgArray[i][j].setOnClickListener(null);
            }
        }
    }

    private boolean checkForWin() {
        String[][] state = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = "";
                if (imgArray[i][j].getTag() != null) {
                    state[i][j] = imgArray[i][j].getTag().toString();
                }
                Log.d(TAG, "" + i + j + ": " + state[i][j]);
            }
        }

        //check if win

        for (int i = 0; i < 3; i++) {
            if (
                    state[i][0].equals(state[i][1])
                            && state[i][0].equals(state[i][2])
                            && !state[i][0].equals("")
            ) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (
                    state[0][i].equals(state[1][i])
                            && state[0][i].equals(state[2][i])
                            && !state[0][i].equals("")
            ) {
                return true;
            }
        }

        if (
                state[0][0].equals(state[1][1])
                        && state[0][0].equals(state[2][2])
                        && !state[0][0].equals("")
        ) {
            return true;
        }

        if (
                state[0][2].equals(state[1][1])
                        && state[0][2].equals(state[2][0])
                        && !state[0][2].equals("")
        ) {
            return true;
        }

        return false;
    }
}