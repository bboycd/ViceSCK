package com.example.sungwon.vicenews;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by SungWon on 9/28/2016.
 */

public class NewsPopularProvider extends ContentProvider {

    private ViceDBHelper myDB;
    private static final String AUTHORITY = "com.example.sungwon.vicenews.NewsPopularProvider";

    private static final String ARTICLES_POPULAR_TABLE = ViceDBHelper.DATABASE_TABLE_NAME_POPULAR;

    public static final Uri CONTENT_POPULAR_URI = Uri.parse("content://"
            + AUTHORITY + "/" + ARTICLES_POPULAR_TABLE);

    public static final int ARTICLES_POPULAR = 1;
    public static final int ARTICLES_POPULAR_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, ARTICLES_POPULAR_TABLE, ARTICLES_POPULAR);
        sURIMatcher.addURI(AUTHORITY, ARTICLES_POPULAR_TABLE + "/#", ARTICLES_POPULAR_ID);
    }

    @Override
    public boolean onCreate() {
        myDB = new ViceDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        int uriType = sURIMatcher.match(uri);

        Cursor cursor = null;

        switch (uriType) {
            case ARTICLES_POPULAR:
                //TODO: Make query for popular articles auto set to 0
//                cursor = myDB.getPopularArticles(uri.getLastPathSegment());
                break;
            case ARTICLES_POPULAR_ID:
                //TODO: Make query for pop art
//                cursor = myDB.getPopularArticles(uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
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
