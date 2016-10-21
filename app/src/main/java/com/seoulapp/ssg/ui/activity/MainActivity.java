package com.seoulapp.ssg.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.SsgApiService;
import com.seoulapp.ssg.listener.RecyclerItemClickListener;
import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.Volunteer;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.SsacTipPagerAdapter;
import com.seoulapp.ssg.ui.adapter.VolunteerRecyclerAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ViewPager ssacPager;
    private RecyclerView rcv_volunteer;

    private SsacTipPagerAdapter tipPagerAdapter;
    private VolunteerRecyclerAdapter volunteerRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





       /* String name = intent.getStringExtra("입력한 이름");
        if(name.equals(""))
            Toast.makeText(this, "입력한 아이디가 없습니다!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "입력한 아이디는 " + id + "입니다.", Toast.LENGTH_SHORT).show();*/


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ssacPager = (ViewPager) findViewById(R.id.vp_ssac_tip);
        rcv_volunteer = (RecyclerView) findViewById(R.id.rcv_volunteer);
        rcv_volunteer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        volunteerRecyclerAdapter = new VolunteerRecyclerAdapter(this);
        rcv_volunteer.setAdapter(volunteerRecyclerAdapter);

        tipPagerAdapter = new SsacTipPagerAdapter(this);
        ssacPager.setAdapter(tipPagerAdapter);
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

        getMainViewData();

    }

    private void getMainViewData() {
        SsgApiService service = ServiceGenerator.getInstance().createService(SsgApiService.class);
        Call<Model> call = service.getMainViewData();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d(TAG, "onResponse: " + response.message());

                if (response.isSuccessful()) {
                    volunteerRecyclerAdapter.addItems(response.body().volunteers);
                    tipPagerAdapter.addItems(response.body().ssacTips);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}