package com.example.inbetweenandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Quit = (Button) findViewById(R.id.btnQuit);
        Quit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Button btnQuit = (Button) findViewById(R.id.btnQuit);

                btnQuit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(btnQuit.getContext());
                        builder1.setMessage("Do you want to quit?");
                        builder1.setCancelable(false);

                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                });
            }
        });
        Button Instruction = (Button) findViewById(R.id.btnInstruction);
        Instruction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Button btnInstruction = (Button) findViewById(R.id.btnInstruction);

                btnInstruction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Instruction.class);
                        startActivity(intent);
                    }
                });
            }
        });
        Button Start = (Button) findViewById(R.id.btnStart);
        Start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Button btnStart = (Button) findViewById(R.id.btnStart);

                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Start.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }
}