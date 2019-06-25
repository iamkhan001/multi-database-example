package com.nstudio.database.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by imran khan on 26-Sep-17.
 */

public class DataBaseUser extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private final String tag = DataBaseUser.class.getSimpleName();

    DataBaseUser(Context context, String dbName) {
        super(context, dbName, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String TABLE_USERS = "CREATE TABLE IF NOT EXISTS "
                +"users ( "
                + "id INTEGER PRIMARY KEY , "
                + "name VARCHAR(50) ,"
                + "email VARCHAR(100) ,"
                + "mobile VARCHAR(20));";

        try {
            sqLiteDatabase.execSQL(TABLE_USERS);
            Log.e(tag, "DB USER > " + TABLE_USERS);
        } catch (Exception e) {
            Log.e(tag, "DB USER > " + e);
        }




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


    }

}
