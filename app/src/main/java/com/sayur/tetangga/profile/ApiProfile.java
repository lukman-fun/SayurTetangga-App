package com.sayur.tetangga.profile;

import com.sayur.tetangga.utils.ApiResponsed;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiProfile {
    @GET("profile/{id}")
    Call<Users> profile(@Path("id") String id);

    @POST("upProfile/{id}")
    Call<ApiResponsed> upProfile(@Path("id") String id, @Body HashMap<String, Object> data);
}
