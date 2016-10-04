package com.example.abc.flashyfingers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4;
    String ans = "";
    String userAns = "";
    ArrayList<String> wordList;
    int flag = 0;
    boolean win = false;
    TextView result,time;
    private int points;
    final CounterClass timer = new CounterClass(10000,1000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            initalise();

        Intent intent = getIntent();
        points = intent.getIntExtra("pts", 0);
        result.setText(Integer.toString(points));
        WordGenerator wg = new WordGenerator();
        wordList = wg.getList();
        buttonsettext();
        setButtonOnClick();
        TernarySearchTree tst = new TernarySearchTree();
        tst.insert(wordList.get(0));
        tst.insert(wordList.get(1));
        tst.insert(wordList.get(2));
        tst.insert(wordList.get(3));
        ans = tst.toString();
        checkForVictory();
    }

    private void checkForVictory() {
        if(flag==4) {
            win = check(ans, userAns);
            if(win){
                timer.cancel();
                Intent i = new Intent(this, MainActivity.class);
                points++;
                //Log.d("GOOGLE", Integer.toString(points));
                i.putExtra("pts", points);
                startActivity(i);

            }else{
               // points = 0;
                result.setText("GAME OVER");
                timer.cancel();
                SharedPreferences preferences = getSharedPreferences("Mydata", MODE_PRIVATE);
                if(points > preferences.getInt("high", 0))
                    preferences.edit().putInt("high",points);
                //timer.onFinish();
                Intent i = new Intent(this, ResultActivity.class);
                i.putExtra("point", points);
                startActivity(i);
            }
        }
    }

    private void setButtonOnClick() {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }

    private void buttonsettext() {
        b1.setText(wordList.get(0));
        b2.setText(wordList.get(1));
        b3.setText(wordList.get(2));
        b4.setText(wordList.get(3));
    }


    private void initalise() {
        b1 = (Button) findViewById(R.id.B1);
        b2 = (Button) findViewById(R.id.B2);
        b3 = (Button) findViewById(R.id.B3);
        b4 = (Button) findViewById(R.id.B4);
        result = (TextView) findViewById(R.id.resultN);
        time = (TextView) findViewById(R.id.timeT);
        wordList = new ArrayList<String>();
        timer.start();
    }


    public boolean check(String s1, String s2){
        return s1.equalsIgnoreCase(s2);
    }

    @Override
    public void onClick(View v) {
     int id = v.getId();
        switch(id){
            case R.id.B1:
                userAns+= b1.getText().toString();
                flag++;
                b1.setClickable(false);
                b1.setBackgroundColor(Color.DKGRAY);
                checkForVictory();
                break;
            case R.id.B2:
                userAns+= b2.getText().toString();
                flag++;
                b2.setClickable(false);
                b2.setBackgroundColor(Color.DKGRAY);
                checkForVictory();
                break;
            case R.id.B3:
                userAns+= b3.getText().toString();
                flag++;
                b3.setClickable(false);
                b3.setBackgroundColor(Color.DKGRAY);
                checkForVictory();
                break;
            case R.id.B4:
                userAns+= b4.getText().toString();
                flag++;
                b4.setClickable(false);
                b4.setBackgroundColor(Color.DKGRAY);
                checkForVictory();
                break;
        }
    }

    public class CounterClass extends CountDownTimer{

        public CounterClass(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millis)- TimeUnit.MILLISECONDS.toMinutes(millis));
            time.setText(hms);
            time.setTextColor(Color.parseColor("#ff0000"));
        }

        @Override
        public void onFinish(){
            time.setText("Time Over");
            result.setVisibility(View.VISIBLE);
            result.setText("GameOver");
            SharedPreferences preferences = getSharedPreferences("Mydata", MODE_PRIVATE);
            if(points > preferences.getInt("high", 0))
                preferences.edit().putInt("high",points);


                // points = 0;
                result.setText("GAME OVER");
                timer.cancel();
                preferences = getSharedPreferences("Mydata", MODE_PRIVATE);
                if(points > preferences.getInt("high", 0))
                    preferences.edit().putInt("high",points);
                //timer.onFinish();
                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                i.putExtra("point", points);
                startActivity(i);

        }
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(MainActivity.this,"Cannot press back from here",Toast.LENGTH_SHORT).show();
    }
}
