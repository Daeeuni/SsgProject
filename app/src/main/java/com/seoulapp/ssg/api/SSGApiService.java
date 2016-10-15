package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by win7-64 on 2016-09-10.
 */
public interface SsgApiService {
    @GET("ssg_cnt")
    Call<Model> getSsgCount();

    @GET("maindata")
    Call<Model> getMainViewData();

    @GET("gallery")
    Call<Model> getSsgGallery(@Query("page") int page);
}
