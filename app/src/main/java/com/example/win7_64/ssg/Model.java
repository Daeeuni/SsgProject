package com.example.win7_64.ssg;

import com.google.gson.annotations.SerializedName;

/**
 * Created by win7-64 on 2016-09-10.
 */
public class Model {
    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private int msg;

    @SerializedName("ssg_count")
    private String ssg_count;

    public String getSsg_count(){

        return  ssg_count;
    }
}

