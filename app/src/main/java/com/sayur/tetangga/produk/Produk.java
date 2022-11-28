package com.sayur.tetangga.produk;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Produk {
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
        @SerializedName("nama")
        private String nama;
        @SerializedName("harga")
        private String harga;
        @SerializedName("per")
        private String per;
        @SerializedName("stok")
        private String stok;
        @SerializedName("gambar")
        private String gambar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }

        public String getPer() {
            return per;
        }

        public void setPer(String per) {
            this.per = per;
        }

        public String getStok() {
            return stok;
        }

        public void setStok(String stok) {
            this.stok = stok;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }
    }
}
