package com.briliantech.bdict;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


public class DictDbHandler {

    public static final String TABLE_DICT_ITEMS = "dict_items";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_WORD_TYPE = "wordtype";
    public static final String COLUMN_WORD_DESC = "description";

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DictDbHandler instance;

    private DictDbHandler(Context context) {
        this.openHelper = new DatabaseOpenHandler(context);
    }

    public static DictDbHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DictDbHandler(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

   public boolean search_word(String word){
       boolean s = false;
       Cursor c = database.rawQuery("SELECT id," + COLUMN_WORD + " FROM " + TABLE_DICT_ITEMS + " WHERE " + COLUMN_WORD + "=\"" + word.toString() + "\" COLLATE NOCASE", null);
       if(c != null){
           if(c.moveToFirst()) {
               if(c.getString(1).equalsIgnoreCase(word)){
                   s = true;
               }
               else {
                   s = false;
               }
           }
       }
       return s;
   }

    public String fetch_word_desc(String word){
        String s = "";
        Cursor c = database.rawQuery("SELECT " + COLUMN_WORD + "," + COLUMN_WORD_DESC + " FROM " + TABLE_DICT_ITEMS + " WHERE " + COLUMN_WORD + "=\"" + word + "\" COLLATE NOCASE", null);
        if(c != null){
            if(c.moveToFirst()) {
                if(c.getString(0).equalsIgnoreCase(word)) {
                    s = c.getString(1).toString();
                }
            }
        }
        return s;
    }

    public String databaseToString() {
        String s = "";
        Cursor c = database.rawQuery("SELECT " + COLUMN_WORD + "," + COLUMN_WORD_DESC + " FROM " + TABLE_DICT_ITEMS, null);
        if(c != null){
            if(c.moveToFirst()) {
                do {
                    s += c.getString(0);
                    s += "==>";
                    s += c.getString(1);
                    s += "\n";
                } while (c.moveToNext());
            }
        }
        return s;
    }
}

