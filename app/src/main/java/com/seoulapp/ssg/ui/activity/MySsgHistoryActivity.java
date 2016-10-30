package com.seoulapp.ssg.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.SsgApiService;
import com.seoulapp.ssg.model.SsgModel;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.SsgHistoryRecyclerAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySsgHistoryActivity extends AppCompatActivity {
    private static final String TAG = MySsgHistoryActivity.class.getSimpleName();

    private SsgHistoryRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_ssg_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setTitle("");
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_ssghistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        mAdapter = new SsgHistoryRecyclerAdapter(this);
        recyclerView.setAdapter(mAdapter);
        SsgApiService service = ServiceGenerator.getInstance().createService(SsgApiService.class);
        Call<SsgModel> call = service.getMySsgHistory(1);
        call.enqueue(new Callback<SsgModel>() {
            @Override
            public void onResponse(Call<SsgModel> call, Response<SsgModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().code == 200) {
                        mAdapter.addItems(response.body().ssgs);
                    }
                }
            }

            @Override
            public void onFailure(Call<SsgModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){ finish(); }
        return super.onOptionsItemSelected(item);
    }
}
