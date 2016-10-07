package com.seoulapp.ssg.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class Users {
    @SerializedName("uname")
    private String name;

    @SerializedName("uphone")
    private String phone_num;

    @SerializedName("apply")
    private int joinCheck;

    public void setName(String name) { this.name = name; }

    public void setPhone_num(String phone_num) { this.phone_num = phone_num; }

    public void setJoinCheck(int joinCheck) { this.joinCheck = joinCheck; }

    public int getJoinCheck() { return joinCheck; }
}
