package com.sayur.tetangga.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sayur.tetangga.R;
import com.sayur.tetangga.auth.Sesi;
import com.sayur.tetangga.utils.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananFrament extends Fragment implements PesananAdapter.PesananClick {
    RecyclerView rvPesanan;
    PesananAdapter pesananAdapter;
    ApiPesanan apiPesanan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pesanan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        apiPesanan = new Server().load().create(ApiPesanan.class);
        rvPesanan = v.findViewById(R.id.rvPesanan);
        rvPesanan.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPesanan.setHasFixedSize(true);
        pesananAdapter = new PesananAdapter(getActivity(), this);
        rvPesanan.setAdapter(pesananAdapter);
        getPesanan();
    }

    private void getPesanan(){
        Call<Pesanan> pesanan = apiPesanan.getPesanan(new Sesi(getActivity()).get().getId());
        pesanan.enqueue(new Callback<Pesanan>() {
            @Override
            public void onResponse(Call<Pesanan> call, Response<Pesanan> response) {
                if(response.body().getStatus().equals("200")){
                    pesananAdapter.Update(response.body().getData());
                }else{
                    Toast.makeText(getActivity(), "Pesanan Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pesanan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetailPesanan(Pesanan.Data data) {
//        Toast.makeText(getActivity(), data.getId(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), DetailPesananActivity.class);
        i.putExtra("id", data.getId());
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPesanan();
    }
}
