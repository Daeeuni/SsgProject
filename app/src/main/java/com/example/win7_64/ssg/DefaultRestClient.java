package com.example.win7_64.ssg;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by win7-64 on 2016-09-10.
 */
public class

DefaultRestClient<T> {

    private T service;
    private String baseUrl = "http://52.78.115.250:9000"; // 서버 주소

    public T getClient(Class<? extends T> type){
        if(service == null) {
            OkHttpClient client = new OkHttpClient();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(type);
        }
        return service;
    }
}
