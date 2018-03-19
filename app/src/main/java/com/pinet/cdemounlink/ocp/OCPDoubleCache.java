package com.pinet.cdemounlink.ocp;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/11/24.
 */

public class OCPDoubleCache {

    private OCPDoubleCache mDoubleCache;
    private OCPDiskCache mDiskCache;


    public OCPDoubleCache()
    {
        mDoubleCache = new OCPDoubleCache();
        mDiskCache = new OCPDiskCache();
    }

    public void put(String imageUrl,Bitmap bitmap)
    {
       // this.mDiskCache.put();
    }


//    public Bitmap get(String imageUrl)
//    {
//        Bitmap bitmap =
//    }


}
