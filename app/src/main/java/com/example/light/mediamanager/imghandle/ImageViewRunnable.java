package com.example.light.mediamanager.imghandle;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.light.mediamanager.R;
import com.example.light.mediamanager.common.MediaData;

import java.util.HashMap;

public class ImageViewRunnable implements Runnable{
    private static final int D_RES_ID = R.mipmap.ic_launcher;
    private MediaData mMediaData;
    private HashMap<ImageView, String> mImageViewMap = null;
    private Bitmap mBitmap;

    public ImageViewRunnable(Bitmap bmp, MediaData mediaData, HashMap<ImageView, String> imageViewStringHashMap) {
        mBitmap = bmp;
        mMediaData = mediaData;
        mImageViewMap = imageViewStringHashMap;
    }


    @Override
    public void run() {
        if (inImageViewValid()) {
            if (mBitmap != null) {
                mMediaData.imgview.setImageBitmap(mBitmap);
            } else {
                mMediaData.imgview.setImageResource(D_RES_ID);
            }
        }
    }

    private boolean inImageViewValid() {
        String mediaPath = mImageViewMap.get(mMediaData.imgview);
        return !(mediaPath == null || !mediaPath.equals(mMediaData.mediapath));
    }

}
