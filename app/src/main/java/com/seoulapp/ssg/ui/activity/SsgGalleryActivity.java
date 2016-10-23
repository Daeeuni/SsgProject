package com.seoulapp.ssg.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.SsgApiService;
import com.seoulapp.ssg.model.SsgModel;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.SsgGalleryRecylcerAdapter;
import com.seoulapp.ssg.util.DividerItemDecoration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SsgGalleryActivity extends AppCompatActivity {
    private static final String TAG = SsgGalleryActivity.class.getSimpleName();
    boolean isLast = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssg_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final SsgGalleryRecylcerAdapter mAdapter = new SsgGalleryRecylcerAdapter(this);
        recyclerView.setAdapter(mAdapter);

        SsgApiService service = ServiceGenerator.getInstance().createService(SsgApiService.class);

        Call<SsgModel> call = service.getSsgGallery(1);

        call.enqueue(new Callback<SsgModel>() {
            @Override
            public void onResponse(Call<SsgModel> call, Response<SsgModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().code == 200) {
                        mAdapter.addItems(response.body().ssgs);
                        Log.d(TAG, "onResponse: " + response.body().ssgs.size());
                        if (response.body().last) {
                            isLast = response.body().last;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SsgModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }



}
