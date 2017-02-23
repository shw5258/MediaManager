package com.example.light.mediamanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.light.mediamanager.imghandle.ImageUtils;

public class ImageViewerActivity extends AppCompatActivity {

    public static final String IMAGE_PATH = "IMAGE_PATH";
    public static final String ORIENTATION = "ORIENTATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent i = getIntent();
        String imagePath = i.getStringExtra(IMAGE_PATH);
        int orientation = i.getIntExtra(ORIENTATION, 0);

        showImageView(imagePath, orientation);
    }

    private void showImageView(String imagePath, int orientation) {

        Bitmap bmp = ImageUtils.getImageBitmap(imagePath, orientation);
        ImageView iView = (ImageView) findViewById(R.id.showimage);
        iView.setImageBitmap(bmp);
    }
}
