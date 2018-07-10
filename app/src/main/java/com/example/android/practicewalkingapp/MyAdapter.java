package com.example.android.practicewalkingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public MyAdapter(ListItemClickListener listItemClickListener) {
        mListItemClickListener = listItemClickListener;
        WALKS_DATA = DummyData.getWalks();
        DISTANCE_DATA = DummyData.getDistances();
        mNumberItems = WALKS_DATA.size();
    }
  //  public static final HashMap<String, Integer> MAP_DATA = DummyData.getAllData();
    public static ArrayList<String> WALKS_DATA;
    public static ArrayList<Integer> DISTANCE_DATA;
    private int mNumberItems;
    final private ListItemClickListener mListItemClickListener;

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

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.bind(WALKS_DATA.get(position), DISTANCE_DATA.get(position));
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView typeOfWalkView;
        TextView distanceOfWalkView;

        public MyViewHolder(View itemView) {
            super(itemView);
            typeOfWalkView = (TextView) itemView.findViewById(R.id.walk_text_view);
            distanceOfWalkView = (TextView) itemView.findViewById(R.id.distance_text_view);
            itemView.setOnClickListener(this);

        }

        void bind(String s, int i) {
            typeOfWalkView.setText(s);
            distanceOfWalkView.setText(String.valueOf(i) + " miles");
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
