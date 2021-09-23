package com.example.covidwars;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class characterSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);

        Button cPolice = (Button)findViewById(R.id.btnSecu);
        Button cMom = (Button)findViewById(R.id.btnMom);
        Button cDoctor = (Button)findViewById(R.id.btnDoctor);
        Button cNurse = (Button)findViewById(R.id.btnNurse);
        Button cAunt = (Button)findViewById(R.id.btnAunt);

        cPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(characterSelect.this, game.class);
                intent.putExtra("character",R.drawable.secud);
                intent.putExtra("bg",R.drawable.sta);
                startActivity(intent);
            }
        });

        cMom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(characterSelect.this, game.class);
                intent.putExtra("character",R.drawable.momd);
                intent.putExtra("bg",R.drawable.hou);
                startActivity(intent);
            }
        });

        cDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(characterSelect.this, game.class);
                intent.putExtra("character",R.drawable.drd);
                intent.putExtra("bg",R.drawable.hos);
                startActivity(intent);
            }
        });

        cNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(characterSelect.this, game.class);
                intent.putExtra("character",R.drawable.nursed);
                intent.putExtra("bg",R.drawable.hos);
                startActivity(intent);
            }
        });

        cAunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(characterSelect.this, game.class);
                intent.putExtra("character",R.drawable.auntd);
                intent.putExtra("bg",R.drawable.hou);
                startActivity(intent);
            }
        });



    }
}