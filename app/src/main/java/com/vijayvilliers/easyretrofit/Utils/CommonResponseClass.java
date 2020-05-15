package com.vijayvilliers.easyretrofit.Utils;

import android.util.Log;
import com.google.gson.JsonElement;
import com.vijayvilliers.easyretrofit.Interfaces.Api;
import com.vijayvilliers.easyretrofit.Interfaces.CommonResponse;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonResponseClass {

    CommonResponse commonResponse;
    private static final String TAG = "CommonResponseClass";
    public static Api apiService = ApiClient.getClient().create(Api.class);

    public CommonResponseClass( CommonResponse commonResponse) {

        this.commonResponse = commonResponse;


    }

    public void getCommonResponse(final String apicall, final String action) {

        Call<JsonElement> commonCall = apiService.getApiData(apicall);

        Log.d(TAG, "hit:" + apicall);

        commonCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                commonResponse.onResponseSuccess(apicall, action, true, response);
                Log.d(TAG, "hit response: " + response.body());

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

                commonResponse.onResponseSuccess(apicall, action, false, null);
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });


    }


 public void postCommonResponse(final String apicall, final String action, HashMap<String, String> dataMap) {

        Call<JsonElement> commonCall = apiService.postApiValues(apicall, dataMap);

        Log.d(TAG, "hit:" + apicall + "\n" + dataMap);

        commonCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                commonResponse.onResponseSuccess(apicall, action, true, response);
                Log.d(TAG, "hit response: " + response.body());

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

                commonResponse.onResponseSuccess(apicall, action, false, null);
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });


    }

    }
