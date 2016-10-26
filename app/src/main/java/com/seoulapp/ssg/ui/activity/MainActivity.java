package com.seoulapp.ssg.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.SsgApiService;
import com.seoulapp.ssg.listener.RecyclerItemClickListener;
import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.Volunteer;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.SsacTipPagerAdapter;
import com.seoulapp.ssg.ui.adapter.VolunteerRecyclerAdapter;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager ssacPager;
    private CircleIndicator circleIndicator;
    private RecyclerView rcv_volunteer;

    private SsacTipPagerAdapter tipPagerAdapter;
    private VolunteerRecyclerAdapter volunteerRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ssacPager = (ViewPager) findViewById(R.id.vp_ssac_tip);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        rcv_volunteer = (RecyclerView) findViewById(R.id.rcv_volunteer);
        rcv_volunteer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        volunteerRecyclerAdapter = new VolunteerRecyclerAdapter(this);
        rcv_volunteer.setAdapter(volunteerRecyclerAdapter);

        tipPagerAdapter = new SsacTipPagerAdapter(this);
        ssacPager.setAdapter(tipPagerAdapter);

        Button btnSsgReport = (Button) findViewById(R.id.btn_ssg_report);
        btnSsgReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(i);
            }
        });
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

            drawerLayout.addDrawerListener(actionBarDrawerToggle);

            actionBarDrawerToggle.syncState();
        }

        rcv_volunteer.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Volunteer volunteer = volunteerRecyclerAdapter.getItem(position);
                                Intent intent = new Intent(MainActivity.this, VolunteerActivity.class);
                                intent.putExtra("volunteerParcel", volunteer);
                                startActivity(intent);
                            }
                        }
                )
        );

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);


        getMainViewData();

    }

    private void getMainViewData() {
        SsgApiService service = ServiceGenerator.getInstance().createService(SsgApiService.class);
        Call<Model> call = service.getMainViewData();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                if (response.isSuccessful()) {
                    volunteerRecyclerAdapter.addItems(response.body().volunteers);
                    tipPagerAdapter.addItems(response.body().ssacTips);
                    circleIndicator.setViewPager(ssacPager);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_ssg_gallery:
                Intent i = new Intent(MainActivity.this, SsgGalleryActivity.class);
                startActivity(i);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}