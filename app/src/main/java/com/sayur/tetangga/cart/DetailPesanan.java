package com.sayur.tetangga.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailPesanan {
    @SerializedName("pesanan")
    private Pesanan pesanan;
    @SerializedName("detail_pesanan")
    private Detail_Pesanan detail_pesanan;

    public Pesanan getPesanan() {
        return pesanan;
    }

    public void setPesanan(Pesanan pesanan) {
        this.pesanan = pesanan;
    }

    public Detail_Pesanan getDetail_pesanan() {
        return detail_pesanan;
    }

    public void setDetail_pesanan(Detail_Pesanan detail_pesanan) {
        this.detail_pesanan = detail_pesanan;
    }

    public class Pesanan{
        @SerializedName("status")
        private String status;
        @SerializedName("data")
        private com.sayur.tetangga.cart.Pesanan.Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public com.sayur.tetangga.cart.Pesanan.Data getData() {
            return data;
        }

        public void setData(com.sayur.tetangga.cart.Pesanan.Data data) {
            this.data = data;
        }
    }

    public class Detail_Pesanan{
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
            @SerializedName("id_transaksi")
            private String idTransaksi;
            @SerializedName("id_produk")
            private String idProduk;
            @SerializedName("qty")
            private String qty;
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

            public String getIdTransaksi() {
                return idTransaksi;
            }

            public void setIdTransaksi(String idTransaksi) {
                this.idTransaksi = idTransaksi;
            }

            public String getIdProduk() {
                return idProduk;
            }

            public void setIdProduk(String idProduk) {
                this.idProduk = idProduk;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
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
}
