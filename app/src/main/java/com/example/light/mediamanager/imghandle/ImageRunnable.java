package com.example.light.mediamanager.imghandle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.light.mediamanager.imgdata.MediaData;

import java.util.HashMap;

/**
 * Created by light on 2017-02-23.
 */

public class ImageRunnable implements Runnable{

    private final MediaData mediaData;
    private final HashMap<ImageView, String> imageViewStringHashMap;

    public ImageRunnable(MediaData mediaData, HashMap<ImageView, String> imageViewStringHashMap) {
        this.mediaData = mediaData;
        this.imageViewStringHashMap = imageViewStringHashMap;
    }


    @Override
    public void run() {
        if (isImageViewValid(mediaData)) {
            Bitmap bmp = ImageUtils.getImageThumbnail(mediaData);
            Activity a = (Activity) mediaData.imgview.getContext();
            ImageViewRunnable imageViewRunnable = new ImageViewRunnable(
                    bmp,
                    mediaData,
                    imageViewStringHashMap
            );
            a.runOnUiThread(imageViewRunnable);
        }
    }

    private boolean isImageViewValid(MediaData mediaData) {
        String mediaPath = imageViewStringHashMap.get(mediaData.imgview);
        return !(mediaPath == null || !mediaPath.equals(mediaData.mediapath));
    }

}
