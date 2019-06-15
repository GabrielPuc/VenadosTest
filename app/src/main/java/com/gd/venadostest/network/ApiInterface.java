package com.gd.venadostest.network;

import com.gd.venadostest.network.responses.BasicResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.Call;


public interface ApiInterface {

    @Headers("Accept:application/json")
    @GET("/api/games")
    Call<BasicResponse> getGames();

    @Headers("Accept:application/json")
    @GET("/api/statistics")
    Call<BasicResponse> getStats();

    @Headers("Accept:application/json")
    @GET("/api/players")
    Call<BasicResponse> getPlayers();

}