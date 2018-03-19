package com.pinet.cdemounlink.ocp;

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

public class OCPImageLoader {

    private ExecutorService mExecutorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    private LruCache<String,Bitmap> mLruCache;


    public OCPImageLoader()
    {
        initCache();
    }



    private void initCache()
    {
        final int mMaxSize = (int) (Runtime.getRuntime().maxMemory()/1024);
        int cacheMemory = mMaxSize /4;

        mLruCache = new LruCache<String,Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes()* value.getHeight()/1024;
            }
        };
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
        Bitmap bitmap = mLruCache.get(imageUrl);
        if(bitmap == null)
        {
            this.mExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    //加载缓存图片
                    final Bitmap bitmap = downLoadImage(imageUrl);
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
                    mLruCache.put(imageUrl,bitmap);
                }
            });
        }
        else
        {
            imageView.setImageBitmap(bitmap);
        }
    }


    /***
     * 下载图片
     * @param imageUrl
     * @return
     */
    private Bitmap downLoadImage(String imageUrl)
    {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap =  BitmapFactory.decodeStream(connection.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
