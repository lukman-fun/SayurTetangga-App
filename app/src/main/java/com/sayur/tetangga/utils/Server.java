package com.sayur.tetangga.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    protected static Retrofit retrofit = null;

    public Retrofit load(){
        if(retrofit == null){
            return new Retrofit.Builder()
                    .baseUrl(Constaint.API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return null;
    }
}
