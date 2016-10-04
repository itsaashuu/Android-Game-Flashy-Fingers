package com.example.abc.flashyfingers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView result;
    Button b;
    int points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent i = getIntent();
        points = i.getIntExtra("point",0);
        result = (TextView) findViewById(R.id.resultN);
       // hs = (TextView) findViewById(R.id.highS);
        b = (Button) findViewById(R.id.startNew);

        SharedPreferences sharedPreferences = getSharedPreferences("Mydata",MODE_PRIVATE);


        result.setText("GAME OVER\nYour score is "+ Integer.toString(points));
       // hs.setText("Highscore : " + Integer.toString(sharedPreferences.getInt("high",0)));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(ResultActivity.this,"Cannot press back from here",Toast.LENGTH_SHORT).show();
    }
}
