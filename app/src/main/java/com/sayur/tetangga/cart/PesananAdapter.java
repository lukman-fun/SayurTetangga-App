package com.sayur.tetangga.cart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayur.tetangga.R;
import com.sayur.tetangga.utils.Constaint;

import java.util.List;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.PesananHolder> {
    Context c;
    List<Pesanan.Data> data;
    PesananClick pesananClick;

    public PesananAdapter(Context c, PesananClick pesananClick) {
        this.c = c;
        this.pesananClick = pesananClick;
    }

    public void Update(List<Pesanan.Data> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PesananHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PesananHolder(LayoutInflater.from(c).inflate(R.layout.item_pesanan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PesananHolder holder, int position) {
        Pesanan.Data pesanan = data.get(position);
        holder.kode.setText("#" + pesanan.getKodeTransaksi());
        holder.desk.setText("Pengiriman : " + pesanan.getDataPengiriman().getPayment() + "\n" + "Ongkir : Rp " + pesanan.getDataPengiriman().getOngkir());
        String[] stsPesanan = Constaint.getStatusPesanan(Integer.parseInt(pesanan.getStatus()));
        holder.status.setText(stsPesanan[0]);
        holder.status.getBackground().setColorFilter(Color.parseColor(stsPesanan[1]), PorterDuff.Mode.ADD);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesananClick.onDetailPesanan(pesanan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data!=null ? data.size() : 0;
    }

    public class PesananHolder extends RecyclerView.ViewHolder {
        TextView kode, desk, status;
        public PesananHolder(@NonNull View v) {
            super(v);
            kode = v.findViewById(R.id.kodePesanan);
            desk = v.findViewById(R.id.deskPesanan);
            status = v.findViewById(R.id.stsPesanan);
        }
    }

    public interface PesananClick{
        void onDetailPesanan(Pesanan.Data data);
    }
}
