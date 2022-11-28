package com.sayur.tetangga.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sayur.tetangga.R;
import com.sayur.tetangga.db.Cart;

import java.util.List;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.KeranjangHolder> {
    Context c;
    List<Cart> data;
    KeranjangClick keranjangClick;

    public KeranjangAdapter(Context c, List<Cart> data, KeranjangClick keranjangClick) {
        this.c = c;
        this.data = data;
        this.keranjangClick = keranjangClick;
    }

    @NonNull
    @Override
    public KeranjangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KeranjangHolder(LayoutInflater.from(c).inflate(R.layout.item_keranjang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KeranjangHolder holder, int position) {
        Cart cart = data.get(position);
        Glide.with(c).load(cart.getGambar()).into(holder.img);
        holder.nama.setText(cart.getNama());
        holder.harga.setText("Rp " + cart.getHarga() + "/" + cart.getPer());
        holder.qty.setText(cart.getQty());

        holder.min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(cart.getQty()) > 1){
                    cart.setQty((Integer.parseInt(cart.getQty())-1) + "");
                    keranjangClick.onMin(cart, position);
                }
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.setQty((Integer.parseInt(cart.getQty())+1) + "");
                keranjangClick.onPlus(cart, position);
            }
        });

        holder.rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keranjangClick.onRmv(cart, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class KeranjangHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView nama, harga, qty;
        ImageView min, plus, rmv;
        public KeranjangHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.imgKeranjang);
            nama = v.findViewById(R.id.namaKeranjang);
            harga = v.findViewById(R.id.hargaKeranjang);
            qty = v.findViewById(R.id.qty);
            min = v.findViewById(R.id.min);
            plus = v.findViewById(R.id.plus);
            rmv = v.findViewById(R.id.rmv);
        }
    }

    public interface KeranjangClick{
        void onMin(Cart cart, int position);
        void onPlus(Cart cart, int position);
        void onRmv(Cart cart, int position);
    }
}
