package com.example.sungwon.vicenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import static com.android.volley.Request.Method.HEAD;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO TRANSISTION ANIMATION
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Intent intent = new Intent();

        //WEBVIEW
        WebView webview = new WebView(this);
        String html = intent.getStringExtra("body");
        setContentView(webview);
        webview.loadData(html, "text/html", null);

        FloatingActionButton fab_share = (FloatingActionButton) findViewById(R.id.fab_share);
        fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        FloatingActionButton fab_readLater = (FloatingActionButton) findViewById(R.id.fab_readLater);
        fab_readLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
