package com.example.light.mediamanager.imgdata;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.light.mediamanager.MainActivity;

import java.util.ArrayList;

/**
 * Created by light on 2017-02-23.
 */

public class GetMediaData {

    //거의 개념의 구분을 위해 클래스를 만든 느낌이다. 클래스 하나에 정적메소드 하나뿐이다.
    public static void getImageArrayData(Context aContext, ArrayList<MediaData> aMediaData) {
        String[] imagecolumns = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.ORIENTATION
        };
        Cursor imageCursor = aContext.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                imagecolumns,
                null,
                null,
                null
        );
        if (imageCursor != null && imageCursor.moveToFirst()) {
            int imageid = 0;
            String imagePath = null;
            int orientation = 0;
            int imageIdColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
            int imagePathColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int imageOrientationColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);

            do {
                imageid = imageCursor.getInt(imageIdColumnIndex);
                imagePath = imageCursor.getString(imagePathColumnIndex);
                orientation = imageCursor.getInt(imageOrientationColumnIndex);

                MediaData info = new MediaData();
                info.type = MainActivity.TYPE_IMAGE;
                info.mediaid = imageid;
                info.mediapath = imagePath;
                info.orientation = orientation;
                aMediaData.add(info);
            } while (imageCursor.moveToNext());
        }
    }
}
