package com.sayur.tetangga;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sayur.tetangga.cart.TfActivity;
import com.theartofdev.edmodo.cropper.CropImage;


public class CropperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropper);
        if(getIntent().hasExtra("img")){
            CropImage.activity(Uri.parse(getIntent().getStringExtra("img"))).start(CropperActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Intent i = new Intent();
                i.putExtra("img", resultUri.toString());
                setResult(TfActivity.REQ_CROP, i);
                finish();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
