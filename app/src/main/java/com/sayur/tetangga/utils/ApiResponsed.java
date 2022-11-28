package com.sayur.tetangga.utils;

import com.google.gson.annotations.SerializedName;

public class ApiResponsed {
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
