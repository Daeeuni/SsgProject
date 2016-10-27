package com.seoulapp.ssg.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.seoulapp.ssg.R;

/**
 * Created by win7-64 on 2016-10-22.
 */

public class SsgSettingActivity extends BaseActivity {

    private static final String TAG = SsgSettingActivity.class.getSimpleName();

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


