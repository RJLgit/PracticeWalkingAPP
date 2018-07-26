package com.example.android.practicewalkingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "walks.db";
    public static final String TABLE_NAME = "walks_table";
    public static final String ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_DIST = "Distance";
    public static final String COL_LOC = "Location";
    public static final String COL_DISTKM = "DistanceKm";


    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Distance DOUBLE, Location TEXT, DistanceKm DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String dist, String loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        Double d = Double.parseDouble(dist);
        contentValues.put(COL_DIST, d);
        contentValues.put(COL_LOC, loc);
        Double dkm = d * 1.61;
        contentValues.put(COL_DISTKM, dkm);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getIds() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME, null);
        ArrayList<String> res = new ArrayList<>();
        c.moveToFirst();
            while (c.isAfterLast() == false) {
                res.add(String.valueOf(c.getInt(c.getColumnIndex(ID))));
                        c.moveToNext();
            }
            c.close();
            return res;
        }

                //iterate through cursor and populate ArrayList with ids.


    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] { id });
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }
}
