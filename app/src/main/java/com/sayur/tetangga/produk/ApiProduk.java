package com.sayur.tetangga.produk;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiProduk {
    @GET("getHome")
    Call<Home> getHome();

    @GET("detailKtg/{ktg}")
    Call<Produk> getProduk(@Path("ktg") String ktg);
}
