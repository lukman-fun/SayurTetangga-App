package com.sayur.tetangga.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WaktuPengiriman {
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
        @SerializedName("name")
        private String name;
        @SerializedName("start")
        private String start;
        @SerializedName("end")
        private String end;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }
    }
}
