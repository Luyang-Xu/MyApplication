package com.example.luyang.myapplication.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by luyang on 2017/11/6.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String create_table="CREATE TABLE Book" +
            "(" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    author TEXT," +
            "    price REAL," +
            "    pages INTEGER," +
            "    name TEXT" +
            ")";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
        Toast.makeText(mContext,"create succeed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
