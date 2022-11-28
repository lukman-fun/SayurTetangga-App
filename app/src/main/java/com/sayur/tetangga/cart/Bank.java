package com.sayur.tetangga.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bank {
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
        @SerializedName("nama_bank")
        private String namaBank;
        @SerializedName("norek")
        private String norek;

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

        public String getNamaBank() {
            return namaBank;
        }

        public void setNamaBank(String namaBank) {
            this.namaBank = namaBank;
        }

        public String getNorek() {
            return norek;
        }

        public void setNorek(String norek) {
            this.norek = norek;
        }
    }
}
