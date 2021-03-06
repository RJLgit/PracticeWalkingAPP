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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MyAdapter.ListItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int NUM_OF_ITEMS = 18;
    private MyAdapter myAdapter;
    @BindView(R.id.my_recycle_view)
    RecyclerView mWalksList;
    @BindView(R.id.learnbutton)
    Button learnMoreButton;
    private Button mapButton;
    @BindView(R.id.databasebutton) Button dataBut;
    public static DatabaseHelperClass myDb;


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
            MyAdapter.miles = true;
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mWalksList.setLayoutManager(layoutManager);

            mWalksList.setHasFixedSize(true);
            myAdapter = new MyAdapter(this, this, myDb);
            // myAdapter.setDistanceDatatoM();

            mWalksList.setAdapter(myAdapter);
        /**

         */
        }
        if (s.equals(getString(R.string.pref_units_km))) {
            MyAdapter.miles = false;
           // /**
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mWalksList.setLayoutManager(layoutManager);

            mWalksList.setHasFixedSize(true);
            myAdapter = new MyAdapter(this, this, myDb);
           // myAdapter.setDistanceDataToKm();

            mWalksList.setAdapter(myAdapter);
            //*/
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ss = sharedPreferences.getString(getString(R.string.settings_units_key), getString(R.string.pref_units_miles));
        Intent i = new Intent(this, DetailsActivity.class);
        MyAdapter.mData.moveToPosition(index);
        i.putExtra("Walk", MyAdapter.mData.getString(1));
        //add if/else to check shared preferences for miles or kms
        if (ss.equals("Miles")) {
            i.putExtra("Distance", MyAdapter.mData.getDouble(2));
        } else if (ss.equals("Kms")) {
            Double k = MyAdapter.mData.getDouble(2) * 1.61;
            k = (double) Math.round(k * 100d) / 100d;
            i.putExtra("Distance", k);
        }

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
            String[] add = {"enquiries@nationaltrust.org.uk"};
            emailIntent.setType("*/*");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, add);
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public static DatabaseHelperClass getMyDatabase() {
        return myDb;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // mWalksList = (RecyclerView) findViewById(R.id.my_recycle_view);
        // mWalksList.addItemDecoration(new DividerItemDecoration(mWalksList.getContext(), DividerItemDecoration.HORIZONTAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mWalksList.setLayoutManager(layoutManager);
        if (myDb == null)
        {
            myDb = new DatabaseHelperClass(this);
        }
        mWalksList.setHasFixedSize(true);
        myAdapter = new MyAdapter(this, this, myDb);

        mWalksList.setAdapter(myAdapter);
        ReminderUtils.scheduleChargingReminder(this);


        // learnMoreButton = (Button) findViewById(R.id.learnbutton);
        /**
         learnMoreButton.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        Uri walkPage = Uri.parse("https://en.wikipedia.org/wiki/Walking");
        Intent inte = new Intent(Intent.ACTION_VIEW, walkPage);
        if (inte.resolveActivity(getPackageManager()) != null) {
        startActivity(inte);
        // NotificationUtils.remindUser(MainActivity.this);
        }
        }
        });
         */
        setUpSharedPreferences();


    }

    @OnClick(R.id.databasebutton)
    public void dataButClick() {
        Intent i = new Intent(this, addToDatabase.class);
        startActivity(i);
    }

    @OnClick(R.id.learnbutton)
    public void learnMoreClick() {
        Uri walkPage = Uri.parse("https://en.wikipedia.org/wiki/Walking");
        Intent inte = new Intent(Intent.ACTION_VIEW, walkPage);
        if (inte.resolveActivity(getPackageManager()) != null) {
            startActivity(inte);
        }
    }
}
