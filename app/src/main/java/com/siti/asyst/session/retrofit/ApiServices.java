package com.siti.asyst.session.retrofit;

import com.siti.asyst.session.retrofit.request.LoginRequest;
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
    Call<TaskResponse> getTask();
    //Call<TaskResponse> getTask(@Body TaskRequest taskRequest);
    //Call<MovieResponse> getMovies(@Query("api_key") String api_key, @Query("year") String year, @Query("page") int page, @Query("sort_by") String sort_by);
    //Call<TaskResponse> getTask(@Query(""))

}
