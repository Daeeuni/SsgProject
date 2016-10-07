package com.seoulapp.ssg.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.model.SsacTip;
import com.seoulapp.ssg.ui.adapter.SsacTipPagerAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ViewPager ssacPager;
    private LinearLayout ll_ssac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ssacPager = (ViewPager) findViewById(R.id.vp_ssac_tip);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }
            };

            //Setting the actionbarToggle to drawer layout
            drawerLayout.addDrawerListener(actionBarDrawerToggle);

            //calling sync state is necessay or else your hamburger icon wont show up
            actionBarDrawerToggle.syncState();
        }
        ArrayList<SsacTip> items = new ArrayList<>();
        for (int i = 0; i<4; i++){
            SsacTip tip = new SsacTip();
            tip.subject = "Hot Pick";
            tip.title = "벽 낙서 페인트로 지우기"+ i;
            tip.subTitle = "페인팅 제대로 하는 법"+ i;
            items.add(tip);
        }

        SsacTipPagerAdapter ssacTipPagerAdapter = new SsacTipPagerAdapter(this, items);
        ssacPager.setAdapter(ssacTipPagerAdapter);

        ll_ssac = (LinearLayout) findViewById(R.id.ll_ssac_join);
        //ll_ssac.removeAllViews();

        for(int i=0; i<2; i++){
            Button btn_ssac = new AppCompatButton(this);
            btn_ssac.setText("경복궁");
            btn_ssac.setHeight(20);
            btn_ssac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), VolunteerActivity.class); // 이부분 괜찮은가?
                    startActivity(intent);
                }
            });
            ll_ssac.addView(btn_ssac);
        }



    }
}
