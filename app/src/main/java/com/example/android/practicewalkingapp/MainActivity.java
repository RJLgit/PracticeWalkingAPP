package com.example.android.practicewalkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MyAdapter.ListItemClickListener {

    private static final int NUM_OF_ITEMS = 18;
    private MyAdapter myAdapter;
    private RecyclerView mWalksList;

    @Override
    public void onListItemClick(int index) {
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("Walk", MyAdapter.WALKS_DATA.get(index));
        i.putExtra("Distance", MyAdapter.DISTANCE_DATA.get(index));
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWalksList = (RecyclerView) findViewById(R.id.my_recycle_view);
       // mWalksList.addItemDecoration(new DividerItemDecoration(mWalksList.getContext(), DividerItemDecoration.HORIZONTAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mWalksList.setLayoutManager(layoutManager);

        mWalksList.setHasFixedSize(true);
        myAdapter = new MyAdapter(this);

        mWalksList.setAdapter(myAdapter);

    }
}
