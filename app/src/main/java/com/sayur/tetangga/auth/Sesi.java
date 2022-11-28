package com.sayur.tetangga.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sayur.tetangga.profile.Users;

public class Sesi {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Sesi(Context c) {
        this.sharedPreferences = c.getSharedPreferences("sesi_login", c.MODE_PRIVATE);
    }

    public void set(Users.Data data){
        editor = sharedPreferences.edit();
        editor.putString("data", new Gson().toJson(data).toString());
        editor.commit();
    }

    public Users.Data get(){
        return new Gson().fromJson(sharedPreferences.getString("data", null), Users.Data.class);
    }

    public boolean valid(){
        return sharedPreferences.getString("data", null) != null ? true : false;
    }

    public void rmv(){
        editor = sharedPreferences.edit();
        editor.remove("data");
        editor.commit();
    }
}
