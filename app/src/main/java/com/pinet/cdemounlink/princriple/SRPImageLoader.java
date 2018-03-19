package com.pinet.cdemounlink.princriple;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SRPImageLoader {

    private SRPImageCache imageCache;
    private SRPImageRequest imageRequest;

    public SRPImageLoader()
    {
        this.imageCache = new SRPImageCache();
        this.imageRequest = new SRPImageRequest();
    }


    /***
     * 显示图片
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public void displayImage(final Context context, final String imageUrl, final ImageView imageView)
    {
        imageView.setTag(imageUrl);
        //通过线程池下载或者显示图片
        Bitmap bitmap = imageCache.get(imageUrl);
        if(bitmap == null)
        {

            imageRequest.getBitmap(imageUrl, new SRPImageRequest.BitmapListener() {
                @Override
                public void getBitMap(final Bitmap bitmap) {
                    if(bitmap == null)
                    {
                        return ;
                    }
                    if(imageView.getTag().equals(imageUrl))
                    {
                        //更新UI线程
                        Activity activity = (Activity) context;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                    imageCache.put(imageUrl,bitmap);
                }
            });

        }
        else
        {
            imageView.setImageBitmap(bitmap);
        }
    }

}
