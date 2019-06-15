package com.gd.venadostest.utils;

import com.gd.venadostest.Constants;
import com.gd.venadostest.network.ApiInterface;
import com.gd.venadostest.network.RetrofitClient;

public class RetrofitUtils {

    private RetrofitUtils() {}

    //public static final String BASE_URL = "http://148.209.1.31/AppSisbiuady/";

    public static ApiInterface getRestClient() {

        return RetrofitClient.getClient(Constants.DOMAIN).create(ApiInterface.class);
    }
}
