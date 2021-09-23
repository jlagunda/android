package com.example.covidwars;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class endgame extends AppCompatActivity {

    Button back;
    ProgressBar progressBar;
    TextView Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);
        Score = findViewById(R.id.Score);
        final  int myscore = getIntent().getIntExtra("life",10);
        final  int life = getIntent().getIntExtra("score",0);
        back = findViewById(R.id.btnMenu);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10);
        progressBar.setProgress(myscore);
        String lifes = String.valueOf(life);
        Score.setText(lifes );





        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(endgame.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}