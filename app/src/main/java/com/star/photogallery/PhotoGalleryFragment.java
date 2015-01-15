package com.star.photogallery;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;

public class PhotoGalleryFragment extends Fragment {

    private static final String TAG = "PhotoGalleryFragment";

    private GridView mGridView;
    private ArrayList<GalleryItem> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        mGridView = (GridView) v.findViewById(R.id.gridView);
        setupAdapter();

        return v;
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<GalleryItem>> {

        @Override
        protected ArrayList<GalleryItem> doInBackground(Void... params) {
//            try {
//                String result = new FlickrFetchr().getUrl("https://www.google.com");
//                Log.i(TAG, "Fetch contents of URL: " + result);
//            } catch (IOException e) {
//                Log.e(TAG, "Failed to fetch URL: ", e);
//                e.printStackTrace();
//            }
//            new FlickrFetchr().fetchItems();
//
//            return null;

            return new FlickrFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(ArrayList<GalleryItem> items) {
            mItems = items;
            setupAdapter();
        }
    }

    private void setupAdapter() {
        if ((getActivity() != null) && (mGridView != null)) {
            if (mItems != null) {
                mGridView.setAdapter(new ArrayAdapter<GalleryItem>(
                        getActivity(), android.R.layout.simple_gallery_item, mItems));
            } else {
                mGridView.setAdapter(null);
            }
        }
    }
}
