package com.seoulapp.ssg.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class User {

    @SerializedName("uname")
    private String name;

    @SerializedName("uphone")
    private String phone_num;

    @SerializedName("apply")
    private int joinCheck;

    @SerializedName("uid")
    private int user_num;

    public User() {
    }

    public void setUser_num(int user_num) { this.user_num = user_num; }

    public void setJoinCheck(int joinCheck) { this.joinCheck = joinCheck; }

    public void setName(String name) { this.name = name; }

    public void setPhone_num(String phone_num) { this.phone_num = phone_num; }

    public int getUser_num() { return user_num; }

    public int getJoinCheck() { return joinCheck; }

    public String getName() { return name; }

    public String getPhone_num() { return phone_num; }

}
