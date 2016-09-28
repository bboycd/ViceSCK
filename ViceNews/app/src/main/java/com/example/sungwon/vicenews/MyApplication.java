package com.example.sungwon.vicenews;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by SungWon on 9/28/2016.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this); //enables stetho
    }
}
