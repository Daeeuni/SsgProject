package com.seoulapp.ssg.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.VolunteerApiService;
import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.Ssac;
import com.seoulapp.ssg.model.User;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.VolunteerPagerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class VolunteerActivity extends BaseActivity {

    private static final String TAG = VolunteerActivity.class.getSimpleName();
    private TextView tv_volunteer_title;
    private TextView tv_volunteer_title_content;
    private TextView tv_volunteer_date;
    private TextView tv_volunteer_date_content;
    private TextView tv_volunteer_schedule;
    private TextView tv_volunteer_schedule_content;
    private TextView tv_volunteer_place;
    private TextView tv_volunteer_place_content;
    private TextView tv_volunteer_meetingpoint;
    private TextView tv_volunteer_meetingpoint_content;
    private TextView tv_volunteer_total;
    private TextView tv_volunteer_total_content;
    private TextView tv_volunteer_detail;
    private TextView tv_volunteer_detail_content;
    private Button btn_volunteer_join;
    private ArrayAdapter<String> adapter;
    private ViewPager mPager;
    private VolunteerApiService v_service;
    private VolunteerPagerAdapter v_pageAdapter;

    private String[] v_title = {"제목", "날짜", "봉사시간", "활동장소", "집합장소", "모집인원", "상세설명"};
    Ssac volParcel;
    User user;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setTitle("");
        }

        Intent intent = getIntent();
        volParcel = intent.getParcelableExtra("volunteerParcel");

        v_pageAdapter = new VolunteerPagerAdapter(VolunteerActivity.this);

        tv_volunteer_title = (TextView) findViewById(R.id.tv_volunteer_title);
        tv_volunteer_title_content = (TextView) findViewById(R.id.tv_volunteer_title_content);
        tv_volunteer_date = (TextView) findViewById(R.id.tv_volunteer_date);
        tv_volunteer_date_content = (TextView) findViewById(R.id.tv_volunteer_date_content);
        tv_volunteer_schedule = (TextView) findViewById(R.id.tv_volunteer_schedule);
        tv_volunteer_schedule_content = (TextView) findViewById(R.id.tv_volunteer_schedule_content);
        tv_volunteer_place = (TextView) findViewById(R.id.tv_volunteer_place);
        tv_volunteer_place_content = (TextView) findViewById(R.id.tv_volunteer_place_content);
        tv_volunteer_meetingpoint = (TextView) findViewById(R.id.tv_volunteer_meetingpoint);
        tv_volunteer_meetingpoint_content = (TextView) findViewById(R.id.tv_volunteer_meetingpoint_content);
        tv_volunteer_total = (TextView) findViewById(R.id.tv_volunteer_total);
        tv_volunteer_total_content = (TextView) findViewById(R.id.tv_volunteer_total_content);
        tv_volunteer_detail = (TextView) findViewById(R.id.tv_volunteer_detail);
        tv_volunteer_detail_content = (TextView) findViewById(R.id.tv_volunteer_detail_content);

        btn_volunteer_join = (Button) findViewById(R.id.btn_volunteer_join);
        mPager = (ViewPager) findViewById(R.id.pager_volunteer);
        mPager.setAdapter(v_pageAdapter);

        tv_volunteer_title.append(v_title[0]);
        tv_volunteer_date.append(v_title[1]);
        tv_volunteer_schedule.append(v_title[2]);
        tv_volunteer_place.append(v_title[3]);
        tv_volunteer_meetingpoint.append(v_title[4]);
        tv_volunteer_total.append(v_title[5]);
        tv_volunteer_detail.append(v_title[6]);

        tv_volunteer_title_content.append(volParcel.getVolunteerTitle());
        tv_volunteer_date_content.append(volParcel.getSchedule());
        tv_volunteer_schedule_content.append(volParcel.getTime());
        tv_volunteer_place_content.append(volParcel.getSpot());
        tv_volunteer_meetingpoint_content.append(volParcel.getMeeting_location());
        tv_volunteer_total_content.append(volParcel.getTotal_volunteer() + " / " + volParcel.getRecruitment());
        tv_volunteer_detail_content.append(volParcel.getDetail_info());

        btn_volunteer_join.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final Dialog dialog_check = new Dialog(VolunteerActivity.this);
                dialog_check.setContentView(R.layout.join_agreement);
                dialog_check.setTitle("주의사항");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog_check.findViewById(R.id.txt_dialog);
                text.setText("- 해당 봉사활동에 참가신청 시, 7일 전까지 취소가 가능합니다.\n" +
                        "- 무단으로 3회 이상 불참하게 되면 봉사활동 참여가 제한 될 수 있습니다.\n" +
                        "\n" +
                        "참가 신청하시겠습니까?");

                Button dialogButton = (Button) dialog_check.findViewById(R.id.btn_dialog_OK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // custom dialog
                        dialog_check.dismiss();
                        final Dialog dialog_info = new Dialog(VolunteerActivity.this);
                        View view = getLayoutInflater().inflate(R.layout.join_info, null);
                        final EditText edit_name = (EditText) view.findViewById(R.id.edit_name);
                        final EditText edit_phone = (EditText) view.findViewById(R.id.edit_phone);
                        final EditText edit_email = (EditText) view.findViewById(R.id.edit_email);

                        Button btn_info_ok = (Button) view.findViewById(R.id.btn_join_OK);
                        Button btn_info_cancel = (Button) view.findViewById(R.id.btn_join_Cancel);

                        dialog_info.setContentView(view);
                        dialog_info.setTitle("봉사활동 신청 양식");

                        // set the custom dialog components - text, image and button

                        btn_info_ok.setOnClickListener(new View.OnClickListener() {


                                @Override
                                public void onClick (View v){
                                    int uid = 1;
                                    v_service = ServiceGenerator.getInstance().createService(VolunteerApiService.class);
                                    Call<Model> call = v_service.joinVolunteer(volParcel.getVolunteerId(), uid, edit_name.getText().toString(), edit_phone.getText().toString());
                                    call.enqueue(new Callback<Model>() {
                                        @Override
                                        public void onResponse(Call<Model> call, Response<Model> response) {
                                            if (response.isSuccessful()) {
                                                if (response.body().getCode() == 200) {
                                                    setBntJoin(btn_volunteer_join);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Model> call, Throwable t) {
                                            Log.d("joinOK", t.getMessage());
                                        }
                                    });

                                    dialog_info.dismiss();
                                }
                            }

                            );

                            btn_info_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick (View v){
                                    dialog_info.dismiss();
                                }
                            }

                            );

                            dialog_info.show();
                        }
                    }

                    );

                    dialog_check.show();
                }
            }

            );

        VolunteerApiService service = ServiceGenerator.getInstance().createService(VolunteerApiService.class);
        Call<Ssac> call = service.getSsacPictures(volParcel.getVolunteerId(), 1);

        call.enqueue(new Callback<Ssac>() {
            @Override
            public void onResponse(Call<Ssac> call, Response<Ssac> response) {
                if (response.isSuccessful()) {
                    v_pageAdapter.additems(response.body().getPictures());
                }
            }

            @Override
            public void onFailure(Call<Ssac> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setBntJoin(View v) {
        btn_volunteer_join.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.btnChanged));
        btn_volunteer_join.setText("참가신청 완료");
        btn_volunteer_join.invalidate();
    }

}
