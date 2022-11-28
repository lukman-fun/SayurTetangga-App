package com.sayur.tetangga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sayur.tetangga.auth.LoginActivity;
import com.sayur.tetangga.auth.Sesi;
import com.sayur.tetangga.banner.BannerAdapter;
import com.sayur.tetangga.cart.CartActivity;
import com.sayur.tetangga.db.AppDatabase;
import com.sayur.tetangga.db.Cart;
import com.sayur.tetangga.produk.ApiProduk;
import com.sayur.tetangga.banner.Banner;
import com.sayur.tetangga.produk.Home;
import com.sayur.tetangga.produk.Ktg;
import com.sayur.tetangga.produk.KtgAdapter;
import com.sayur.tetangga.produk.Produk;
import com.sayur.tetangga.produk.ProdukActivity;
import com.sayur.tetangga.produk.ProdukAdapter;
import com.sayur.tetangga.profile.ProfileActivity;
import com.sayur.tetangga.utils.Constaint;
import com.sayur.tetangga.utils.Server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements KtgAdapter.KtgListener {
    ViewPager vpBanner;
    BannerAdapter bannerAdapter;
    RecyclerView rvKtg;
    KtgAdapter ktgAdapter;
    RecyclerView rvProduk;
    ProdukAdapter produkAdapter;
    ApiProduk api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = new Server().load().create(ApiProduk.class);
        vpBanner = findViewById(R.id.banner);
        rvKtg = findViewById(R.id.ktg);
        rvKtg.setLayoutManager(new GridLayoutManager(this, 5));
        rvKtg.setHasFixedSize(true);
        ktgAdapter = new KtgAdapter(this, this);
        rvKtg.setAdapter(ktgAdapter);
        rvProduk = findViewById(R.id.produk);
        rvProduk.setLayoutManager(new GridLayoutManager(this, 2));
        rvProduk.setHasFixedSize(true);
        produkAdapter = new ProdukAdapter(this);
        rvProduk.setAdapter(produkAdapter);
        getHome();
//        kadaluarsa();
    }

    private void getHome(){
        Call<Home> getHome = api.getHome();
        getHome.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Banner banner = response.body().getBanner();
                if(banner.getStatus().equals("200")){
                    bannerAdapter = new BannerAdapter(MainActivity.this, banner.getData());
                    vpBanner.setAdapter(bannerAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Banner Kosong", Toast.LENGTH_SHORT).show();
                }

                Produk produk = response.body().getProduk();
                if(produk.getStatus().equals("200")){
                    produkAdapter.Update(produk.getData());
                }else{
                    Toast.makeText(getApplicationContext(), "Produk Kosong", Toast.LENGTH_SHORT).show();
                }

                Ktg ktg = response.body().getKtg();
                if(ktg.getStatus().equals("200")){
                    ktgAdapter.Update(ktg.getData());
                }else{
                    Toast.makeText(getApplicationContext(), "Kategori Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                return true;
            case R.id.logout:
//                startActivity(new Intent(MainActivity.this, CartActivity.class));
                AlertDialog.Builder alertLogout = new AlertDialog.Builder(this);
                alertLogout.setMessage("Apakah anda yakin ingin logout dari akun ini?");
                alertLogout.setCancelable(false);
                alertLogout.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Sesi(getApplicationContext()).rmv();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                });
                alertLogout.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertLogout.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHome();
    }

    @Override
    public void onKtgClick(Ktg.Data data) {
        Intent i = new Intent(this, ProdukActivity.class);
        i.putExtra("id", data.getId());
        startActivity(i);
    }

    private void kadaluarsa(){
        try {
            String dateExp = "2022-02-30";
            Calendar calendar = Calendar.getInstance();
            String dateNow = String.format("%04d-%02d-%02d",
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH)+1,
                    calendar.get(Calendar.DAY_OF_MONTH));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long now = simpleDateFormat.parse(dateNow).getTime();
            long exp = simpleDateFormat.parse(dateExp).getTime();

            if(now >= exp){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("APlikasi anda sudah kadaluarsa, segera hubungi developernya");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                });
                builder.show();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}