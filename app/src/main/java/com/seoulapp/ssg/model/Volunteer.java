package com.seoulapp.ssg.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Boram Moon on 2016-10-05.
 */
public class Volunteer implements Parcelable {

    @SerializedName("vid")
    private int volunteerId;
    @SerializedName("vname")
    private String volunteerTitle;

    private String spot;

    private String schedule;

    private String time;

    private String meeting_location;

    private String detail_info;

    private int recruitment;

    private int total_volunteer;

    private String thumbnail;

    private String picture;

    public Volunteer() {
    }

    public Volunteer(Parcel source) {
        volunteerId = source.readInt();
        volunteerTitle = source.readString();
        spot = source.readString();
        schedule = source.readString();
        time = source.readString();
        meeting_location = source.readString();
        detail_info = source.readString();
        recruitment = source.readInt();
        total_volunteer = source.readInt();
        thumbnail = source.readString();
        picture = source.readString();
    }

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

    public String getMeeting_location() { return meeting_location; }

    public String getDetail_info() { return detail_info; }

    public int getRecruitment() {
        return recruitment;
    }

    public int getTotal_volunteer() {
        return total_volunteer;
    }

    public String getThumbnail() { return thumbnail; }

    public String getPicture() {
        return picture;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public void setVolunteerTitle(String volunteerTitle) {
        this.volunteerTitle = volunteerTitle;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMeeting_location(String meeting_location) { this.meeting_location = meeting_location; }

    public void setDetail_info(String detail_info) { this.detail_info = detail_info; }

    public void setRecruitment(int recruitment) {
        this.recruitment = recruitment;
    }

    public void setTotal_volunteer(int total_volunteer) { this.total_volunteer = total_volunteer; }

    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail;}

    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(volunteerId);
        dest.writeString(volunteerTitle);
        dest.writeString(spot);
        dest.writeString(schedule);
        dest.writeString(time);
        dest.writeString(meeting_location);
        dest.writeString(detail_info);
        dest.writeInt(recruitment);
        dest.writeInt(total_volunteer);
        dest.writeString(thumbnail);
        dest.writeString(picture);
    }

    public static Creator<Volunteer> CREATOR = new Creator<Volunteer>() {
        @Override
        public Volunteer createFromParcel(Parcel source) {
            return new Volunteer(source);
        }

        @Override
        public Volunteer[] newArray(int size) {
            return new Volunteer[size];
        }
    };
}