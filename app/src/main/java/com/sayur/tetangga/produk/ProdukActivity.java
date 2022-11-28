package com.sayur.tetangga.produk;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sayur.tetangga.R;
import com.sayur.tetangga.utils.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukActivity extends AppCompatActivity {
    RecyclerView rvProduk;
    ProdukAdapter produkAdapter;
    ApiProduk apiProduk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);
        rvProduk = findViewById(R.id.produk);
        rvProduk.setLayoutManager(new GridLayoutManager(this, 2));
        rvProduk.setHasFixedSize(true);
        produkAdapter = new ProdukAdapter(this);
        rvProduk.setAdapter(produkAdapter);
        apiProduk = new Server().load().create(ApiProduk.class);
        apiProduk.getProduk(getIntent().getStringExtra("id").toLowerCase()).enqueue(new Callback<Produk>() {
            @Override
            public void onResponse(Call<Produk> call, Response<Produk> response) {
                if(response.body().getStatus().equals("200")){
                    produkAdapter.Update(response.body().getData());
                }else{
                    Toast.makeText(getApplicationContext(), "Produk Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produk> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
