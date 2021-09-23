package com.example.covidwars;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class game extends AppCompatActivity implements View.OnClickListener {
    private List<Questions> questionsList;

    RadioButton choice1;
    RadioButton choice2;
    RadioButton choice3;

    ProgressBar life;



    int nItem;

    ImageView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView frontLiner = (ImageView) findViewById(R.id.frontliner);
        RelativeLayout background = findViewById(R.id.background);

        int police = getIntent().getIntExtra("character", R.drawable.secud);
        int mom = getIntent().getIntExtra("character", R.drawable.momd);
        int doctor = getIntent().getIntExtra("character", R.drawable.drd);
        int nurse = getIntent().getIntExtra("character", R.drawable.nursed);
        int aunt = getIntent().getIntExtra("character", R.drawable.auntd);
        int sta = getIntent().getIntExtra("bg", R.drawable.sta);
        int hou = getIntent().getIntExtra("bg", R.drawable.hou);
        int hos = getIntent().getIntExtra("bg", R.drawable.hos);



        frontLiner.setImageResource(police);
        frontLiner.setImageResource(doctor);
        frontLiner.setImageResource(mom);
        frontLiner.setImageResource(nurse);
        frontLiner.setImageResource(aunt);
        background.setBackgroundResource(sta);
        background.setBackgroundResource(hou);
        background.setBackgroundResource(hos);

        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);

        life = findViewById(R.id.life);
        life.setMax(10);
        life.setProgress(10);




        question = findViewById(R.id.question);


        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);

        getQuestionsList();


    }

    private void getQuestionsList() {
        questionsList = new ArrayList<>();

        questionsList.add(new Questions(R.drawable.q1, "Philippines", "China", "India", 2, ""));
        questionsList.add(new Questions(R.drawable.q2, "January 2020", "December 2019", "March 2020", 1, ""));
        questionsList.add(new Questions(R.drawable.q3, "Do Nothing", "Consult to a Front liner", "Implement Social Distancing", 3, ""));
        questionsList.add(new Questions(R.drawable.q4, "Help her with her bags", "Give her alcohol", "Do Nothing", 2, ""));
        questionsList.add(new Questions(R.drawable.q5, "Wear a Face Mask and Face Shield", "Social Distancing", "Touching anything outside", 1, ""));
        questionsList.add(new Questions(R.drawable.q7, "Fever,Dry Cough, Tiredness", "Sore Throat, Headache, Diarrhoea", "Chest pain, Shortness of breathe, Lost of taste", 1, ""));
        questionsList.add(new Questions(R.drawable.q8, "30 Days", "No need to Quarantine", "14 Days", 3, ""));
        questionsList.add(new Questions(R.drawable.q9, "Nausea", "Tiredness", "Sore throat", 1, ""));
        questionsList.add(new Questions(R.drawable.q10, "Airborne Infection Isolation Room *With Exhaust", "Airborne Infection Isolation Room *Without Exhaust", "None of the Above", 2, ""));
        questionsList.add(new Questions(R.drawable.q11, "3 Meters", "5 Meters", "No Required Distance", 1, ""));

        Collections.shuffle(questionsList);
        setQuestion();


    }

    private void setQuestion() {
        question.setImageResource(questionsList.get(0).getQuestion());
        choice1.setText(questionsList.get(0).getOptionA());
        choice2.setText(questionsList.get(0).getOptionB());
        choice3.setText(questionsList.get(0).getOptionC());

        nItem = 0;
    }


    @Override
    public void onClick(View v) {
        int choice = 0;

        choice1.setClickable(false);
        choice2.setClickable(false);
        choice3.setClickable(false);

        switch (v.getId()) {
            case R.id.choice1:
                choice = 1;
                break;

            case R.id.choice2:
                choice = 2;
                break;

            case R.id.choice3:
                choice = 3;
                break;


            default:
        }
        checkAnswer(choice);


    }
        int score = 0;
        int lifes = 10;
    private void checkAnswer(int choice) {
        if (choice == questionsList.get(nItem).getCorrectAns()) {
            lifes -= 1;
            life.setProgress(lifes);
            score++;

        }
        choice1.setChecked(false);
        choice2.setChecked(false);
        choice3.setChecked(false);
        choice1.setClickable(true);
        choice2.setClickable(true);
        choice3.setClickable(true);
        nextQuestion();
    }

    private void nextQuestion() {

        if (nItem < questionsList.size() - 1) {

            nItem++;

            check(question, 0, 0);
            check(choice1, 0, 1);
            check(choice2, 0, 2);
            check(choice3, 0, 3);




        } else
            {

                Intent intent = new Intent(game.this, endgame.class);
                intent.putExtra("life",lifes);
                intent.putExtra("score",score);
                startActivity(intent);

        }
    }

    private void check(final View view, final int value, int viewNum) {

        if (value == 0) {
            switch (viewNum) {

                case 0:
                    ((ImageView) view).setImageResource(questionsList.get(nItem).getQuestion());
                    break;
                case 1:
                    ((TextView)view).setText(questionsList.get(nItem).getOptionA());
                    break;
                case 2:
                    ((TextView)view).setText(questionsList.get(nItem).getOptionB());
                    break;
                case 3:
                    ((TextView)view).setText(questionsList.get(nItem).getOptionC());
                    break;

            }

        }
    }
}