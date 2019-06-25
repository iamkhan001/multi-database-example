package com.nstudio.database.test;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by imran khan on 26-Sep-17.
 */

public class DataBaseApp extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private final String tag = DataBaseApp.class.getSimpleName();

    DataBaseApp(Context context) {
        super(context, "appDatabase", null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String TABLE_USER_IDS = "CREATE TABLE IF NOT EXISTS "
                +"databaseNames ( "
                + "id INTEGER PRIMARY KEY , "
                + "dbName VARCHAR(20) UNIQUE NOT NULL);";

        try {
            sqLiteDatabase.execSQL(TABLE_USER_IDS);
            Log.e(tag, "DB USER > " + TABLE_USER_IDS);
        } catch (Exception e) {
            Log.e(tag, "DB USER > " + e);
        }

        ArrayList<String> list  = new ArrayList<>();





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {



    }




}
