package com.sayur.tetangga.utils;

public class Constaint {
//    public static final String BASE_URL = "http://192.168.43.21/sayurtetangga/";
    public static final String BASE_URL = "http://sayurtetangga.my.id/";
    public static final String API = BASE_URL + "API/";

    public static final String[] getStatusPesanan(int status){
        switch (status){
            case 1: return new String[]{"Menunggu Pembayaran", "#6861ce"};
            case 2: return new String[]{"Menungu Konfirmasi Admin", "#48abf7"};
            case 3: return new String[]{"Pesanan diProses", "#1572e8"};
            case 4: return new String[]{"Pesanan diKirim", "#ffad46"};
            case 5: return new String[]{"Pesanan diTerima", "#31ce36"};
            case 0:
            default: return new String[]{"Pesanan diBatalkan", "#f25961"};
        }
    }
}
