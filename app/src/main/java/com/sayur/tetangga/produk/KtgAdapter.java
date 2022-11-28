package com.sayur.tetangga.produk;

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

public class KtgAdapter extends RecyclerView.Adapter<KtgAdapter.KtgHolder> {
    Context c;
    List<Ktg.Data> data;
    KtgListener ktgListener;

    public KtgAdapter(Context c, KtgListener ktgListener) {
        this.c = c;
        this.ktgListener = ktgListener;
    }

    public void Update(List<Ktg.Data> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KtgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KtgHolder(LayoutInflater.from(c).inflate(R.layout.item_ktg, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KtgHolder holder, int position) {
        Ktg.Data ktg = data.get(position);
        Glide.with(c).load(Constaint.BASE_URL + ktg.getIcon()).into(holder.img);
        holder.txt.setText(ktg.getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ktgListener.onKtgClick(ktg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class KtgHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        public KtgHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.imgKtg);
            txt = v.findViewById(R.id.textKtg);
        }
    }

    public interface KtgListener{
        void onKtgClick(Ktg.Data data);
    }
}
