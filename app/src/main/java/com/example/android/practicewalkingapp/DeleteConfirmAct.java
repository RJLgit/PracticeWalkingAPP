package com.example.android.practicewalkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteConfirmAct extends AppCompatActivity {
    Button bckBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_confirm);
        bckBut = (Button) findViewById(R.id.buttonBack1);
        bckBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteConfirmAct.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
