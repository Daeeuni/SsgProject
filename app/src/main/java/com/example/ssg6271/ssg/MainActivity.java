package com.example.ssg6271.ssg;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.*;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    private ImageButton btn_report;
    private Button btn_sak_tip;
    private Button btn_sak_join;
    private TextView tv_ssg_count;  // 동적으로 통계 받아오는 부분 (쓱)
    private TextView tv_sak_count;  // 동적으로 통계 받아오는 부분 (싹)

    //listview
    private String[] menu;
    private ArrayAdapter<String> adapter;
    private ListView menu_view;

    private Toolbar toolbar;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_report = (ImageButton) findViewById(R.id.btn_report);
        btn_sak_tip = (Button) findViewById(R.id.btn_sak_tip);
        btn_sak_join = (Button) findViewById(R.id.btn_sak_join);
        tv_ssg_count = (TextView) findViewById(R.id.tv_ssg_count);
        tv_sak_count = (TextView) findViewById(R.id.tv_sak_count);

        //listview
        menu = getResources().getStringArray(R.array.menu);
        // simple_list_item_1은 textview를 담고 있는 xml 형태이다.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        menu_view = (ListView) findViewById(R.id.menu_view);
        menu_view.setAdapter(adapter);
        menu_view.setOnItemClickListener(new DrawerItemClickListener());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.addDrawerListener(dtToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //api 22 에

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "제보버튼", Toast.LENGTH_SHORT).show();
            }
        });

        btn_sak_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "싹팁", Toast.LENGTH_SHORT).show();
            }
        });

        btn_sak_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "싹 참여하기", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    //배열의 index 순서대로 Event를 정의할 수 있음.
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            switch (position) {
                case 0:
                    Toast.makeText(MainActivity.this, "Username", Toast.LENGTH_SHORT).show();
                    break;

                case 1:
                    Toast.makeText(MainActivity.this, "나의 쓱 제보", Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    Toast.makeText(MainActivity.this, "나의 싹 내역", Toast.LENGTH_SHORT).show();
                    break;

                case 3:
                    Toast.makeText(MainActivity.this, "환경설정", Toast.LENGTH_SHORT).show();
                    break;
            }
            dlDrawer.closeDrawer(menu_view);

        }


    }


}
