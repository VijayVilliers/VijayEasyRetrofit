package com.vijayvilliers.easyretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vijayvilliers.easyretrofit.Interfaces.CommonResponse;
import com.vijayvilliers.easyretrofit.Models.EmployeeData;
import com.vijayvilliers.easyretrofit.Utils.CommonResponseClass;
import com.vijayvilliers.easyretrofit.Utils.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CommonResponse {

    private Context context=MainActivity.this;
    private static final String TAG = "MainActivity";
    CommonResponseClass easyRetrofitCall=new CommonResponseClass(this);
    HashMap<String,String> dataMap=new HashMap<>();
    List<EmployeeData> employeeDataList=new ArrayList<>(); //save the output respose in this list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       // dataMap is for POST data, here it is empty
        //apicall is your end path
        //action is useful for call multiple api in same activity
        easyRetrofitCall.getCommonResponse("employees","Employee Detail");
        //by using this way u can pass many request all response are comes in response success
    }

    @Override
    public void onResponseSuccess(String apiCall, String action, Boolean status, Response response) {

        //here u get the response for all your api calls
        // 1st check the status whether the api call is success or fail
        // here the parameter action helps to differntiate the api call using if condition (ex:
        // if(action.equals("something"){

        // }))

if(status) {

    if (action.equals("Employee Detail")) {

        if (null!=response.body() && response.body().toString().length() != 0) { //convert json response to string and check it not empty


            String JSONOutput = response.body().toString();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(JSONOutput);
                JSON json = new JSON(jsonObject); //


                if (json.key("status").stringValue().equals("success")) {

                    if (!jsonObject.isNull("data")) {
                        JSONArray jsonArray = json.getJsonObject().getJSONArray("data");
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<EmployeeData>>() {
                        }.getType();
                        employeeDataList = gson.fromJson(jsonArray.toString(), listType);
                        Log.d(TAG, "onResponseSuccess: " + jsonArray.toString());

                        toast("Success",context);

                        //now you can pass the employeeDataList to adapter and show it recycle view
                    }
                } else {

                    toast(json.key("data").stringValue(), context);
                }

            } catch (JSONException e) {

                toast("Something went wrong", context);
            }

        } else {

            toast("No Data found", context);
        }

    }


    /*
    //get another response
    if(action.equals("action")){


    }*/

}

    }

    public void toast(String s, Context context) {

        Toast.makeText(context, "" + s, Toast.LENGTH_SHORT).show();
    }
}
