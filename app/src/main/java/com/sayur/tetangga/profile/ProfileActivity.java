package com.sayur.tetangga.profile;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.sayur.tetangga.R;
import com.sayur.tetangga.utils.ApiResponsed;
import com.sayur.tetangga.utils.Server;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout nama_lengkap, no_telp, alamat, password;
    Button btnUpdate;
    ApiProfile apiProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        no_telp = findViewById(R.id.no_telp);
        alamat = findViewById(R.id.alamat);
        password = findViewById(R.id.password);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        apiProfile = new Server().load().create(ApiProfile.class);
        profile();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpdate:
                HashMap<String, Object> upProfile = new HashMap<>();
                upProfile.put("nama_lengkap", nama_lengkap.getEditText().getText().toString());
                upProfile.put("no_telp", no_telp.getEditText().getText().toString());
                upProfile.put("alamat", alamat.getEditText().getText().toString());
                upProfile.put("password", password.getEditText().getText().toString());
                upProfile.put("foto", "");
                apiProfile.upProfile("2", upProfile).enqueue(new Callback<ApiResponsed>() {
                    @Override
                    public void onResponse(Call<ApiResponsed> call, Response<ApiResponsed> response) {
                        if(response.body().getStatus().equals("200")){
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponsed> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    private void profile(){
        apiProfile.profile("2").enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.body().getStatus().equals("200")){
                    Users.Data usersData = response.body().getData();
//                    Toast.makeText(getApplicationContext(), usersData.getNama_lengkap(), Toast.LENGTH_SHORT).show();
                    nama_lengkap.getEditText().setText(usersData.getNama_lengkap());
                    no_telp.getEditText().setText(usersData.getNo_telp());
                    alamat.getEditText().setText(usersData.getAlamat());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
