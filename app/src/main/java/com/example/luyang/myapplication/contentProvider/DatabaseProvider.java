package com.example.luyang.myapplication.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.luyang.myapplication.dbutil.MyDatabaseHelper;

/**
 * Created by luyang on 2017/11/7.
 */

public class DatabaseProvider extends ContentProvider {

    private static final int BOOK_DIR = 0;

    private static final int BOOK_ITEM = 1;

    private static final int CATEGORY_DIR = 2;

    private static final int CATEGORY_ITEM = 3;

    private static final String AUTHORITY = "com.example.luyang.myapplication.provider";

    private static UriMatcher uriMatcher;

    MyDatabaseHelper mdb;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "Book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "Category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "Category/#", CATEGORY_ITEM);

    }


    @Override
    public boolean onCreate() {
        mdb = new MyDatabaseHelper(getContext(), "bookstore.db", null, 2);
        return true;
    }

    /**
     * 可用sql或者内置的数据库操作函数ContentValues传过来的值操作
     *
     * @param uri
     * @param strings
     * @param s
     * @param strings1
     * @param s1
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = mdb.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.rawQuery("select * from Book", null);
                break;
            case BOOK_ITEM:
                String bid = uri.getPathSegments().get(1);
                cursor = db.rawQuery("select * from Book where id = ?", new String[]{bid}, null);
                break;
            case CATEGORY_DIR:
                cursor = db.rawQuery("select * from Category", null);
                break;
            case CATEGORY_ITEM:
                String cid = uri.getPathSegments().get(1);
                cursor = db.rawQuery("select * from Category where id=?", new String[]{cid}, null);
                break;
            default:
                break;
        }
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mdb.getWritableDatabase();
        Uri returnuri = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:

            case BOOK_ITEM:
                long newbookid = db.insert("Book", null, contentValues);
                returnuri = Uri.parse("content://" + AUTHORITY + "/Book/" + newbookid);
                break;
            case CATEGORY_DIR:

            case CATEGORY_ITEM:
                long newcategoryid = db.insert("Category", null, contentValues);
                returnuri = Uri.parse("content://" + AUTHORITY + "/Category/" + newcategoryid);
                break;
            default:
                break;
        }
        return returnuri;
    }

    /**
     * @param uri
     * @param s:slection
     * @param strings:selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = mdb.getWritableDatabase();
        int rownumber = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                rownumber = db.delete("Book", s, strings);
                break;
            case BOOK_ITEM:
                String bid = uri.getPathSegments().get(1);
                rownumber = db.delete("Book", "id = ?", new String[]{bid});
                break;
            case CATEGORY_DIR:
                rownumber = db.delete("Category", s, strings);
                break;
            case CATEGORY_ITEM:
                String cid = uri.getPathSegments().get(1);
                rownumber = db.delete("Category", "id = ?", new String[]{cid});
                break;
            default:
                break;
        }
        return rownumber;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = mdb.getWritableDatabase();
        int rownumber = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                rownumber = db.update("Book", contentValues, s, strings);
                break;
            case BOOK_ITEM:
                String bid = uri.getPathSegments().get(1);
                rownumber = db.update("Book", contentValues, "id = ?", new String[]{bid});
                break;
            case CATEGORY_DIR:
                rownumber = db.update("Category", contentValues, s, strings);
                break;
            case CATEGORY_ITEM:
                String cid = uri.getPathSegments().get(1);
                rownumber = db.update("Category", contentValues, "id = ?", new String[]{cid});
                break;
            default:
                break;
        }
        return rownumber;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.luyang.myapplication.provider.Book";

            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.luyang.myapplication.provider.Book";


            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.luyang.myapplication.provider.Category";

            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.luyang.myapplication.provider.Category";

            default:
                break;
        }
        return null;
    }
}
