package com.sayur.tetangga.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.sayur.tetangga.R;
import com.sayur.tetangga.auth.Sesi;
import com.sayur.tetangga.db.AppDatabase;
import com.sayur.tetangga.db.Cart;
import com.sayur.tetangga.utils.ApiResponsed;
import com.sayur.tetangga.utils.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangFragment extends Fragment implements KeranjangAdapter.KeranjangClick, View.OnClickListener {
    AppDatabase db;
    RecyclerView rvItem;
    KeranjangAdapter keranjangAdapter;

    TextInputLayout nama, noTelp, alamat, catatan;
    Spinner payment, wkt;
    TextView ongkir;
    String rpOngkir = "0";

    TextView totals;
    Button checkout;

    ApiPesanan apiPesanan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_keranjang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        db = Room.databaseBuilder(getActivity(), AppDatabase.class, AppDatabase.TB_CART).allowMainThreadQueries().build();
        apiPesanan = new Server().load().create(ApiPesanan.class);
        rvItem = v.findViewById(R.id.rvItemProduk);
        rvItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvItem.setHasFixedSize(true);
        keranjangAdapter = new KeranjangAdapter(getActivity(), db.dao().getCartAll(), this);
        rvItem.setAdapter(keranjangAdapter);

        nama = v.findViewById(R.id.namaPenerima);
        noTelp = v.findViewById(R.id.noTelpPenerima);
        alamat = v.findViewById(R.id.alamatPenerima);
        catatan = v.findViewById(R.id.catatanPenerima);
        payment = v.findViewById(R.id.paymentPenerima);
        wkt = v.findViewById(R.id.wktPengiriman);
        ongkir = v.findViewById(R.id.ongkirPenerima);

        totals = v.findViewById(R.id.totalKeranjang);
        checkout = v.findViewById(R.id.btnPesan);
        checkout.setOnClickListener(this);

        total();

//        if(payment.getSelectedItemPosition() == 0){
//            ongkir.setText("Ongkir : Rp 3000");
//        }else{
//            ongkir.setText("Ongkir : Rp 0");
//        }

        getOngkir();
        getWkt();
    }

    private void getOngkir(){
        apiPesanan.getOngkir().enqueue(new Callback<ApiResponsed>() {
            @Override
            public void onResponse(Call<ApiResponsed> call, Response<ApiResponsed> response) {
                rpOngkir = response.body().getData();
                ongkir.setText("Ongkir : Rp " + rpOngkir);
                payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                ongkir.setText("Ongkir : Rp " + rpOngkir);
                                break;
                            case 1:
                                ongkir.setText("Ongkir : Rp 0");
                                break;
                        }
                        total();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                total();
            }

            @Override
            public void onFailure(Call<ApiResponsed> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getWkt(){
        apiPesanan.getWkt().enqueue(new Callback<WaktuPengiriman>() {
            @Override
            public void onResponse(Call<WaktuPengiriman> call, Response<WaktuPengiriman> response) {
                if(response.body().getStatus().equals("200")){
                    String[] dataWkt = new String[response.body().getData().size()];
                    for(int i = 0; i < response.body().getData().size(); i++){
                        WaktuPengiriman.Data dtWkt = response.body().getData().get(i);
                        dataWkt[i] = dtWkt.getName() + "  " + dtWkt.getStart() + " - " + dtWkt.getEnd();
                    }
                    ArrayAdapter<String> wktAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, dataWkt);
                    wktAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    wkt.setAdapter(wktAdapter);
                }else{
                    Toast.makeText(getActivity(), "Waktu Pengiriman belum tersedia", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WaktuPengiriman> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMin(Cart cart, int position) {
//        Toast.makeText(getActivity(), new Gson().toJson(cart).toString(), Toast.LENGTH_SHORT).show();
        db.dao().upCart(cart);
        keranjangAdapter.notifyDataSetChanged();
        total();
    }

    @Override
    public void onPlus(Cart cart, int position) {
//        Toast.makeText(getActivity(), new Gson().toJson(cart).toString(), Toast.LENGTH_SHORT).show();
        db.dao().upCart(cart);
        keranjangAdapter.notifyDataSetChanged();
        total();
    }

    @Override
    public void onRmv(Cart cart, int position) {
        AlertDialog.Builder rmvAlert = new AlertDialog.Builder(getActivity());
        rmvAlert.setMessage("Apakah anda yakin ingin menghapus item ini?");
        rmvAlert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.dao().delCart(cart);
                keranjangAdapter.data.remove(position);
                keranjangAdapter.notifyDataSetChanged();
                total();
                dialogInterface.cancel();
            }
        });
        rmvAlert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        rmvAlert.show();
    }

    private void total(){
        int ttl = 0;
        for(Cart cart : db.dao().getCartAll()){
            ttl += (Integer.parseInt(cart.getHarga()) * Integer.parseInt(cart.getQty()));
        }
        totals.setText("Rp " + ( (ttl == 0) ? "0" : (ttl + ( (payment.getSelectedItemPosition() == 0) ? Integer.parseInt(rpOngkir) : 0 ) ) ));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPesan:
                if(totals.getText().equals("Rp 0")){
                    Toast.makeText(getActivity(), "Maaf Keranjang Kosong", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String, Object> dataCheckout = new HashMap<>();
                    dataCheckout.put("id_users", new Sesi(getActivity()).get().getId());

                    HashMap<String, Object> dataPengiriman = new HashMap<>();
                    dataPengiriman.put("penerima", nama.getEditText().getText().toString());
                    dataPengiriman.put("no_telp", noTelp.getEditText().getText().toString());
                    dataPengiriman.put("alamat", alamat.getEditText().getText().toString());
                    dataPengiriman.put("payment", payment.getSelectedItem().toString());
                    dataPengiriman.put("ongkir", payment.getSelectedItemPosition() == 0 ? rpOngkir : "0");
                    dataPengiriman.put("catatan", catatan.getEditText().getText().toString());
                    dataPengiriman.put("waktu", wkt.getSelectedItem().toString());

                    dataCheckout.put("data_pengiriman", dataPengiriman);

                    List<HashMap<String, Object>> listProduk = new ArrayList<>();
                    for(Cart cart : db.dao().getCartAll()){
                        HashMap<String, Object> carts = new HashMap<>();
                        carts.put("id_produk", cart.getIdProduk());
                        carts.put("qty", cart.getQty());
                        listProduk.add(carts);
                    }
                    dataCheckout.put("data_produk", listProduk);

                    Call<ApiResponsed> transaksi = apiPesanan.transaksi(dataCheckout);
                    transaksi.enqueue(new Callback<ApiResponsed>() {
                        @Override
                        public void onResponse(Call<ApiResponsed> call, Response<ApiResponsed> response) {
                            if(response.body().getStatus().equals("200")){
                                db.dao().delAllCart();
                                keranjangAdapter.data.clear();
                                keranjangAdapter.notifyDataSetChanged();

                                nama.getEditText().setText("");
                                noTelp.getEditText().setText("");
                                alamat.getEditText().setText("");
                                payment.setSelection(0);
                                catatan.getEditText().setText("");

                                total();
                                Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponsed> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    Toast.makeText(getActivity(), new Gson().toJson(dataCheckout).toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
