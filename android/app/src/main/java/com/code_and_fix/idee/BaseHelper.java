package com.code_and_fix.idee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mary on 25.11.2016.
 */

public class BaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "saved_ideas";
    private static final int DB_VERSION = 1;

    BaseHelper(Context context)
    {
    super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE saved_ideas (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, idea TEXT, tag TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
