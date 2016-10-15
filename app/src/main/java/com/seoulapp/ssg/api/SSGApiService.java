package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Boram Moon on 2016-10-11.
 */

public interface SsgApiService {
    /*@GET("ssg_cnt")
    Call<Model> getSsgCount();*/

    @GET("maindata")
    Call<Model> getMainViewData();
}
