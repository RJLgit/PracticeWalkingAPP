package com.example.android.practicewalkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.message_text_view) TextView congratsTextView;
    @BindView(R.id.back_button) Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        //congratsTextView = (TextView) findViewById(R.id.message_text_view);
       // backButton = (Button) findViewById(R.id.back_button);


        Intent intent = getIntent();
        String whichWalk = null;
        double whichDistance = 0;
        if (intent.hasExtra("Walk")) {
            whichWalk = intent.getStringExtra("Walk");
        }
        if (intent.hasExtra("Distance")) {
            whichDistance = intent.getDoubleExtra("Distance", 0);
        }
        if (whichWalk != null) {
            congratsTextView.setText("Congrats, you have completed the " + whichWalk + " walk, which covered a distance of " + String.valueOf(whichDistance) + " miles.");
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
