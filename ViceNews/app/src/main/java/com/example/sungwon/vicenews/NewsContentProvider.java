package com.example.sungwon.vicenews;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by SungWon on 9/26/2016.
 */

public class NewsContentProvider extends ContentProvider {

    private ViceDBHelper myDB;
    private static final String AUTHORITY = "ly.generalassemb.drewmahrt.syncadapterexample.NewsContentProvider";
    private static final String ARTICLES_TABLE = "articles";
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + ARTICLES_TABLE);

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
