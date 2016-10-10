package com.seoulapp.ssg.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class Volunteer {

    @SerializedName("vname")
    private String volunteer_title;

    private String spot;

    private String schedule;

    private String time;

    private int recruitment;

    private int total_volunteer;

    private String picture;

    public String getVolunteer_title(){ return volunteer_title; }

    public String getSpot(){ return spot; }

    public String getSchedule() { return schedule; }

    public String getTime() { return  time; }

    public int getRecruitment() { return  recruitment; }

    public int getTotal_volunteer() { return total_volunteer; }

    public String getPicture() { return picture; }

}
