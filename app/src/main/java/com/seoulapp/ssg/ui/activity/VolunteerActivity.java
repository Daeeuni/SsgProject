package com.seoulapp.ssg.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.VolunteerApiService;
import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.User;
import com.seoulapp.ssg.model.Volunteer;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.VolunteerPagerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class VolunteerActivity extends AppCompatActivity {

    private static final String TAG = VolunteerActivity.class.getSimpleName();
    private TextView join_textView;
    private Button join_botton;
    private ArrayAdapter<String> adapter;
    private ViewPager mPager;
    private VolunteerApiService v_service;
    private VolunteerPagerAdapter v_pageAdapter;

    private ArrayList<String> pics;
    private String[] v_title = {"제목", "날짜", "봉사시간", "활동장소", "집합장소", "모집인원",  "상세설명"};
    Volunteer volParcel;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        Intent intent = getIntent();
        volParcel = intent.getParcelableExtra("volunteerParcel");
        pics = new ArrayList<>();
        pics.add(volParcel.getPicture());
        v_pageAdapter = new VolunteerPagerAdapter(VolunteerActivity.this);

        join_textView = (TextView) findViewById(R.id.join_textView);
        join_botton = (Button) findViewById(R.id.join_button);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(v_pageAdapter);
        v_pageAdapter.additems(pics);

        join_textView.append(v_title[0] + " : " + volParcel.getVolunteerTitle() + "\n");
        join_textView.append(v_title[1] + " : " + volParcel.getSchedule() + "\n");
        join_textView.append(v_title[2] + " : " + volParcel.getTime() + "\n");
        join_textView.append(v_title[3] + " : " + volParcel.getSpot() + "\n");
        join_textView.append(v_title[4] + " : " + volParcel.getMeeting_location() + "\n");
        join_textView.append(v_title[5] + " : " + volParcel.getTotal_volunteer() + "/" + volParcel.getRecruitment() + "\n");
        join_textView.append(v_title[6] + " : " + volParcel.getDetail_info()+ "\n");

        join_botton.setOnClickListener(new View.OnClickListener() {
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
                            public void onClick(View v) {
                                int uid = 1;
                                v_service = ServiceGenerator.getInstance().createService(VolunteerApiService.class);
                                Call<Model> call = v_service.joinVolunteer(volParcel.getVolunteerId(), uid, edit_name.getText().toString(), edit_phone.getText().toString());
                                call.enqueue(new Callback<Model>() {
                                    @Override
                                    public void onResponse(Call<Model> call, Response<Model> response) {
                                        if(response.isSuccessful()) {
                                            if(response.body().getCode() == 200) {
                                                setBntJoin(join_botton);
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
                        });

                        btn_info_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog_info.dismiss();
                            }
                        });

                        dialog_info.show();
                    }
                });

                dialog_check.show();
            }
        });


    }

    public void setBntJoin(View v) {
            join_botton.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.btnChanged));
            join_botton.setText("참가신청 완료");
            join_botton.invalidate();
    }



    /*
    private void setCurrentInflateItem(int type){
        if(type==0){
            mPager.setCurrentItem(0);
        }else if(type==1){
            mPager.setCurrentItem(1);
        }else{
            mPager.setCurrentItem(2);
        }
    }
    */

}
