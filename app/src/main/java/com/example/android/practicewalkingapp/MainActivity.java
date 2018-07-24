package com.example.android.practicewalkingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.ListItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int NUM_OF_ITEMS = 18;
    private MyAdapter myAdapter;
    private RecyclerView mWalksList;
    private Button learnMoreButton;
    private Button mapButton;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(getString(R.string.setting_color_key))) {
            String v = sharedPreferences.getString(s, getResources().getString(R.string.pref_color_light_value));
            changeColor(v);
        }
        if (s.equals(getString(R.string.settings_units_key))) {
            String p = sharedPreferences.getString(s, getResources().getString(R.string.pref_units_miles));
            changeUnits(p);
        }
    }

    private void setUpSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String s = sharedPreferences.getString(getString(R.string.setting_color_key), getString(R.string.pref_color_light_value));
        changeColor(s);
        String ss = sharedPreferences.getString(getString(R.string.settings_units_key), getString(R.string.pref_units_miles));
        changeUnits(ss);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    private void changeUnits(String s) {
        if (s.equals(getString(R.string.pref_units_miles))) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mWalksList.setLayoutManager(layoutManager);

            mWalksList.setHasFixedSize(true);
            myAdapter = new MyAdapter(this,this);
            myAdapter.setDistanceDatatoM();

            mWalksList.setAdapter(myAdapter);
        }
        if (s.equals(getString(R.string.pref_units_km))) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mWalksList.setLayoutManager(layoutManager);

            mWalksList.setHasFixedSize(true);
            myAdapter = new MyAdapter(this, this);
            myAdapter.setDistanceDataToKm();

            mWalksList.setAdapter(myAdapter);
        }
    }

    private void changeColor(String s) {
        if (s.equals(getString(R.string.pref_color_light_value))) {
            mWalksList.setBackgroundColor(getResources().getColor(R.color.colorLightBackground));
        }
        if (s.equals(getString(R.string.pref_color_primary_value))) {
            mWalksList.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if (s.equals(getString(R.string.pref_color_accent_value))) {
            mWalksList.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onListItemClick(int index) {
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("Walk", MyAdapter.WALKS_DATA.get(index));
        i.putExtra("Distance", MyAdapter.DISTANCE_DATA.get(index));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.walking_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.my_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        if (id == R.id.contact_settings) {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            String[] add = { "enquiries@nationaltrust.org.uk" };
            emailIntent.setType("*/*");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, add);
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            }
        }
        return super.onOptionsItemSelected(item);
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
        myAdapter = new MyAdapter(this,this);

        mWalksList.setAdapter(myAdapter);
        ReminderUtils.scheduleChargingReminder(this);


        learnMoreButton = (Button) findViewById(R.id.learnbutton);
        learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri walkPage = Uri.parse("https://en.wikipedia.org/wiki/Walking");
                Intent inte = new Intent(Intent.ACTION_VIEW, walkPage);
                if (inte.resolveActivity(getPackageManager()) != null) {
                   startActivity(inte);
                   // NotificationUtils.remindUser(MainActivity.this);
               }
            }
        });
        setUpSharedPreferences();



    }
}
