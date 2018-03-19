package com.pinet.cdemounlink.ocp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/24.
 */

public class OCPDiskCache {

    private static final String SDCARD_DIR = "cache/imagechahe";

    public void puT(String imageUrl, Bitmap bitmap)
    {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(SDCARD_DIR.concat(imageUrl));
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream != null)
            {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Bitmap get(String imageUrl)
    {
       return BitmapFactory.decodeFile(SDCARD_DIR.concat(imageUrl));
    }



}
