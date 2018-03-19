package com.pinet.cdemounlink.princriple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SRPImageRequest {

    private ExecutorService mExecutorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());;

    public SRPImageRequest()
    {

    }


    public void getBitmap(final String imageUrl, final BitmapListener bitmapListener)
    {

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoadImage(imageUrl);
                bitmapListener.getBitMap(bitmap);
            }
        });
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


    public interface BitmapListener{
        public void getBitMap(Bitmap bitmap);
    }


}
