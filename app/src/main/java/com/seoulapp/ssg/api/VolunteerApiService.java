package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public interface VolunteerApiService {
    @GET("/volunteer_list")
    Call<Model> getVolunteer_info();
}
