package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;

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
    Call<Model> getSsgGallery(@Query("page") int page);
}
