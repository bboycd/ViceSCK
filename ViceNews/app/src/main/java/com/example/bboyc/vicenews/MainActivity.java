package com.example.bboyc.vicenews;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab1, fab2, fab3, fab4, fab;

    Animation fab_close, fab_open, rotate_anticlockwise, rotate_clockwise;
    boolean isOpen = true;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //ANIMATION LAYOUTS
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        rotate_anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        rotate_clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);

        //MY FLOATING ACTION BUTTONS
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen)
                {
                    fab.startAnimation(rotate_anticlockwise);

                    fab1.startAnimation(fab_close);
                    fab4.startAnimation(fab_close);
                    fab3.startAnimation(fab_close);
                    fab2.startAnimation(fab_close);

                    fab1.setClickable(false);
                    fab4.setClickable(false);
                    fab3.setClickable(false);
                    fab2.setClickable(false);

                    isOpen = false;

                }
                else
                {
                    fab.startAnimation(rotate_clockwise);

                    fab1.startAnimation(fab_open);
                    fab4.startAnimation(fab_open);
                    fab3.startAnimation(fab_open);
                    fab2.startAnimation(fab_open);

                    fab1.setClickable(true);
                    fab4.setClickable(true);
                    fab3.setClickable(true);
                    fab2.setClickable(true);

                    isOpen = true;
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
