package com.gd.venadostest.models;

import com.google.gson.annotations.SerializedName;

public class Players {

    @SerializedName("name")
    public String name;

    @SerializedName("first_surname")
    public String first_surname;

    @SerializedName("second_surname")
    public String second_surname;

    @SerializedName("birthday")
    public String birthday;

    @SerializedName("birth_place")
    public String birth_place;

    @SerializedName("weight")
    public Double weight;

    @SerializedName("height")
    public Double height;

    @SerializedName("role")
    public String role;

    @SerializedName("position")
    public String position;

    @SerializedName("number")
    public int number;

    @SerializedName("position_short")
    public String position_short;

    @SerializedName("last_team")
    public String last_team;

    @SerializedName("role_short")
    public String role_short;

    @SerializedName("image")
    public String image;

}