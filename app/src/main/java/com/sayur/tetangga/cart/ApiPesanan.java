package com.sayur.tetangga.cart;

import com.sayur.tetangga.utils.ApiResponsed;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiPesanan {
    @POST("transaksi")
    Call<ApiResponsed> transaksi(@Body HashMap<String, Object> data);

    @GET("getPesanan/{id_user}")
    Call<Pesanan> getPesanan(@Path("id_user") String id_user);

    @GET("getDetailPesanan/{id_pesanan}")
    Call<DetailPesanan> getDetailPesanan(@Path("id_pesanan") String id_pesanan);

    @GET("upStatus/{id_pesanan}/{sts}")
    Call<ApiResponsed> upStatus(@Path("id_pesanan") String id_pesanan, @Path("sts") String sts);

    @POST("upBuktiTf/{id_pesanan}")
    Call<ApiResponsed> upBuktiTf(@Path("id_pesanan") String id_pesanan, @Body HashMap<String, Object> data);

    @GET("listBank")
    Call<Bank> getBank();

    @GET("getOngkir")
    Call<ApiResponsed> getOngkir();

    @GET("getWkt")
    Call<WaktuPengiriman> getWkt();
}
