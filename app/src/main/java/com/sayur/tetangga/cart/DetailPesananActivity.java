package com.sayur.tetangga.cart;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sayur.tetangga.R;
import com.sayur.tetangga.utils.ApiResponsed;
import com.sayur.tetangga.utils.Constaint;
import com.sayur.tetangga.utils.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPesananActivity extends AppCompatActivity implements View.OnClickListener {
    TextView kode, penerima, noTelp, alamat, pengiriman, ongkir, wkt, catatan, status, total;
    LinearLayout lnBatalBayar;
    Button btnBayar, btnBatal, btnTerima;
    RecyclerView rvItemPesanan;

    ApiPesanan apiPesanan;
    int totalHarga = 0;
    String idPesanan = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        apiPesanan = new Server().load().create(ApiPesanan.class);

        kode = findViewById(R.id.kodePesanan);
        penerima = findViewById(R.id.namaPenerima);
        noTelp = findViewById(R.id.noTelpPenerima);
        alamat = findViewById(R.id.alamatPenerima);
        pengiriman = findViewById(R.id.paymentPenerima);
        ongkir = findViewById(R.id.ongkirPenerima);
        wkt = findViewById(R.id.wktPengiriman);
        catatan = findViewById(R.id.catatanPenerima);
        status = findViewById(R.id.stsPesanan);
        total = findViewById(R.id.totalHarga);

        lnBatalBayar = findViewById(R.id.lnBatalBayar);
        btnBayar = findViewById(R.id.btnBayar);
        btnBayar.setOnClickListener(this);
        btnBatal = findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(this);
        btnTerima = findViewById(R.id.btnTerima);
        btnTerima.setOnClickListener(this);

        rvItemPesanan = findViewById(R.id.rvItemPesanan);
        rvItemPesanan.setLayoutManager(new LinearLayoutManager(this));
        rvItemPesanan.setHasFixedSize(true);

        idPesanan = getIntent().getStringExtra("id");
//        getDetailPesanan();
    }

    private void getDetailPesanan(){
        Call<DetailPesanan> detailPesananCall = apiPesanan.getDetailPesanan(idPesanan);
        detailPesananCall.enqueue(new Callback<DetailPesanan>() {
            @Override
            public void onResponse(Call<DetailPesanan> call, Response<DetailPesanan> response) {
                if(response.body().getDetail_pesanan().getStatus().equals("200")){
                    Pesanan.Data pesanan = response.body().getPesanan().getData();
                    Pesanan.Data.DataPengiriman dataPengiriman = pesanan.getDataPengiriman();

                    kode.setText("#" + pesanan.getKodeTransaksi());

                    penerima.setText(": " + dataPengiriman.getPenerima());
                    noTelp.setText(": " + dataPengiriman.getNoTelp());
                    alamat.setText(": " + dataPengiriman.getAlamat());
                    penerima.setText(": " + dataPengiriman.getPayment());
                    ongkir.setText(": Rp " + dataPengiriman.getOngkir());
                    totalHarga += Integer.parseInt(dataPengiriman.getOngkir());
                    wkt.setText(": " + dataPengiriman.getWaktu());
                    catatan.setText(": " + dataPengiriman.getCatatan());
                    String[] stsPesanan = Constaint.getStatusPesanan(Integer.parseInt(pesanan.getStatus()));
                    status.setText(stsPesanan[0]);
                    status.getBackground().setColorFilter(Color.parseColor(stsPesanan[1]), PorterDuff.Mode.ADD);

                    if(pesanan.getStatus().equals("1")){
                        lnBatalBayar.setVisibility(View.VISIBLE);
                    }else{
                        lnBatalBayar.setVisibility(View.GONE);
                    }

                    if(pesanan.getStatus().equals("4")){
                        btnTerima.setVisibility(View.VISIBLE);
                    }else{
                        btnTerima.setVisibility(View.GONE);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Data Pengiriman Kosong", Toast.LENGTH_SHORT).show();
                }

                if(response.body().getDetail_pesanan().getStatus().equals("200")){
                    List<DetailPesanan.Detail_Pesanan.Data> detailP = response.body().getDetail_pesanan().getData();
                    for(DetailPesanan.Detail_Pesanan.Data d : detailP){
                        totalHarga += (Integer.parseInt(d.getHarga()) * Integer.parseInt(d.getQty()));
                    }
                    rvItemPesanan.setAdapter(new DetailPesananAdapter(DetailPesananActivity.this, detailP));
                    total.setText(": Rp " + totalHarga);
                }else{
                    Toast.makeText(getApplicationContext(), "Barang Pesanan Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailPesanan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBatal:
                upStatus("0");
                getDetailPesanan();
                break;
            case R.id.btnBayar:
                Intent i = new Intent(getApplicationContext(), TfActivity.class);
                i.putExtra("id", idPesanan);
                startActivity(i);
                break;
            case R.id.btnTerima:
                upStatus("5");
                getDetailPesanan();
                break;
        }
    }

    public void upStatus(String sts){
        Call<ApiResponsed> up = apiPesanan.upStatus(idPesanan, sts);
        up.enqueue(new Callback<ApiResponsed>() {
            @Override
            public void onResponse(Call<ApiResponsed> call, Response<ApiResponsed> response) {
                if(response.body().getStatus().equals("200")){
                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    getDetailPesanan();
                }
            }

            @Override
            public void onFailure(Call<ApiResponsed> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDetailPesanan();
    }
}
