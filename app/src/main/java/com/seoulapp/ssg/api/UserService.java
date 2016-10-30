package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by daeuni on 2016-10-15.
 */

public interface UserService {
    @FormUrlEncoded
    @POST("Login") // 회원가입
    Call<Model> signUp(@Field("social_id") String socialId, @Field("email") String email,
                       @Field("nickname") String nickname, @Field("profile") String profile,
                       @Field("join_type") String join_type, @Field("access_token") String token);



    @FormUrlEncoded
    @POST("myProfile")
    Call<User> getMyProfile(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("send_authMail")
    Call<User> sendEmail(@Field("uid") int uid, @Field("email") String email);

    @FormUrlEncoded
    @POST("profile_modify")
    Call<User> changeEmail(@Field("uid") int uid, @Field("email") String email, @Field("auth_key") String auth_key);
}
