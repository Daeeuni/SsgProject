package com.seoulapp.ssg.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dongyoon on 2016. 10. 19..
 */

public class Ssg implements Parcelable{
    @SerializedName("gid")
    public int ssgId;
    @SerializedName("pname")
    public String spotName;
    @SerializedName("detail_location")
    public String spotDetail;

    public String comment;
    @SerializedName("total_like")
    public int likeCount;
    @SerializedName("total_declare")
    public int declareCount;
    @SerializedName("create_date")
    public String date;

    public double lat;
    public double lng;
    public String picture;
    public User user;

    @SerializedName("like_cnt")
    public byte wantRemove;

    public boolean wantRemove() {
        return wantRemove != 0;
    }

    public void setWantRemove(boolean want) {
        if (want){
            wantRemove = 1;
        } else{
            wantRemove = 0;
        }
    }

    public Ssg(){
    }

    public Ssg(Parcel source){
        ssgId = source.readInt();
        picture = source.readString();
        spotName = source.readString();
        spotDetail = source.readString();
        comment = source.readString();
        lat = source.readDouble();
        lng = source.readDouble();
        date = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ssgId);
        dest.writeString(picture);
        dest.writeString(spotName);
        dest.writeString(spotDetail);
        dest.writeString(comment);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(date);
    }

    public static Creator<Ssg> CREATOR = new Creator<Ssg>() {
        @Override
        public Ssg createFromParcel(Parcel source) {
            return new Ssg(source);
        }

        @Override
        public Ssg[] newArray(int size) {
            return new Ssg[size];
        }
    };
}
/*
"gallery_list": [
        {
        "gid": 9,
        "picture": "http://52.78.115.250:9000/1/gyeongbok.jpg",
        "pname": "경복궁",
        "detail_location": "작은 기둥",
        "comment": "경복궁 낙서",
        "total_like": 0,
        "total_declare": 0,
        "create_date": "2016-09-06",
        "user": {
        "nickname": "Jay Park"
        }
        }
        */
