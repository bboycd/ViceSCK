package com.example.sungwon.vicenews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.database.DatabaseUtils.dumpCursor;

/**
 * Created by Owen on 9/26/2016.
 */

public class ViceDBHelper extends SQLiteOpenHelper{
    //instantiating database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ViceNews.db";
    //instantiating columns in database
    public static final String DATABASE_TABLE_NAME_LATEST = "ViceNewsLatest";
    public static final String DATABASE_TABLE_NAME_POPULAR = "ViceNewsPopular";
    public static final String VICENEWS_COLUMN_ID = "_id";
    public static final String VICENEWS_TITLE = "title";
    public static final String VICENEWS_AUTHOR = "author";
    public static final String VICENEWS_BODY = "body";
    public static final String VICENEWS_PREVIEW = "preview";
    public static final String VICENEWS_CATEGORY = "category";
    public static final String VICENEWS_THUMBNAIL = "thumbnail";
    public static final String [] VICENEWS_COLUMNS = {VICENEWS_COLUMN_ID, VICENEWS_TITLE, VICENEWS_AUTHOR, VICENEWS_BODY, VICENEWS_PREVIEW, VICENEWS_CATEGORY, VICENEWS_THUMBNAIL};

    public static final String SQL_CREATE_VICENEWS_TABLE_LATEST =
            "CREATE TABLE " + DATABASE_TABLE_NAME_LATEST +
                    "( " +
                    VICENEWS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VICENEWS_TITLE + " TEXT," +
                    VICENEWS_AUTHOR + " TEXT," +
                    VICENEWS_BODY + " TEXT," +
                    VICENEWS_PREVIEW + " TEXT," +
                    VICENEWS_CATEGORY + " TEXT," +
                    VICENEWS_THUMBNAIL + " TEXT )";
    public static final String SQL_DROP_VICENEWS_TABLE_LATEST = "DROP TABLE IF EXISTS "+ DATABASE_TABLE_NAME_LATEST;

    public static final String SQL_CREATE_VICENEWS_TABLE_POPULAR =
            "CREATE TABLE " + DATABASE_TABLE_NAME_POPULAR +
                    "( " +
                    VICENEWS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VICENEWS_TITLE + " TEXT," +
                    VICENEWS_AUTHOR + " TEXT," +
                    VICENEWS_BODY + " TEXT," +
                    VICENEWS_PREVIEW + " TEXT," +
                    VICENEWS_CATEGORY + " TEXT," +
                    VICENEWS_THUMBNAIL + " TEXT )";
    public static final String SQL_DROP_VICENEWS_TABLE_POPULAR = "DROP TABLE IF EXISTS "+ DATABASE_TABLE_NAME_POPULAR;

    //creating as Singleton class
    private static ViceDBHelper instance;

    public static ViceDBHelper getInstance(Context context){
        if(instance == null){
            instance =  new ViceDBHelper(context);
        }
        return instance;
    }

    public ViceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating table
        db.execSQL(SQL_CREATE_VICENEWS_TABLE_LATEST);
        db.execSQL(SQL_CREATE_VICENEWS_TABLE_POPULAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_VICENEWS_TABLE_LATEST);
        db.execSQL(SQL_DROP_VICENEWS_TABLE_POPULAR);
        onCreate(db);
    }

    public long addArticleLatest(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long insertedRowLatest = db.insert(DATABASE_TABLE_NAME_LATEST, null, values);
        return insertedRowLatest;

    }
    public long addArticlePopular(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long insertedRowPopular = db.insert(DATABASE_TABLE_NAME_POPULAR, null, values);
        return insertedRowPopular;
    }

    public int deleteAllArticlesLatest() {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(DATABASE_TABLE_NAME_LATEST,null,null);
        return rowsDeleted;
    }
    public int deleteAllArticlesPopular() {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(DATABASE_TABLE_NAME_POPULAR,null,null);
        return rowsDeleted;
    }

    public Cursor getRecentArticles(String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE_NAME_LATEST,VICENEWS_COLUMNS,selection,selectionArgs,null,null,null);
        dumpCursor(cursor);
        return cursor;
    }
    public Cursor getPopularArticles(String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE_NAME_POPULAR,VICENEWS_COLUMNS,selection,selectionArgs,null,null,null);
        dumpCursor(cursor);
        return cursor;
    }

    public Cursor getArticlesByIdLatest(String id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE_NAME_LATEST,VICENEWS_COLUMNS,VICENEWS_COLUMN_ID +" = ?", new String[]{id}, null, null, null);

        return cursor;
    }
    public Cursor getArticlesByIdPopular(String id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE_NAME_POPULAR,VICENEWS_COLUMNS,VICENEWS_COLUMN_ID +" = ?", new String[]{id}, null, null, null);

        return cursor;
    }

    //cursor for searching News Article
    public Cursor searchArticles(String query, String choice) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor scursor = null;
        switch (choice) {
            case "latest":

                scursor = db.query(DATABASE_TABLE_NAME_LATEST,
                        VICENEWS_COLUMNS,
                        VICENEWS_TITLE + " LIKE ? OR " + VICENEWS_AUTHOR + " LIKE ? OR " + VICENEWS_CATEGORY + " LIKE ? ",
                        new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"},
                        null,
                        null,
                        null);

                break;
            case "popular":

                scursor = db.query(DATABASE_TABLE_NAME_POPULAR,
                        VICENEWS_COLUMNS,
                        VICENEWS_TITLE + " LIKE ? OR " + VICENEWS_AUTHOR + " LIKE ? OR " + VICENEWS_CATEGORY + " LIKE ? ",
                        new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"},
                        null,
                        null,
                        null);
                break;
        }
            DatabaseUtils.dumpCursor(scursor);
            return scursor;

    }
    //cursor for showing News Article details
    public Cursor detailsArticlesLatest(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dcursor = db.query(DATABASE_TABLE_NAME_LATEST,
                VICENEWS_COLUMNS,
                VICENEWS_COLUMN_ID+ "= ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        dumpCursor(dcursor);
        return dcursor;
    }
    public Cursor detailsArticlesPopular(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dcursor = db.query(DATABASE_TABLE_NAME_POPULAR,
                VICENEWS_COLUMNS,
                VICENEWS_COLUMN_ID+ "= ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        dumpCursor(dcursor);
        return dcursor;
    }

}

