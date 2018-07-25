package com.example.android.practicewalkingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.Settings.Global.getString;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public MyAdapter(Context context, ListItemClickListener listItemClickListener, DatabaseHelperClass databaseHelperClass) {

        mData = databaseHelperClass.getAllData();
        mListItemClickListener = listItemClickListener;
        WALKS_DATA = DummyData.getWalks();
        DISTANCE_DATA = DummyData.getDistances();
        ADDRESS_DATA = DummyData.getAddresses();
        // mNumberItems = WALKS_DATA.size();
        mNumberItems = mData.getCount();
        c = context;
    }
  //  public static final HashMap<String, Integer> MAP_DATA = DummyData.getAllData();
    public static ArrayList<String> WALKS_DATA;
    public static ArrayList<Double> DISTANCE_DATA;
    public static ArrayList<String> ADDRESS_DATA;
    private int mNumberItems;
    final private ListItemClickListener mListItemClickListener;
    private Context c;
    private Cursor mData;

    public interface ListItemClickListener {
        void onListItemClick(int index);
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForListItem = R.layout.recycler_view_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutForListItem, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    public void setDistanceDataToKm() {
        ArrayList<Double> n = new ArrayList<>();
        for (Double i: DISTANCE_DATA) {
            Double p = i * 1.61;

            p = (double) Math.round(p * 100d) / 100d;
            n.add(p);

        }
        DISTANCE_DATA = n;
    }

    public void setDistanceDatatoM () {
        DISTANCE_DATA = DummyData.getDistances();
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        mData.moveToPosition(position);



        holder.bind(mData.getString(1), mData.getDouble(2), mData.getString(3));

        // holder.bind(WALKS_DATA.get(position), DISTANCE_DATA.get(position), ADDRESS_DATA.get(position));
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView typeOfWalkView;
        TextView distanceOfWalkView;
        Button mapButton;
        String addressData;

        public MyViewHolder(View itemView) {
            super(itemView);
            typeOfWalkView = (TextView) itemView.findViewById(R.id.walk_text_view);
            distanceOfWalkView = (TextView) itemView.findViewById(R.id.distance_text_view);
            addressData = "1600 Ampitheatre Parkway, CA";
            mapButton = (Button) itemView.findViewById(R.id.mapButton);
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String add = addressData;
                    Uri.Builder builder = new Uri.Builder();
                    builder.scheme("geo").path("0,0").query(add);
                    Uri addressUri = builder.build();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(addressUri);
                    c.startActivity(intent);


                }
            });
            itemView.setOnClickListener(this);

        }

        void bind(String s, Double i, String a) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
            String units = " " + sharedPreferences.getString(c.getResources().getString(R.string.settings_units_key), c.getResources().getString(R.string.pref_units_miles));

            typeOfWalkView.setText(s);
            distanceOfWalkView.setText(String.valueOf(i) + units);
            addressData = a;

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
    mListItemClickListener.onListItemClick(clickedPosition);

            // Open new activity and pass data
        }

    }
}
