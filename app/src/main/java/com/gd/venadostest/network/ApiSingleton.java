package com.gd.venadostest.network;

import com.gd.venadostest.BuildConfig;
import com.gd.venadostest.Constants;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiSingleton {

    private static ApiInterface sTravelGuideInterface;
    private static final int TIMEOUT = 30; // In secpnds
    private static final String BASE_URL = Constants.DOMAIN; // In secpnds

    private ApiSingleton() {
        /* Do nothing */
    }

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public synchronized static final ApiInterface getApiInterfaceInstance() {
        if (sTravelGuideInterface == null) {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);

            final RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.DOMAIN)
                    .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .setClient(new OkClient(client)).build();
            sTravelGuideInterface = restAdapter.create(ApiInterface.class);
        }
        return sTravelGuideInterface;
    }

}