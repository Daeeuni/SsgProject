package com.seoulapp.ssg.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.seoulapp.ssg.R;

/**
 * Created by win7-64 on 2016-10-22.
 */

public class SsgSettingActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_setting);

        Button profileChButton = (Button) findViewById(R.id.profile_change);
        profileChButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SsgSettingActivity.this, ProfileChActivity.class);
                startActivity(i);
            }

        });

    }

    }









  /*
        public boolean onSettingItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.profile_change:
                    Intent i = new Intent(SsgSettingActivity.this, ProfileChActivity.class);
                    Log.d(TAG, "onSettingItemSelected: "+ "Sccess");
                    startActivity(i);
                    break;



            }

            return false;
        }*/