package com.example.sungwon.vicenews;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by SungWon on 9/26/2016.
 */

public class NewsContentProvider extends ContentProvider {

    private ViceDBHelper myDB;
    private static final String AUTHORITY = "com.example.sungwon.vicenews.NewsContentProvider";
    private static final String ARTICLES_TABLE = ViceDBHelper.DATABASE_TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + ARTICLES_TABLE);

    public static final int ARTICLES = 1;
    public static final int ARTICLES_ID = 2;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, ARTICLES_TABLE, ARTICLES);
        sURIMatcher.addURI(AUTHORITY, ARTICLES_TABLE + "/#", ARTICLES_ID);
    }

    @Override
    public boolean onCreate() {
        myDB = new ViceDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = sURIMatcher.match(uri);

        Cursor cursor;

        switch (uriType) {
            case ARTICLES:
                cursor = myDB.getArticlesById(uri.getLastPathSegment());
                break;
            case ARTICLES_ID:
                cursor = myDB.getArticles(selection, selectionArgs);
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
