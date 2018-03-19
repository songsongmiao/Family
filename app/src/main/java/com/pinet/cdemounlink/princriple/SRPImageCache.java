package com.pinet.cdemounlink.princriple;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 2017/11/24.
 * 单一职责原则实现规范
 */

public class SRPImageCache {

    private LruCache<String,Bitmap> mLruCache;

    public SRPImageCache()
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


    public void put(String imageUrl,Bitmap bitmap)
    {
        this.mLruCache.put(imageUrl,bitmap);
    }

    public Bitmap get(String imageUrl)
    {
        return this.mLruCache.get(imageUrl);
    }




}
