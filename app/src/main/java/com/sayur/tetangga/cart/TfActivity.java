package com.sayur.tetangga.cart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.sayur.tetangga.CropperActivity;
import com.sayur.tetangga.R;
import com.sayur.tetangga.utils.ApiResponsed;
import com.sayur.tetangga.utils.Server;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TfActivity extends AppCompatActivity implements View.OnClickListener {
    TextView bank;
    ImageView imgBukti;
    Button btnBukti;
    String base64Img = "";
    ImagePicker imagePicker;

    public static final int REQ_CROP = 101;
    ApiPesanan apiPesanan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tf);
        apiPesanan = new Server().load().create(ApiPesanan.class);
        bank = findViewById(R.id.bank);
        imgBukti = findViewById(R.id.imgBukti);
        imgBukti.setOnClickListener(this);
        btnBukti = findViewById(R.id.btnBukti);
        btnBukti.setOnClickListener(this);
        imagePicker = new ImagePicker(this, null, new OnImagePickedListener() {
            @Override
            public void onImagePicked(Uri imageUri) {
                Intent i = new Intent(TfActivity.this, CropperActivity.class);
                i.putExtra("img", imageUri.toString());
                startActivityForResult(i, REQ_CROP);
            }
        });
        bank();
    }

    private void bank(){
        Call<Bank> call = apiPesanan.getBank();
        call.enqueue(new Callback<Bank>() {
            @Override
            public void onResponse(Call<Bank> call, Response<Bank> response) {
                if(response.body().getStatus().equals("200")){
                    String bnk = "";
                    for(Bank.Data dt : response.body().getData()){
                        bnk += dt.getNorek() + " - (" + dt.getNamaBank() + ") " + dt.getNama() + "\n";
                    }
                    bank.setText(bnk);
                }
            }

            @Override
            public void onFailure(Call<Bank> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBukti:
                imagePicker.choosePicture(true);
                break;
            case R.id.btnBukti:
//                Toast.makeText(getApplicationContext(), base64Img, Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(base64Img)){
                    Toast.makeText(getApplicationContext(), "Silahkan upload foto bukti transfer, dengan meng-klik gambar di atas", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String, Object> dataButkiTf = new HashMap<>();
                    dataButkiTf.put("img", base64Img);
                    Call<ApiResponsed> call = apiPesanan.upBuktiTf(getIntent().getStringExtra("id"), dataButkiTf);
                    call.enqueue(new Callback<ApiResponsed>() {
                        @Override
                        public void onResponse(Call<ApiResponsed> call, Response<ApiResponsed> response) {
                            if(response.body().getStatus().equals("200")){
                                Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponsed> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);
        if(requestCode == REQ_CROP){
            Uri resultUri = Uri.parse(data.getStringExtra("img"));
            if(resultUri != null){
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();

                    base64Img = Base64.encodeToString(bytes, Base64.DEFAULT);
                    imgBukti.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
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
