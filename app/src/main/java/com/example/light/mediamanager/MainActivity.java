package com.example.light.mediamanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUEST_EXTERNAL = 100;
    public static String TYPE = "TYPE";
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_VIDEO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button imgBtn = (Button) findViewById(R.id.imagebtn);
        Button videoBtn = (Button) findViewById(R.id.videobtn);

        imgBtn.setOnClickListener(this);
        videoBtn.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL
                );
                imgBtn.setEnabled(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imagebtn:
                openImageViewer();
                break;

            case R.id.videobtn:
                openVideoPlayer();
                break;

            default:
                break;
        }
    }

    private void openVideoPlayer() {
        Intent i = new Intent(this, ImageGridActivity.class);
        i.putExtra(TYPE, TYPE_VIDEO);
        startActivity(i);
    }

    private void openImageViewer() {
        Intent i = new Intent(this, ImageGridActivity.class);
        i.putExtra(TYPE, TYPE_IMAGE);
        startActivity(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_EXTERNAL:
                if (grantResults.length == 0){
                    break;
                } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Button imgBtn = (Button) findViewById(R.id.imagebtn);
                    imgBtn.setEnabled(true);
                }
                break;
            default:
                break;
        }
    }
}
