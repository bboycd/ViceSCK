package com.example.sungwon.vicenews;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SungWon on 9/26/2016.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    ContentResolver mContentResolver;

    private static final String AUTHORITY = "com.example.sungwon.vicenews.NewsContentProvider";
    private static final String STOCKS_TABLE = ViceDBHelper.DATABASE_TABLE_NAME_LATEST;
    public static final Uri SYMBOLS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + STOCKS_TABLE + "/symbols");

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs, ContentResolver mContentResolver) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mContentResolver = context.getContentResolver();


    }


    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d(SyncAdapter.class.getName(), "Starting Sync");
        String page = bundle.getString("page");
//        mContentResolver.delete(NewsContentProvider.CONTENT_URI, null, null);
        getRecentArticles();
        getPopularArticles();
    }

    private void getRecentArticles() {
        URL viceURL = null;
        final Gson gson = new Gson();
        String data = null;
        InputStream inStream = null;
        HttpURLConnection connection = null;
        try{
            viceURL = new URL("http://vice.com/api/getlatest/0");
            connection = (HttpURLConnection) viceURL.openConnection();
            connection.connect();
            inStream = connection.getInputStream();
            data = getInputData(inStream);
            SearchResult result = gson.fromJson(data, SearchResult.class);
            NewsItem newsItemArray = result.data;
            ContentValues values = new ContentValues();
//            values.put()
//            TODO: do value put based on db
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void getPopularArticles() {
        URL viceURL = null;
        final Gson gson = new Gson();
        String data = null;
        InputStream inStream = null;
        HttpURLConnection connection = null;
        try{
            viceURL = new URL("http://vice.com/api/getmostpopular/0");
            connection = (HttpURLConnection) viceURL.openConnection();
            connection.connect();
            inStream = connection.getInputStream();
            data = getInputData(inStream);
            SearchResult result = gson.fromJson(data, SearchResult.class);
            NewsItem newsItemArray = result.data;
            ContentValues values = new ContentValues();
//            values.put()
//            TODO: do value put based on db
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String getInputData(InputStream inStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        String data = null;

        while ((data = reader.readLine()) != null){
            builder.append(data);
        }

        reader.close();

        return builder.toString();
    }
}
