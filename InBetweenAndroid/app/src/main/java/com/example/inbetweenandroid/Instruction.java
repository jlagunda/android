package com.example.inbetweenandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Instruction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        Button Back = (Button) findViewById(R.id.btnBack);
        Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Button btnInstruction = (Button) findViewById(R.id.btnBack);

                btnInstruction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Instruction.this.finish();
                    }
                });
            }
        });
    }
}