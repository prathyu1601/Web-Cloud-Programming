package com.example.sampath.mobilelab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLLiteDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Words.db";


    public static final String TABLE_NAME = "WORDS";
    public static final String COLUMN_ID = "Word_id";
    public static final String COLUMN_NAME = "word_name";
    public static final String COLUMN_TEXT = "word_ans";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_WORD = "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT, "
                + COLUMN_TEXT+ " TEXT )";

        db.execSQL(SQL_CREATE_WORD);
    }

    public void deleteEntries()
    {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void create(String word_name,String word_ans){
        //fill this method to insert the row

        // Create map of values
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, word_name);
        values.put(COLUMN_TEXT, word_ans);
        // Insert row (returns primary key)
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(TABLE_NAME, null, values);
    }

    public Cursor retrieve(){
        SQLiteDatabase db = getReadableDatabase();
        String[] cols = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_TEXT};

        String sortOrder = COLUMN_NAME + " ASC";

        Cursor c = db.query(
                TABLE_NAME,                    // The table to query
                cols,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        return c;
    }

    public void update(int id, String word_name, String word_ans){

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, word_name);
        values.put(COLUMN_TEXT, word_ans);

        // Get rowid to update that row
        String whereClause = COLUMN_ID + " = ?";
        String[] args = { String.valueOf(id) };

        SQLiteDatabase db = getReadableDatabase();
        int count = db.update(
                TABLE_NAME,
                values,
                whereClause,
                args);
    }

}
