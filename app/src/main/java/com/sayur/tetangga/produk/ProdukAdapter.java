package com.sayur.tetangga.produk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.sayur.tetangga.R;
import com.sayur.tetangga.db.AppDatabase;
import com.sayur.tetangga.db.Cart;
import com.sayur.tetangga.utils.Constaint;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukHolder> {
    Context c;
    List<Produk.Data> data;
    AppDatabase db;

    public ProdukAdapter(Context c) {
        this.c = c;
        this.db = db = Room.databaseBuilder(c, AppDatabase.class, AppDatabase.TB_CART).allowMainThreadQueries().build();
    }

    public void Update(List<Produk.Data> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProdukHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProdukHolder(LayoutInflater.from(c).inflate(R.layout.item_produk, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukHolder holder, int position) {
        Produk.Data produk = data.get(position);
        Glide.with(c).load(Constaint.BASE_URL + produk.getGambar()).into(holder.img);
        holder.title.setText(produk.getNama());
        holder.price.setText("Rp " + produk.getHarga() + "/" + produk.getPer());
        holder.stok.setText("Stok : " + produk.getStok());
        if(db.dao().getCartById(produk.getId()) != null || Integer.parseInt(produk.getStok()) == 0){
            holder.btn.setVisibility(View.INVISIBLE);
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                cart.setIdProduk(Integer.parseInt(produk.getId()));
                cart.setNama(produk.getNama());
                cart.setHarga(produk.getHarga());
                cart.setPer(produk.getPer());
                cart.setGambar(Constaint.BASE_URL + produk.getGambar());
                cart.setQty("1");

                if(db.dao().addCart(cart) > 0){
                    holder.btn.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(c, "Tambah ke keranjang gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ProdukHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title, price, stok;
        Button btn;
        public ProdukHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.imgProduk);
            title = v.findViewById(R.id.nmProduk);
            price = v.findViewById(R.id.hrgProduk);
            stok = v.findViewById(R.id.stokProduk);
            btn = v.findViewById(R.id.btnProduk);
        }
    }

}
