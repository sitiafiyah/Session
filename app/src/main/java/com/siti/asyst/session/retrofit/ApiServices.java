package com.siti.asyst.session.retrofit;

import com.siti.asyst.session.retrofit.request.LoginRequest;
import com.siti.asyst.session.retrofit.request.TaskRequest;
import com.siti.asyst.session.retrofit.response.LoginResponse;
import com.siti.asyst.session.retrofit.response.TaskResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("Login/getProfileInfo")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);
    //response                  //requesr

    @GET("Task/getAllTask")
    Call<TaskResponse> getTask(@Body TaskRequest taskRequest);

}
