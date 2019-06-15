package com.gd.venadostest.models;

import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("local")
    public Boolean local;

    @SerializedName("opponent")
    public String opponent;

    @SerializedName("opponent_image")
    public String opponent_image;

    @SerializedName("datetime")
    public String datetime;

    @SerializedName("league")
    public String league;

    @SerializedName("image")
    public String image;

    @SerializedName("home_score")
    public int home_score;

    @SerializedName("away_score")
    public int away_score;

    public Game(Boolean local, String opponent, String opponent_image, String datetime, String league, String image, int home_score, int away_score) {
        this.local = local;
        this.opponent = opponent;
        this.opponent_image = opponent_image;
        this.datetime = datetime;
        this.league = league;
        this.image = image;
        this.home_score = home_score;
        this.away_score = away_score;
    }
}