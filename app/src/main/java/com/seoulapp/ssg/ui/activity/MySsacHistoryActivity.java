package com.seoulapp.ssg.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.model.Volunteer;
import com.seoulapp.ssg.ui.adapter.SsacHistoryRecyclerAdapter;

import java.util.ArrayList;

public class MySsacHistoryActivity extends BaseActivity {
    private SsacHistoryRecyclerAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ssac_history);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new SsacHistoryRecyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        ArrayList<Volunteer> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Volunteer item = new Volunteer();
            item.setMeeting_location("경복궁");
            item.setSchedule("11월 13일");
            item.setTime("13:00 ~ 18:00");

            items.add(item);
        }

        mAdapter.addItems(items);
    }
}
