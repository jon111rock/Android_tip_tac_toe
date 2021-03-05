package com.uton.tbdconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initalize imgView array
        ImageView[][] imgArray = new ImageView[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String imgId = "imageView_" + i + j;
                imgArray[i][j] = findViewById(getResources().getIdentifier(imgId, "id", getPackageName()));
                imgArray[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "onClick: " + getResources().getResourceName(v.getId()));
                    }
                });
            }
        }


    }
}