package com.vijayvilliers.easyretrofit.Interfaces;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    //pass the URL path after base url in callAPI
    @GET("{callAPI}")
    Call<JsonElement> getApiData(@Path("callAPI") String callApi);

    @FormUrlEncoded
    @POST("{callAPI}")
    Call<JsonElement> postApiValues(@Path("callAPI") String callApi, @FieldMap Map<String,String> map);

}
