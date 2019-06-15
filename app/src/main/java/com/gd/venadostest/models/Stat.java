package com.gd.venadostest.models;

import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("position")
    public int position;

    @SerializedName("image")
    public String image;

    @SerializedName("team")
    public String team;

    @SerializedName("games")
    public int games;

    @SerializedName("score_diff")
    public int score_diff;

    @SerializedName("points")
    public int points;


}
