package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.SsgModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Boram Moon on 2016-10-11.
 */

public interface SsgApiService {

    @GET("maindata")
    Call<Model> getMainViewData();

    @GET("gallery")
    Call<SsgModel> getSsgGallery(@Query("page") int page);

    @FormUrlEncoded
    @POST("my_ssg")
    Call<SsgModel> getMySsgHistory(@Field("uid") int user_id);
}
