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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.VolunteerApiService;
import com.seoulapp.ssg.model.User;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.VolunteerPagerAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class VolunteerActivity extends AppCompatActivity {
    private static final String TAG = VolunteerActivity.class.getSimpleName();
    //private ImageView join_imgView;
    private TextView join_textView;
    private Button join_botton;
    private ListView lvTest;
    private ArrayAdapter<String> adapter;
    private ViewPager mPager;
    private VolunteerApiService v_service;

    private String[] v_title = {"장소", "일시", "시간", "집합장소", "모집인원", "상세설명"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        Intent intent = getIntent();

        join_botton = (Button) findViewById(R.id.join_button);
        lvTest = (ListView) findViewById(R.id.lv_content);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new VolunteerPagerAdapter(getApplicationContext()));
        v_service = ServiceGenerator.getInstance().createService(VolunteerApiService.class);

        join_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // JoinDialog dialog = new JoinDialog(VolunteerActivity.this);
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
                        dialog_info.setContentView(R.layout.join_info);
                        dialog_info.setTitle("봉사활동 신청 양식");

                        // set the custom dialog components - text, image and button
                        final TextView text_name = (TextView) dialog_info.findViewById(R.id.txt_info_name);
                        final TextView text_phone = (TextView) dialog_info.findViewById(R.id.txt_info_phone);
                        TextView text_email = (TextView) dialog_info.findViewById(R.id.txt_info_email);

                        Button btn_info_ok = (Button) dialog_info.findViewById(R.id.btn_join_OK);
                        Button btn_info_cancel = (Button) dialog_info.findViewById(R.id.btn_join_Cancel);

                        btn_info_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                User v_user = new User();
                                v_user.setName(text_name.getText().toString());
                                v_user.setPhone_num(text_phone.getText().toString());

                                Call<User> call = v_service.joinVolunteer(v_user);
                                call.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        Log.d(TAG, "onResponse: " + response.message());
                                        setBntJoin(join_botton);

                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Toast.makeText(VolunteerActivity.this, "서버전송 실패", Toast.LENGTH_LONG).show();
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

    // 참가신청 버튼변경 함수
    public void setBntJoin(View v) {
        //네트워크로 확인 해야함
        join_botton.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorChanged));
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
