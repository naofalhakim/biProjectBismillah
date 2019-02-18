package com.example.user.projectbidanku;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class FullScreenActivity extends AppCompatActivity {

    private ImageView imageView;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        Intent i = getIntent();
        String path = i.getStringExtra("path");

        imageView = (ImageView) findViewById(R.id.ivFullScreen);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);

        imageView.setImageURI(Uri.fromFile(new File(path)));

//        Glide.with(this)
//                .load(path)
//                .override(this.getWindow().getAttributes().width, this.getWindow().getAttributes().height)
//                .animate(Animation.REVERSE)
//                .into(imageView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
