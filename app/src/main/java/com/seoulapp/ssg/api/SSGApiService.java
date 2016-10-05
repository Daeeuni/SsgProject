package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by win7-64 on 2016-09-10.
 */
public interface SSGApiService {
    @GET("ssg_cnt")
    Call<Model> getSsgCount();
}
