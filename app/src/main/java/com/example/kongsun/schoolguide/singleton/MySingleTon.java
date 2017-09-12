package com.example.kongsun.schoolguide.singleton;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by kongsun on 9/10/17.
 */

public class MySingleTon {
    private static MySingleTon instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private MySingleTon(Context context){
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

    }

    public static MySingleTon getInstance(Context context){

        if(instance == null){
            instance = new MySingleTon(context);
        }
        return instance;
    }
    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void addRequest(Request request){
        requestQueue.add(request);
    }
}
