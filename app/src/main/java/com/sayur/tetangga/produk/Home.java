package com.sayur.tetangga.produk;

import com.google.gson.annotations.SerializedName;
import com.sayur.tetangga.banner.Banner;

public class Home {
    @SerializedName("banner")
    private Banner banner;
    @SerializedName("kategori")
    private Ktg ktg;
    @SerializedName("produk")
    private Produk produk;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Ktg getKtg() {
        return ktg;
    }

    public void setKtg(Ktg ktg) {
        this.ktg = ktg;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }
}
