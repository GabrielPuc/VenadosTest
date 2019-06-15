package com.gd.venadostest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("games")
    public List<Game> games;

    @SerializedName("statistics")
    public List<Stat> stats;

}