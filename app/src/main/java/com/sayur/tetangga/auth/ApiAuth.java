package com.sayur.tetangga.auth;

import com.sayur.tetangga.profile.Users;
import com.sayur.tetangga.utils.ApiResponsed;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAuth {
    @POST("login")
    Call<Users> login(@Body HashMap<String, Object> data);

    @POST("daftar")
    Call<ApiResponsed> daftar(@Body HashMap<String, Object> data);
}
