package com.seoulapp.ssg.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dongyoon on 2016. 10. 19..
 */

public class Ssg {
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

    public String picture;

    public User user;

    public Like like;

    public Declare declare;

    @SerializedName("declare_cnt")
    public byte isDeclare;

    @SerializedName("like_cnt")
    public byte isLike;



    public class Like {
        public int like_cnt;
    }

    public class Declare {
        public int declare_cnt;
    }

    public boolean isDeclare() {
        return declare != null && declare.declare_cnt == 1 || isDeclare == 1;
    }

    public boolean isLike() {
        return like != null && like.like_cnt == 1 || isLike == 1;
    }

}

