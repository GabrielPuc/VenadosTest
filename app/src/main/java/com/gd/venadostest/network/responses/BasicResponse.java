package com.gd.venadostest.network.responses;

import com.gd.venadostest.models.Data;
import com.gd.venadostest.models.Game;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasicResponse {

    @SerializedName("success")
    public Boolean success;

    @SerializedName("data")
    public Data data;

    @SerializedName("code")
    public int code;

}