package com.example.light.mediamanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.light.mediamanager.imgdata.MediaData;
import com.example.light.mediamanager.imghandle.ImageLoader;

import java.util.ArrayList;

/**
 * Created by light on 2017-02-23.
 */
public class ImageAdapter extends BaseAdapter{
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final ImageLoader mImageLoader;
    private ArrayList<MediaData> mMediaList;

    public ImageAdapter(Context aContext) {
        mContext = aContext;
        mInflater = LayoutInflater.from(mContext);
        mImageLoader = new ImageLoader(mContext.getApplicationContext());
    }

    public void initData(ArrayList<MediaData> aMediaList) {
        mMediaList = aMediaList;
    }

    @Override
    public int getCount() {
        if (mMediaList == null) {
            return 0;
        }
        return mMediaList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MediaData mediaData;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.thumbnail, null);
            mediaData = new MediaData();
            mediaData.imgview = (ImageView) convertView.findViewById(R.id.thumbnail);
            convertView.setTag(mediaData);
        } else {
            mediaData = (MediaData) convertView.getTag();
        }

        if (mMediaList != null && mMediaList.size() != 0) {
            mediaData.type = mMediaList.get(position).type;
            mediaData.mediaid = mMediaList.get(position).mediaid;
            mediaData.mediapath = mMediaList.get(position).mediapath;
            mediaData.orientation = mMediaList.get(position).orientation;

            mImageLoader.displayImage(mediaData);
        }
        return convertView;
    }
}

