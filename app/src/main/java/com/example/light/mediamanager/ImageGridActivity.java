package com.example.light.mediamanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.light.mediamanager.common.GetMediaData;
import com.example.light.mediamanager.common.MediaData;

import java.util.ArrayList;

public class ImageGridActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<MediaData> mMediaList = null;
    private int mType = MainActivity.TYPE_IMAGE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imggrid);

        Intent mainIntent = getIntent();
        mType = mainIntent.getIntExtra(MainActivity.TYPE, MainActivity.TYPE_IMAGE);
        mMediaList = new ArrayList<>();

        switch (mType) {
            case MainActivity.TYPE_IMAGE:
                GetMediaData.getImageArrayData(this, mMediaList);
                break;

            case MainActivity.TYPE_VIDEO:
                GetMediaData.getVideoArrayData(this, mMediaList);
                break;

            default:
                break;
        }

        GridView gv = (GridView) findViewById(R.id.gridview);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        imageAdapter.initData(mMediaList);
        gv.setAdapter(imageAdapter);
        gv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MediaData exactData = mMediaList.get(position);
        switch (mMediaList.get(position).type) {
            case MainActivity.TYPE_IMAGE:
                showOriImage(exactData.mediapath, exactData.orientation);
                break;

            case MainActivity.TYPE_VIDEO:
                showVideoPlayer(mMediaList.get(position).mediapath);
                break;

            default:
                break;
        }
    }

    private void showOriImage(String mediapath, int orientation) {
        Intent i = new Intent(this, ImageViewerActivity.class);
        i.putExtra(ImageViewerActivity.IMAGE_PATH, mediapath);
        i.putExtra(ImageViewerActivity.ORIENTATION, orientation);
        startActivity(i);
    }

    private void showVideoPlayer(String aVideoPath) {
        Intent i = new Intent(this, VideoPlayerActivity.class);
        i.putExtra(VideoPlayerActivity.VIDEO_PATH, aVideoPath);
        startActivity(i);
    }
}
