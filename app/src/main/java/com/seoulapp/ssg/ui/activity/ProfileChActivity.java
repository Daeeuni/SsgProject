package com.seoulapp.ssg.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.UserService;
import com.seoulapp.ssg.model.User;
import com.seoulapp.ssg.network.ServiceGenerator;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by win7-64 on 2016-10-24.
 */

public class ProfileChActivity extends BaseActivity {

    TextView tvUserName;
    ImageView ivProfile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ch);

        ivProfile = (ImageView) findViewById(R.id.iv_profile_ch);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);

        UserService service = ServiceGenerator.getInstance().createService(UserService.class);
        Call<User> call = service.getMyProfile(1);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    tvUserName.setText(response.body().getNickname());
                    Glide.with(ProfileChActivity.this)
                            .load(response.body().getProfile())
                            .bitmapTransform(new CropCircleTransformation(ProfileChActivity.this))
                            .placeholder(R.drawable.ic_profile_none)
                            .into(ivProfile);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}
