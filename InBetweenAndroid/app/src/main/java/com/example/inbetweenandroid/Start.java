package com.example.inbetweenandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;
import java.util.Random;



public class Start extends AppCompatActivity {



    MediaPlayer sounds;
    MediaPlayer clap;
    MediaPlayer lose;

    int pocketMoney = 1000;


    Random random = new Random();

    int cardUno;

    int cardDos;

    int cardTres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sounds=MediaPlayer.create(Start.this, R.raw.game);
        sounds.setLooping(true);
        sounds.start();
        clap=MediaPlayer.create(Start.this, R.raw.clap);
        lose=MediaPlayer.create(Start.this, R.raw.lose);

        final ImageButton btnSpeakerOn = findViewById(R.id.btnSpeakerOn);
        final ImageButton btnSpeakerOff = findViewById(R.id.btnSpeakerOff);
        final LottieAnimationView congrats = findViewById(R.id.congrats);
        final RadioButton high = findViewById(R.id.rbtnHigh);
        final RadioButton low = findViewById(R.id.rbtnLow);
        final Button btnBet = findViewById(R.id.btnBet);
        final Button btnFold = findViewById(R.id.btnFold);
        final Button btnReveal = findViewById(R.id.btnReveal);
        final Button btnBack = findViewById(R.id.btnBack);
        final EditText betMoney = findViewById(R.id.txtMoney);
        final TextView currentMoney = findViewById(R.id.txtBox);

