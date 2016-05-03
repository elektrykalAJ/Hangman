package com.school.ee.games.hangman;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Log;

import java.sql.Blob;

/**
 * Created by Chetan Basutkar on 4/30/2016.
 */
public class LoginDatabase {

    public static final String TAG = "LoginDatabase";

    public static final String DB_NAME = "UserDetails.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_LOGIN = "login";

    public static final String TABLE_ID = "_id";
    public static final String USER_ID = "username";
    public static final String USER_PASS = "password";
    public static final String USER_IMAGE = "image";
    public static final String USER_SCORE = "score";

    Context context;
    DbHelper dbHelper;
    SQLiteDatabase db;

    public LoginDatabase(Context context) {
        this.context = context;
        dbHelper = new DbHelper();
    }

    class DbHelper extends SQLiteOpenHelper {

        public DbHelper() {
            super(context, DB_NAME, null, DB_VERSION);
        }

        SQLiteDatabase db = this.getWritableDatabase();

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql_1 = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT,%s BLOB, %s INTEGER)",
                    TABLE_LOGIN,TABLE_ID,USER_ID,USER_PASS,USER_IMAGE,USER_SCORE);
            db.execSQL(sql_1);
            Log.d(TAG,"LOGIN TABLE CREATED");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG,"onUpgrade");
            db.execSQL("drop table if exists" + TABLE_LOGIN);
            onCreate(db);

        }
    }

    public void addLoginData(String user_id, String user_pass, byte[] user_image,int user_score){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID,user_id);
        contentValues.put(USER_PASS,user_pass);
        contentValues.put(USER_IMAGE,user_image);
        contentValues.put(USER_SCORE,user_score);
        db.insert(TABLE_LOGIN,"",contentValues);
        context.sendBroadcast(new Intent(LoginActivity.NEW_USER_ADDED));
    }

    public Cursor query() {
        db = dbHelper.getReadableDatabase();
        return db.query(TABLE_LOGIN, null, null, null, null, null, TABLE_ID + " DESC", null);
    }


}