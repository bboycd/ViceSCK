package com.example.sungwon.vicenews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Owen on 9/26/2016.
 */

public class ViceDBHelper extends SQLiteOpenHelper{
    //instantiating database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ViceNews.db";
    //instantiating columns in database
    public static final String DATABASE_TABLE_NAME = "ViceNews";
    public static final String VICENEWS_COLUMN_ID = "_id";
    public static final String VICENEWS_TITLE = "title";
    public static final String VICENEWS_AUTHOR = "author";
    public static final String VICENEWS_BODY = "body";
    public static final String VICENEWS_PREVIEW = "preview";
    public static final String VICENEWS_CATEGORY = "category";
    public static final String VICENEWS_THUMBNAIL = "thumbnail";
    public static final String [] VICENEWS_COLUMNS = {VICENEWS_COLUMN_ID, VICENEWS_TITLE, VICENEWS_AUTHOR, VICENEWS_BODY, VICENEWS_PREVIEW, VICENEWS_CATEGORY, VICENEWS_THUMBNAIL};

    public static final String SQL_CREATE_VICENEWS_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_NAME +
                    "( " +
                    VICENEWS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VICENEWS_TITLE + " TEXT," +
                    VICENEWS_AUTHOR + " TEXT," +
                    VICENEWS_BODY + " TEXT," +
                    VICENEWS_PREVIEW + " TEXT," +
                    VICENEWS_CATEGORY + " TEXT," +
                    VICENEWS_THUMBNAIL + " TEXT )";
    public static final String SQL_DROP_VICENEWS_TABLE = "DROP TABLE IF EXISTS "+DATABASE_TABLE_NAME;

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
        //creating Comedians table
        db.execSQL(SQL_CREATE_VICENEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_VICENEWS_TABLE);
        onCreate(db);
    }

    public long addArticle(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long insertedRow = db.insert(DATABASE_TABLE_NAME, null, values);
        return insertedRow;
    }

    public int deleteAllArticles() {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(DATABASE_TABLE_NAME,null,null);
        return rowsDeleted;
    }
    public Cursor getArticles(String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE_NAME,VICENEWS_COLUMNS,selection,selectionArgs,null,null,null);
        DatabaseUtils.dumpCursor(cursor);
        return cursor;
    }

    public Cursor getArticlesById(String id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE_NAME,VICENEWS_COLUMNS,VICENEWS_COLUMN_ID +" = ?", new String[]{id}, null, null, null);

        return cursor;
    }

    //cursor for searching News Article
    public Cursor searchArticles(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor scursor = db.query(DATABASE_TABLE_NAME,
                VICENEWS_COLUMNS,
                VICENEWS_TITLE + " LIKE ? OR " + VICENEWS_AUTHOR + " LIKE ? OR " + VICENEWS_CATEGORY + " LIKE ? ",
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"},
                null,
                null,
                null);
        DatabaseUtils.dumpCursor(scursor);
        return scursor;
    }
    //cursor for showing News Article details
    public Cursor detailsArticles(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dcursor = db.query(DATABASE_TABLE_NAME,
                VICENEWS_COLUMNS,
                VICENEWS_COLUMN_ID+ "= ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        DatabaseUtils.dumpCursor(dcursor);
        return dcursor;
    }

}

