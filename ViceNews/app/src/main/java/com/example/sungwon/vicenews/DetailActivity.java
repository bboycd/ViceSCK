package com.example.sungwon.vicenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView mTitleText;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitleText = (TextView)findViewById(R.id.titleText);
        mImageView = (ImageView)findViewById(R.id.imageView_Detail);


        //TODO TRANSISTION ANIMATION
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Intent intent = getIntent();

        //WEBVIEW
        WebView webview;
        webview = (WebView)findViewById(R.id.webview);
        String title = intent.getStringExtra("title");
        String html = intent.getStringExtra("body");
        String url = intent.getStringExtra("image");
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setScrollbarFadingEnabled(false);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.loadData(html, null, null);
        Picasso.with(DetailActivity.this).load(url).fit().into(mImageView);
        mTitleText.setText(title);


//        FloatingActionButton fab_share = (FloatingActionButton) findViewById(R.id.fab_share);
//        fab_share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
//
//        FloatingActionButton fab_readLater = (FloatingActionButton) findViewById(R.id.fab_readLater);
//        fab_readLater.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
