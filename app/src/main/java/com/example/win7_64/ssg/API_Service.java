package com.example.win7_64.ssg;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by win7-64 on 2016-09-10.
 */
public interface API_Service {
    @GET("ssg_count")
    Call<Model> getSsg_count();
}
