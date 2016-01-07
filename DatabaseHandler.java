package com.meljin.meals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s_jin01 on 11/25/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "foodsHandler";
    private static final String TABLE_FOODS = "foods";

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_FOOD = "food";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE_FOODS + "("
                + KEY_ID + "INTEGER PRIMARY KEY" + KEY_DATE + " TEXT," + KEY_FOOD + " TEXT,"
                + ")";
        db.execSQL(CREATE_FOODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODS);

        // Create tables again
        onCreate(db);
    }

    public void addFood(Foods food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, food.id);
        values.put(KEY_DATE, food.date);
        values.put(KEY_FOOD, food.food);
        db.insert(TABLE_FOODS, null, values);
        db.close();
    }

    public Foods getFood(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOODS, new String[] {KEY_DATE, KEY_FOOD}, KEY_DATE + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Foods food = new Foods(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return food;
    }

    public List<Foods> getAllFoods(){
        List<Foods> foodList = new ArrayList<Foods>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FOODS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Foods food = new Foods();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setDate(cursor.getString(1));
                food.setFood(cursor.getString(2));
                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }

    public int getFoodsCount(){
        String countQuery = "SELECT  * FROM " + TABLE_FOODS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateFood(Foods food){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, food.getId());
        values.put(KEY_DATE, food.getDate());
        values.put(KEY_FOOD, food.getFood());

        // updating row
        return db.update(TABLE_FOODS, values, KEY_DATE + " = ?",
                new String[] { String.valueOf(food.getId()) });
    }

    public void deleteFood(Foods food){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_FOODS, KEY_DATE + "=?", new String[] {String.valueOf(food.getId())});
        db.close();
    }
}
