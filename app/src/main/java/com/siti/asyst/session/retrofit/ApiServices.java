package com.siti.asyst.session.retrofit;

import com.siti.asyst.session.model.Task;
import com.siti.asyst.session.retrofit.request.LoginRequest;
import com.siti.asyst.session.retrofit.response.LoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("Login/getProfileInfo")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);
    //response                  //requesr

    @GET("Task/getAllTask")
    Call<ArrayList<Task>> getTask();

}
