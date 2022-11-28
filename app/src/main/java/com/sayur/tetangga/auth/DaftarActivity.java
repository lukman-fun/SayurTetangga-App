package com.sayur.tetangga.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.sayur.tetangga.R;
import com.sayur.tetangga.profile.Users;
import com.sayur.tetangga.utils.ApiResponsed;
import com.sayur.tetangga.utils.Server;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout nama_lengkap, no_telp, password, alamat;
    Button btnDaftar;
    TextView toLogin;
    ApiAuth apiAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_daftar);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        no_telp = findViewById(R.id.no_telp);
        password = findViewById(R.id.password);
        alamat = findViewById(R.id.alamat);
        btnDaftar = findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(this);
        toLogin = findViewById(R.id.toLogin);
        toLogin.setOnClickListener(this);
        apiAuth = new Server().load().create(ApiAuth.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDaftar:
                HashMap<String, Object> data = new HashMap<>();
                data.put("nama_lengkap", nama_lengkap.getEditText().getText().toString());
                data.put("no_telp", no_telp.getEditText().getText().toString());
                data.put("password", password.getEditText().getText().toString());
                data.put("alamat", alamat.getEditText().getText().toString());
                data.put("foto", "");
                apiAuth.daftar(data).enqueue(new Callback<ApiResponsed>() {
                    @Override
                    public void onResponse(Call<ApiResponsed> call, Response<ApiResponsed> response) {
                        if(response.body().getStatus().equals("200")){
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ApiResponsed> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.toLogin:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }
    }
}
