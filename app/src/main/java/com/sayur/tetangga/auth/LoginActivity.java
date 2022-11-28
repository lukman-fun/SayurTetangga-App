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
import com.sayur.tetangga.MainActivity;
import com.sayur.tetangga.R;
import com.sayur.tetangga.profile.Users;
import com.sayur.tetangga.utils.Server;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout no_telp, password;
    Button btnLogin;
    TextView toDaftar;
    ApiAuth apiAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        no_telp = findViewById(R.id.no_telp);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        toDaftar = findViewById(R.id.toDaftar);
        toDaftar.setOnClickListener(this);
        apiAuth = new Server().load().create(ApiAuth.class);
        if(new Sesi(this).valid()){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                HashMap<String, Object> data = new HashMap<>();
                data.put("no_telp", no_telp.getEditText().getText().toString());
                data.put("password", password.getEditText().getText().toString());
                apiAuth.login(data).enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        if(response.body().getStatus().equals("200")){
                            new Sesi(getApplicationContext()).set(response.body().getData());
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.toDaftar:
                startActivity(new Intent(getApplicationContext(), DaftarActivity.class));
                finish();
                break;
        }
    }
}
