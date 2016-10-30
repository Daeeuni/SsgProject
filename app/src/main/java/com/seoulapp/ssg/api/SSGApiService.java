package com.seoulapp.ssg.api;

import com.seoulapp.ssg.model.Model;
import com.seoulapp.ssg.model.Ssg;
import com.seoulapp.ssg.model.SsgModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Boram Moon on 2016-10-11.
 */

public interface SsgApiService {

    @GET("mainData")
    Call<Model> getMainViewData();

    @GET("gallery")
    Call<SsgModel> getSsgGallery(@Query("page") int page, @Query("uid") int uid);

    @FormUrlEncoded
    @POST("my_ssg")
    Call<SsgModel> getMySsgHistory(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("like_toggle")
    Call<Ssg> like(@Field("gid") int gid, @Field("uid") int uid);

    @FormUrlEncoded
    @POST("declare_toggle")
    Call<Ssg> declare(@Field("gid") int gid, @Field("uid") int uid);

    @FormUrlEncoded
    @POST("myssg_delete")
    Call<Model> deleteSsg(@Field("gid") int gid, @Field("uid") int uid);

    @Multipart
    @POST("report")
    Call<Model> upload_ssg(@Part MultipartBody.Part body,
                           @Part("uid") RequestBody uid,
                           @Part("comment") RequestBody comment,
                           @Part("detail_location") RequestBody detailLocation,
                           @Part("pname") RequestBody pname,
                           @Part("lat") RequestBody lat,
                           @Part("lng") RequestBody lng);

}
