package com.example.light.mediamanager.imghandle;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.provider.MediaStore;

import com.example.light.mediamanager.imgdata.MediaData;


public class ImageUtils {

    public static Bitmap getImageBitmap(String aImagePath, int aOrientation) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            Bitmap displayImg = BitmapFactory.decodeFile(aImagePath, options);
            return getRotateBitmap(displayImg, aOrientation);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap getRotateBitmap(Bitmap aSrc, int aOrientation) {
        if (aSrc == null) {
            return null;
        }

        int width = aSrc.getWidth();
        int height = aSrc.getHeight();

        Matrix m = new Matrix();

        if (0 != aOrientation) {
            m.setRotate(aOrientation);
        }

        return Bitmap.createBitmap(aSrc,
                0,
                0,
                width,
                height,
                m,
                false);

    }

    public static Bitmap getImageThumbnail(MediaData mediaData) {
        ContentResolver contentResolver = mediaData.imgview.getContext().getContentResolver();
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(
                contentResolver,
                mediaData.mediaid,
                MediaStore.Images.Thumbnails.MINI_KIND,
                null
        );
        return getCenterBitmap(bitmap, mediaData.orientation);
    }

    private static Bitmap getCenterBitmap(Bitmap src, int orientation) {
        if (src == null) {
            return null;
        }

        int width = src.getWidth();
        int height = src.getHeight();
        Matrix matrix = new Matrix();

        if (orientation != 0) {
            matrix.setRotate(orientation);
        }

        Bitmap thumb;

        if (width >= height) {
            thumb = Bitmap.createBitmap(
                    src,
                    width / 2 - height / 2,
                    0,
                    height,
                    height,
                    matrix,
                    true
            );
        } else {
            thumb = Bitmap.createBitmap(
                    src,
                    height / 2 - width / 2,
                    0,
                    width,
                    width,
                    matrix,
                    true
            );
        }

        return thumb;
    }

}
