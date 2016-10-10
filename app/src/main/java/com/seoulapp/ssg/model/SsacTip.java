package com.seoulapp.ssg.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dongyoon on 2016. 10. 1..
 */
public class SsacTip {
    public String title;
    @SerializedName("subtitle")
    public String subTitle;
    public String category;
    public String thumbnail;
}
