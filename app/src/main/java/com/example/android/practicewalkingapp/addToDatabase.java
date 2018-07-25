package com.example.android.practicewalkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class addToDatabase extends AppCompatActivity {
    @BindView(R.id.textName)
    TextView walkName;
    @BindView(R.id.textDist)
    TextView walkDist;
    @BindView(R.id.textLoc)
    TextView walkLoc;
    @BindView(R.id.addDataButton)
    Button addWalkButton;
    @BindView(R.id.viewWalksButton)
    Button backBut;
    private DatabaseHelperClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_database);
        ButterKnife.bind(this);
        db = MainActivity.getMyDatabase();
    }

    @OnClick(R.id.addDataButton)
   public void addWalk() {
        String w = walkName.getText().toString();
        String d = walkDist.getText().toString();
        String l = walkLoc.getText().toString();
        boolean b = db.insertData(w, d, l);
        if (b) {
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.viewWalksButton)
    public void goBack() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
