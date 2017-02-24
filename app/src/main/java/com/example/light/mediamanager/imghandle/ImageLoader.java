package com.example.light.mediamanager.imghandle;

import android.content.Context;
import android.widget.ImageView;

import com.example.light.mediamanager.R;
import com.example.light.mediamanager.common.MediaData;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by light on 2017-02-23.
 */

public class ImageLoader {

    private static final int D_RES_ID = R.mipmap.ic_launcher;
    private final HashMap<ImageView, String> mImageViewMap;
    private final ExecutorService mExecutorService;

    public ImageLoader(Context aContext) {
        mImageViewMap = new HashMap<>();
        mExecutorService = Executors.newFixedThreadPool(10);
//        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public void displayImage(MediaData mediaData) {
        if (null != mediaData) {
            mImageViewMap.put(mediaData.imgview, mediaData.mediapath);
            mExecutorService.submit(new ImageRunnable(mediaData, mImageViewMap));
            mediaData.imgview.setImageResource(D_RES_ID);
        }
    }
}
