package com.meljin.meals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by s_jin01 on 1/5/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "id";
    public static final String FOOD = "food";

    private final static String DATABASE_NAME = "foods.db";
    private final static int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table"
            + COLUMN_ID + "(" + FOOD + ")";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS"
            + DATABASE_NAME;

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
