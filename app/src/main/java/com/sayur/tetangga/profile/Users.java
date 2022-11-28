package com.sayur.tetangga.profile;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("id")
        private String id;
        @SerializedName("nama_lengkap")
        private String nama_lengkap;
        @SerializedName("no_telp")
        private String no_telp;
        @SerializedName("alamat")
        private String alamat;
        @SerializedName("password")
        private String password;
        @SerializedName("foto")
        private String foto;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama_lengkap() {
            return nama_lengkap;
        }

        public void setNama_lengkap(String nama_lengkap) {
            this.nama_lengkap = nama_lengkap;
        }

        public String getNo_telp() {
            return no_telp;
        }

        public void setNo_telp(String no_telp) {
            this.no_telp = no_telp;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }
    }
}
