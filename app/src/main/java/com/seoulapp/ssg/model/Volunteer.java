package com.seoulapp.ssg.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class Volunteer {

    @SerializedName("vid")
    private int volunteerId;

    @SerializedName("vname")
    private String volunteerTitle;

    private String spot;

    private String schedule;

    private String time;

    private int recruitment;

    private int total_volunteer;

    private String picture;

    public int getVolunteerId() {
        return volunteerId;
    }

    public String getVolunteerTitle() {
        return volunteerTitle;
    }

    public String getSpot() {
        return spot;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getTime() {
        return time;
    }

    public int getRecruitment() {
        return recruitment;
    }

    public int getTotal_volunteer() {
        return total_volunteer;
    }

    public String getPicture() {
        return picture;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public void setVolunteerTitle(String volunteerTitle) {
        this.volunteerTitle = volunteerTitle;
    }

    public void setTotal_volunteer(int total_volunteer) {
        this.total_volunteer = total_volunteer;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setRecruitment(int recruitment) {
        this.recruitment = recruitment;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
