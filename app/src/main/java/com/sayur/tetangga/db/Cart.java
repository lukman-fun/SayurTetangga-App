package com.sayur.tetangga.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = AppDatabase.TB_CART)
public class Cart {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_produk")
    @SerializedName("id_produk")
    private int idProduk;
    @ColumnInfo(name = "nama")
    @SerializedName("nama")
    private String nama;
    @ColumnInfo(name = "harga")
    @SerializedName("harga")
    private String harga;
    @ColumnInfo(name = "per")
    @SerializedName("per")
    private String per;
    @ColumnInfo(name = "gambar")
    @SerializedName("gambar")
    private String gambar;
    @ColumnInfo(name = "qty")
    @SerializedName("qty")
    private String qty;

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
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

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
