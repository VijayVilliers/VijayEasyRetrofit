package com.vijayvilliers.easyretrofit.Interfaces;

import retrofit2.Response;

public interface CommonResponse {


    //triggers when data is received and we need to show it in list
    void onResponseSuccess(String apiCall, String action, Boolean status, Response response);


}
