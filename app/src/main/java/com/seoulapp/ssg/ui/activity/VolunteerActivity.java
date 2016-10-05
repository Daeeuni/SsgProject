package com.seoulapp.ssg.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.*;
import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.network.ServiceGenerator;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class VolunteerActivity extends AppCompatActivity {

    //private ImageView join_imgView;
    private TextView join_textView;
    private Button join_botton;
    private ArrayAdapter<String> adapter;

    private ViewPager mPager;
    private SSGApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //join_imgView = (ImageView) findViewById(R.id.join_imgView);
        join_textView = (TextView) findViewById(R.id.join_textView);
        join_botton = (Button) findViewById(R.id.join_button);
        //join_imgView.setScaleType(ImageView.ScaleType.FIT_END); // ImageView 사이즈 조절

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));

        VolunteerApiService service = ServiceGenerator.getInstance().createService(VolunteerApiService.class);

        Call<Model> call = service.getVolunteer_info();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                    Toast.makeText(VolunteerActivity.this,"성공",Toast.LENGTH_LONG).show();
                    Model model = response.body();
                    join_textView.setText(model.getVolunteer_info().get(0).getVolunteer_title());
                }
                else{
                    int statusCode = response.code();
                    Log.e("My Error Tag", "응답코드:"+statusCode);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e("My Error Tag","서버 onFailure 에러내용:"+t.getMessage());
            }
        });

        // add button listener
        join_botton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog_check = new Dialog(VolunteerActivity.this);
                dialog_check.setContentView(R.layout.join_agreement);
                dialog_check.setTitle("주의사항");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog_check.findViewById(R.id.txt_dialog);
                text.setText(
                        "- 해당 봉사활동에 참가신청 시, 7일 전까지 취소가 가능합니다.\n" +
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
                        TextView text_name = (TextView) dialog_info.findViewById(R.id.txt_info_name);
                        TextView text_phone = (TextView) dialog_info.findViewById(R.id.txt_info_phone);
                        TextView text_email= (TextView) dialog_info.findViewById(R.id.txt_info_email);

                        Button btn_info_ok = (Button) dialog_info.findViewById(R.id.btn_join_OK);
                        Button btn_info_cancel = (Button) dialog_info.findViewById(R.id.btn_join_Cancel);
                        // if button is clicked, close the custom dialog

                        btn_info_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 네트워크로 정보 보내기
                                dialog_info.dismiss();
                                setBntJoin(v);
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

        /* listView Test
        lvTest = (ListView) findViewById(R.id.lv_gallery);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dias);
        lvTest.setAdapter(adapter);
        lvTest.setOnItemClickListener(this);
        */
    }

    // 참가신청 버튼변경 함수
    private void setBntJoin(View v){
        //네트워크로 확인 해야함
        join_botton.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorChanged));
        join_botton.setText("참가신청 완료");
        join_botton.invalidate();
    }

    // 어떻게 쓰일 수 있을지 확인해 볼 것
    private void setCurrentInflateItem(int type){
        if(type==0){
            mPager.setCurrentItem(0);
        }else if(type==1){
            mPager.setCurrentItem(1);
        }else{
            mPager.setCurrentItem(2);
        }
    }

    /* PagerAdapter*/
    private class PagerAdapterClass extends PagerAdapter {

        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c){
            super();
            mInflater = LayoutInflater.from(c);
        }

        // 어떻게 쓰일 수 있을지 확인해 볼 것
        @Override
        public int getCount() {
            return 3;
        }

        // 어떻게 쓰일 수 있을지 확인해 볼 것
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = null;
            if(position==0){
                v = mInflater.inflate(R.layout.inflate_one, null);
                v.findViewById(R.id.imgView01);
            }
            else if(position==1){
                v = mInflater.inflate(R.layout.inflate_two, null);
                v.findViewById(R.id.imgView02);

            }else{
                v = mInflater.inflate(R.layout.inflate_three, null);
                v.findViewById(R.id.imgView03);
            }

            ((ViewPager)container).addView(v, 0);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View)object);
        }
        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }
        @Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        @Override public Parcelable saveState() { return null; }
        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

}

}
