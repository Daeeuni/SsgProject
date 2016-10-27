package com.seoulapp.ssg.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.seoulapp.ssg.R;

public class SsgHistoryActivity extends AppCompatActivity {

    private ListView lv_ssg_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
//      myinfoParcel = intent.getParcelableExtra("myinfoParcel");

        lv_ssg_view = (ListView) findViewById(R.id.lv_ssg_history);


        setContentView(R.layout.activity_ssg_history);
    }
}
