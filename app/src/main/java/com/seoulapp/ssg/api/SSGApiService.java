package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.SsgModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Boram Moon on 2016-10-11.
 */

public interface SsgApiService {

    @GET("maindata")
    Call<Model> getMainViewData();

    @GET("gallery")
    Call<SsgModel> getSsgGallery(@Query("page") int page);
}
