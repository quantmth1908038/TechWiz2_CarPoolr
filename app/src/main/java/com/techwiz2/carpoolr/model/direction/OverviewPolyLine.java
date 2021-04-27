package com.techwiz2.carpoolr.model.direction;

import com.google.gson.annotations.SerializedName;

public class OverviewPolyLine {
    @SerializedName("points")
    public String points;

    public String getPoints() {
        return points;
    }
}
