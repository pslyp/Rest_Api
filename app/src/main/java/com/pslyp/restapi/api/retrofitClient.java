package com.pslyp.restapi.api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitClient {

    private static retrofitClient client;
    private static Retrofit retrofit;

    private String BASR_URL = "https://www.path.com";

    public retrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASR_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized retrofitClient getInstance() {
        if(client == null) {
            client = new retrofitClient();
        }
        return client;
    }

    public Service api() {
        return retrofit.create(Service.class);
    }

}
