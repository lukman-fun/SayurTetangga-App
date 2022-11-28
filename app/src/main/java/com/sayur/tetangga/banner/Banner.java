package com.sayur.tetangga.banner;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Banner {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("id")
        private String id;
        @SerializedName("gambar")
        private String gambar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }
    }
}
