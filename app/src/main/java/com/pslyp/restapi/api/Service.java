package com.pslyp.restapi.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface Service {

    @GET
    Call<ResponseBody> get(@Url String url);

    @POST
    Call<ResponseBody> post(@Url String url);

    @PUT
    Call<ResponseBody> put(@Url String url);

    @DELETE
    Call<ResponseBody> delete(@Url String url);

}