        Button Back = (Button) findViewById(R.id.btnBack);
        Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Start.this.finish();
            }
        });
        Button Reveal = (Button) findViewById(R.id.btnReveal);
        Reveal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            card1();
            card3();
                btnReveal.setEnabled(false);
                btnBack.setEnabled(false);
                if (pocketMoney == 0 ){
                    Toast.makeText(getApplicationContext(), "You have no pocket money left", 3).show();
                }else{

                    if(cardUno == cardTres){
                        high.setEnabled(true);
                        low.setEnabled(true);
                    }
                    betMoney.setEnabled(true);
                    betMoney.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            String betMoneyInput = betMoney.getText().toString();
                            btnBet.setEnabled(!betMoneyInput.isEmpty());

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    btnFold.setEnabled(true);
                    high.setChecked(false);
                    low.setChecked(false);


                }

            }
        });

        Button Bet = (Button) findViewById(R.id.btnBet);
        Bet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                card2();
                int moneyBet = Integer.parseInt(betMoney.getText().toString());
                if (moneyBet > pocketMoney) {
                    Toast.makeText(getApplicationContext(), "Your money is only: " + pocketMoney, 3).show();
                    btnBet.setEnabled(false);
                    btnFold.setEnabled(false);
                    btnReveal.setEnabled(true);
                    high.setEnabled(false);
                    low.setEnabled(false);
                    low.setChecked(false);
                    high.setChecked(false);
                    betMoney.setEnabled(false);
                    betMoney.setText("");
                    Fold();
                } else {
                    if (high.isChecked()) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(btnBet.getContext());
                        if (cardDos > cardUno) {
                            congrats.playAnimation();
                            clap.start();
                            builder1.setMessage("YOU WIN THIS ROUND");
                            builder1.setCancelable(false);

                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    congrats.cancelAnimation();
                                    btnBet.setEnabled(false);
                                    btnFold.setEnabled(false);
                                    btnReveal.setEnabled(true);
                                    high.setEnabled(false);
                                    low.setEnabled(false);
                                    low.setChecked(false);
                                    high.setChecked(false);
                                    betMoney.setEnabled(false);
                                    betMoney.setText("");
                                    Fold();


                                }
                            });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                            pocketMoney += moneyBet;
                        } else {
                            lose.start();
                            builder1.setMessage("YOU LOSE THIS ROUND");
                            builder1.setCancelable(false);

                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    btnBet.setEnabled(false);
                                    btnFold.setEnabled(false);
                                    btnReveal.setEnabled(true);
                                    high.setEnabled(false);
                                    low.setEnabled(false);
                                    low.setChecked(false);
                                    high.setEnabled(false);
                                    betMoney.setEnabled(false);
                                    betMoney.setText("");
                                    Fold();
                                }
                            });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                            pocketMoney -= moneyBet;
                        }
                    String win = String.valueOf(pocketMoney);
                    currentMoney.setText(win);

                    }else if(low.isChecked()) {
                        card2();

                        AlertDialog.Builder builder1 = null;
                        if (cardDos < cardUno) {
                            builder1 = new AlertDialog.Builder(btnBet.getContext());
                            clap.start();
                            builder1.setMessage("YOU WIN THIS ROUND");
                            builder1.setCancelable(false);
                            congrats.playAnimation();

                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    congrats.cancelAnimation();
                                    btnBet.setEnabled(false);
                                    btnFold.setEnabled(false);
                                    btnReveal.setEnabled(true);
                                    high.setEnabled(false);
                                    low.setEnabled(false);
                                    low.setChecked(false);
                                    high.setEnabled(false);
                                    betMoney.setEnabled(false);
                                    betMoney.setText("");
                                    Fold();
                                }
                            });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                            pocketMoney += moneyBet;
                            String win = String.valueOf(pocketMoney);
                            currentMoney.setText(win);

                        } else {
                            lose.start();
                            builder1.setMessage("YOU LOSE THIS ROUND");
                            builder1.setCancelable(false);

                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    btnBet.setEnabled(false);
                                    btnFold.setEnabled(false);
                                    btnReveal.setEnabled(true);
                                    high.setEnabled(false);
                                    low.setEnabled(false);
                                    low.setChecked(false);
                                    high.setChecked(false);
                                    betMoney.setEnabled(false);
                                    betMoney.setText("");
                                    Fold();
                                }
                            });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                            pocketMoney -= moneyBet;

                        }
                        String prizeMoney = String.valueOf(pocketMoney);
                        currentMoney.setText(prizeMoney);


                    }  else {
                        card2();

                        if (cardDos > cardUno && cardDos < cardTres) {
                            congrats.playAnimation();
                            clap.start();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(btnBet.getContext());
                            builder1.setMessage("YOU WIN THIS ROUND");
                            builder1.setCancelable(false);

                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    btnBet.setEnabled(false);
                                    btnFold.setEnabled(false);
                                    btnReveal.setEnabled(true);
                                    high.setEnabled(false);
                                    low.setEnabled(false);
                                    low.setChecked(false);
                                    high.setChecked(false);
                                    betMoney.setEnabled(false);
                                    betMoney.setText("");
                                    Fold();
                                }
                            });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                            pocketMoney += moneyBet;
                            String prizeMoney = String.valueOf(pocketMoney);
                            currentMoney.setText(prizeMoney);

                        } else if (cardDos < cardUno && cardDos > cardTres) {
                            clap.start();
                            congrats.playAnimation();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(btnBet.getContext());
                            builder1.setMessage("YOU WIN THIS ROUND");
                            builder1.setCancelable(false);

                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    btnBet.setEnabled(false);
                                    btnFold.setEnabled(false);
                                    btnReveal.setEnabled(true);
                                    high.setEnabled(false);
                                    low.setEnabled(false);
                                    low.setChecked(false);
                                    high.setChecked(false);
                                    betMoney.setEnabled(false);
                                    betMoney.setText("");
                                    Fold();
                                }
                            });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                            pocketMoney += moneyBet;
                            String prizeMoney = String.valueOf(pocketMoney);
                            currentMoney.setText(prizeMoney);


                        } else {
                            lose.start();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(btnBet.getContext());
                            builder1.setMessage("YOU LOSE THIS ROUND");
                            builder1.setCancelable(false);

                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    btnBet.setEnabled(false);
                                    btnFold.setEnabled(false);
                                    btnReveal.setEnabled(true);
                                    high.setEnabled(false);
                                    low.setEnabled(false);
                                    low.setChecked(false);
                                    high.setChecked(false);
                                    betMoney.setEnabled(false);
                                    betMoney.setText("");
                                    Fold();
                                }
                            });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                            pocketMoney -= moneyBet;
                            String prizeMoney = String.valueOf(pocketMoney);
                            currentMoney.setText(prizeMoney);

                        }

                    }

                }
            }
        });
        Button Fold = (Button) findViewById(R.id.btnFold);
        Fold.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fold();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(btnFold.getContext());
                builder1.setMessage("YOU LOSE (PHP 10) THIS ROUND BECAUSE YOU FOLD");
                builder1.setCancelable(false);
                pocketMoney -= 10 ;
                String win = String.valueOf(pocketMoney);
                currentMoney.setText(win);

                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        betMoney.setEnabled(false);
                        betMoney.setText("");
                        btnBet.setEnabled(false);
                        btnFold.setEnabled(false);
                        btnReveal.setEnabled(true);
                        high.setEnabled(false);
                        low.setEnabled(false);
                        high.setChecked(false);
                        low.setChecked(false);

                    }
                });
                AlertDialog alert11 = builder1.create();
                alert11.show();

            }


        });
        final ImageButton btnSpeaker1 = (ImageButton)findViewById(R.id.btnSpeakerOn);
        btnSpeaker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sounds.pause();
                btnSpeakerOn.setVisibility(View.INVISIBLE);
                btnSpeakerOff.setVisibility(View.VISIBLE);
            }
        });
        final ImageButton btnSpeaker2 = (ImageButton)findViewById(R.id.btnSpeakerOff);
        btnSpeaker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sounds.start();
                btnSpeakerOn.setVisibility(View.VISIBLE);
                btnSpeakerOff.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        sounds.release();
    }

    public void card1()
    {
        cardUno = random.nextInt(14);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(cardUno,"scaleX",1f,0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(cardUno, "scaleX", 0f, 1f);
        oa1.setDuration(200);
        oa2.setDuration(200);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

        if(cardUno == 0)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.joke);
        }
        else if(cardUno == 1)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.ace);
        }
        else if(cardUno == 2)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.two);
        }
        else if(cardUno == 3)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.three);
        }
        else if(cardUno == 4)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.four);
        }
        else if(cardUno == 5)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.five);
        }
        else if(cardUno == 6)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.six);
        }
        else if(cardUno == 7)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.seven);
        }
        else if(cardUno == 8)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.eight);
        }
        else if(cardUno == 9)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.nine);
        }
        else if(cardUno == 10)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.ten);
        }
        else if(cardUno == 11)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.jack);
        }
        else if(cardUno == 12)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.queen);
        }
        else if(cardUno == 13)
        {
            ImageView image = (ImageView) findViewById(R.id.Card1);
            image.setImageResource(R.drawable.king);
        }
                oa2.start();
            }
        });
        oa1.start();
    }
    public void card3()
    {
        cardTres = random.nextInt(14);

        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(cardTres,"scaleX",1f,0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(cardTres, "scaleX", 0f, 1f);
        oa1.setDuration(200);
        oa2.setDuration(200);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
        if(cardTres == 0)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.joke);
        }
        else if(cardTres == 1)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.ace);
        }
        else if(cardTres == 2)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.two);
        }
        else if(cardTres == 3)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.three);
        }
        else if(cardTres == 4)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.four);
        }
        else if(cardTres == 5)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.five);
        }
        else if(cardTres == 6)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.six);
        }
        else if(cardTres == 7)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.seven);
        }
        else if(cardTres == 8)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.eight);
        }
        else if(cardTres == 9)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.nine);
        }
        else if(cardTres == 10)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.ten);
        }
        else if(cardTres == 11)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.jack);
        }
        else if(cardTres == 12)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.queen);
        }
        else if(cardTres == 13)
        {
            ImageView image = (ImageView) findViewById(R.id.Card3);
            image.setImageResource(R.drawable.king);
        }
                oa2.start();
            }
        });
        oa1.start();
    }
    public void card2()
    {
        cardDos = random.nextInt(14);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(cardDos,"scaleX",1f,0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(cardDos, "scaleX", 0f, 1f);
        oa1.setDuration(200);
        oa2.setDuration(200);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
        if(cardDos == 0)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.joke);
        }
        else if(cardDos == 1)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.ace);
        }
        else if(cardDos == 2)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.two);
        }
        else if(cardDos == 3)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.three);
        }
        else if(cardDos == 4)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.four);
        }
        else if(cardDos == 5)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.five);
        }
        else if(cardDos == 6)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.six);
        }
        else if(cardDos == 7)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.seven);
        }
        else if(cardDos == 8)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.eight);
        }
        else if(cardDos == 9)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.nine);
        }
        else if(cardDos == 10)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.ten);
        }
        else if(cardDos == 11)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.jack);
        }
        else if(cardDos == 12)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.queen);
        }
        else if(cardDos == 13)
        {
            ImageView image = (ImageView) findViewById(R.id.Card2);
            image.setImageResource(R.drawable.king);
        }
                oa2.start();
            }
        });
        oa1.start();
    }
    public void Fold()
    {
        ImageView image1 = (ImageView) findViewById(R.id.Card1);
        image1.setImageResource(R.drawable.gray_back);
        ImageView image2 = (ImageView) findViewById(R.id.Card2);
        image2.setImageResource(R.drawable.gray_back);
        ImageView image3 = (ImageView) findViewById(R.id.Card3);
        image3.setImageResource(R.drawable.gray_back);
    }
}

