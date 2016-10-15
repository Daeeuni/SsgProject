package com.seoulapp.ssg.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by win7-64 on 2016-09-10.
 */
public class Model {
    private int code;

    private String msg;

/*    @SerializedName("ssg_count")
    private int ssgCount;*/

    @SerializedName("tip_list")
    public ArrayList<SsacTip> ssacTips;

    @SerializedName("volunteer_list")
    public ArrayList<Volunteer> volunteers;
    public User user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

/*  public int getSsgCount() { return ssgCount; }

    public void setSsgCount(int ssgCount) {
        this.ssgCount = ssgCount;
    }*/

}

