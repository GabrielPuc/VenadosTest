package com.gd.venadostest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    @SerializedName("forwards")
    public List<Players> forwards;

    @SerializedName("centers")
    public List<Players> centers;

    @SerializedName("defenses")
    public List<Players> defenses;

    @SerializedName("goalkeepers")
    public List<Players> goalkeepers;

    @SerializedName("coaches")
    public List<Players> coaches;


}
