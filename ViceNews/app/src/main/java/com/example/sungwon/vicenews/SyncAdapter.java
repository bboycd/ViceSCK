package com.example.sungwon.vicenews;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by SungWon on 9/26/2016.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    ContentResolver mContentResolver;

    private static final String AUTHORITY = "com.example.sungwon.vicenews.NewsContentProvider";
    private static final String STOCKS_TABLE = ViceDBHelper.DATABASE_TABLE_NAME;
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

    }
}
