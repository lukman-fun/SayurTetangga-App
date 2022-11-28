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
import com.sayur.tetangga.utils.Constaint;

import java.util.List;

public class DetailPesananAdapter extends RecyclerView.Adapter<DetailPesananAdapter.DetailPesananHolder> {
    Context c;
    List<DetailPesanan.Detail_Pesanan.Data> data;

    public DetailPesananAdapter(Context c, List<DetailPesanan.Detail_Pesanan.Data> data) {
        this.c = c;
        this.data = data;
    }

    @NonNull
    @Override
    public DetailPesananHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailPesananHolder(LayoutInflater.from(c).inflate(R.layout.item_detail_pesanan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPesananHolder holder, int position) {
        DetailPesanan.Detail_Pesanan.Data detailPesanan = data.get(position);
        Glide.with(c).load(Constaint.BASE_URL + detailPesanan.getGambar()).into(holder.img);
        holder.nama.setText(detailPesanan.getNama());
        holder.harga.setText("Rp " + detailPesanan.getHarga() + "/" + detailPesanan.getPer());
        holder.qty.setText("X " + detailPesanan.getQty());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DetailPesananHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView nama, harga, qty;
        public DetailPesananHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.imgItemPesanan);
            nama = v.findViewById(R.id.namaItemPesanan);
            harga = v.findViewById(R.id.hargaItemPesanan);
            qty = v.findViewById(R.id.qtyItemPesanan);
        }
    }

}
