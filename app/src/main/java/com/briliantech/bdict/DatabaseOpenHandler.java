package com.briliantech.bdict;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Satish on 17-05-2017.
 */

public class DatabaseOpenHandler extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "DICT.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
