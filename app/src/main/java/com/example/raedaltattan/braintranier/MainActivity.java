package com.example.raedaltattan.braintranier;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView sumTextView;
    TextView resultTextView;
    int score = 0;
    int numberOfPoints = 0;
    TextView pointTextView;
    TextView timverTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void generateQuastions(){

        Random rand =  new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int inCorrectAnswer;

        for (int i=0 ; i<4 ; i++) {
            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);
            } else {
                inCorrectAnswer = rand.nextInt(41);
                while (inCorrectAnswer == a + b) {

                    inCorrectAnswer = rand.nextInt(41);

                }
                answers.add(inCorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    public void start(View view){

        startButton.setVisibility(view.INVISIBLE);
    }

    public void chooseAnswer(View view)
    {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct !");


        }else {
            resultTextView.setText("Wrong !");

        }
        numberOfPoints++;
        pointTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfPoints));
        generateQuastions();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView) ;
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        timverTextView = (TextView) findViewById(R.id.timverTextView);

      generateQuastions();

      new CountDownTimer(30100 , 1000){

          @Override
          public void onTick(long milliSeconds) {
              timverTextView.setText(String.valueOf(milliSeconds / 1000 ));
          }

          @Override
          public void onFinish() {
              startButton.setVisibility(View.VISIBLE);
          }
      }.start();
    }
}
