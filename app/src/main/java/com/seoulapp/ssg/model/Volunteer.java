package com.seoulapp.ssg.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dongyoon on 2016. 10. 3..
 */

public class Volunteer {
    @SerializedName("vname") public String title;
    @SerializedName("spot") public String locationName;
    public String time;
    @SerializedName("schedule") public String date;
    @SerializedName("picture") public String pictureUrl;

}
