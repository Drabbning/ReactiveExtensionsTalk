package com.atino.kevin.rxexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class BaseActivity extends AppCompatActivity {

    protected ImageView cats;

    protected URL url = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);

        cats = findViewById(R.id.cats_imageview);
        cats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCats();
            }
        });

        try {
            url = new URL(getString(R.string.api_cat_url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        requestCats();
    }

    abstract void requestCats();
}
