package com.example.sungwon.vicenews;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
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

    private static String TAG = SyncAdapter.class.getCanonicalName();

    ContentResolver mContentResolver;

    private static final String AUTHORITY = "com.example.sungwon.vicenews.NewsContentProvider";
    private static final String STOCKS_TABLE = ViceDBHelper.DATABASE_TABLE_NAME_LATEST;


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
        mContentResolver.delete(NewsContentProvider.CONTENT_POPULAR_URI_FULL, null, null);
        mContentResolver.delete(NewsContentProvider.CONTENT_RECENT_URI_FULL, null, null);
        getPopularArticles();

        getRecentArticles();

    }

    private void getRecentArticles() {
        URL viceURL = null;
        final Gson gson = new Gson();
        String data = null;
        InputStream inStream = null;
        HttpURLConnection connection = null;

        AsyncHttpClient client
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
//            TODOne: do value put based on db
            for (int i = 0; i < newsItemArray.getItems().length; i++) {
                NewsDetail details = newsItemArray.getItems()[i];
                values.put("title",details.getTitle());
                values.put("author",details.getAuthor());
                values.put("body", details.getBody());
                values.put("preview", details.getPreview());
                values.put("category", details.getCategory());
                values.put("thumbnail", details.getThumb());
                mContentResolver.insert(NewsContentProvider.CONTENT_POPULAR_URI_FULL, values);
                if (i>19){Log.d(TAG, "Story Added: "+details.getTitle());}
            }
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
