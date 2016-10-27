package com.seoulapp.ssg.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by win7-64 on 2016-10-24.
 */

public class ProfileChActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ch);

        Intent i = getIntent();
        String profilePath = i.getStringExtra("profile_picture");
        String profileName = i.getStringExtra("profile_name");

        ImageView ivProfile = (ImageView)findViewById(R.id.iv_profile_ch);
        Glide.with(this).load(profilePath).bitmapTransform(new CropCircleTransformation(this)).into(ivProfile);
        TextView tvProfile = (TextView)findViewById(R.id.tv_user_name);
        tvProfile.setText(profileName);


    }
}
