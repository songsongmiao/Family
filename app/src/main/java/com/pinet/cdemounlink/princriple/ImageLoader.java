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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/11/24.
 * 图片加载
 */

public class ImageLoader {

    public static final String TAG = "ImageLoader";

    private LruCache<String,Bitmap> mLruCache;

    private ExecutorService  mExecutorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());;


    public ImageLoader()
    {
        initImageCache();

    }

    /****
     * 初始化图片缓存区
     */
    private void initImageCache()
    {
        //获取cup最大内存
        final int mMaxMenmory = (int) (Runtime.getRuntime().maxMemory()/1024);
        //计算缓存内存大小
        int mCacheSize = mMaxMenmory /4;
        this.mLruCache = new LruCache<String,Bitmap>(mCacheSize){


            @Override
            protected int sizeOf(String key, Bitmap value) {

                //计算图片大小

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
