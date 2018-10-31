package com.example.chris.bj_final.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by chris on 9/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "blackjack";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_QUERY = "CREATE TABLE " + BlackJackContract.BlackJackEntry.TABLE_NAME
            + " ( " + BlackJackContract.BlackJackEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BlackJackContract.BlackJackEntry.COLUMN_NAME + " text, "
            + BlackJackContract.BlackJackEntry.COLUMN_HS + " text);";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations", "Database created...");
    }

    @Override
    public void OnCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table Created...");
    }

    @Override
    public void OnUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL ("DROP TABLE IF EXISTS " + BlackJackContract.BlackJackEntry.TABLE_NAME);
        Log.d("Database operations" , "Database updated...");
    }

    public void AddUser(String name, int highScore, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put (BlackJackContract.BlackJackEntry.COLUMN_NAME,name);
        contentValues.put(BlackJackContract.BlackJackEntry.COLUMN_HS,highScore);
        long l = db.insert(BlackJackContract.BlackJackEntry.TABLE_NAME, null, contentValues);
        Log.d("Database operations", "One row inserted...");
    }

    public Cursor GetInfo (SQLiteDatabase db){
        String[] projection = {BlackJackContract.BlackJackEntry.COLUMN_NAME, BlackJackContract.BlackJackEntry.COLUMN_HS};
        Cursor cursor = db.query(BlackJackContract.BlackJackEntry.TABLE_NAME,projection, null, null, null, null, null);
        return cursor;
    }
}

