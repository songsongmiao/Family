package com.pinet.cdemounlink;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.pinet.cdemounlink.test.TestData;
import com.pinet.imagelib.cache.disc.naming.Md5FileNameGenerator;
import com.pinet.imagelib.core.DisplayImageOptions;
import com.pinet.imagelib.core.ImageLoader;
import com.pinet.imagelib.core.ImageLoaderConfiguration;
import com.pinet.imagelib.core.assist.QueueProcessingType;
import com.pinet.imagelib.core.display.CircleBitmapDisplayer;
import com.pinet.imagelib.core.display.FadeInBitmapDisplayer;
import com.pinet.imagelib.core.display.SimpleBitmapDisplayer;
import com.pinet.imagelib.core.listener.ImageLoadingListener;
import com.pinet.imagelib.core.listener.PauseOnScrollListener;
import com.pinet.imagelib.core.listener.SimpleImageLoadingListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static android.R.id.input;

/**
 * Created by Administrator on 2018-01-10.
 */

public class ImageActivity extends AppCompatActivity {

    private final String TAG = "ImageActivity";


    //设置图片加载缓存
    protected boolean pauseOnScroll = false;
    protected boolean pauseOnFling = true;


    private ListView listViewImage;
    private ImageAdapter mAdapter;
    private List<String> mImageList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);

        initImageLoader(this);//初始话图片的配置

        listViewImage = (ListView) findViewById(R.id.list_image);
        mAdapter= new ImageAdapter(this,new ArrayList<String>());
        listViewImage.setAdapter(mAdapter);


        new Thread(new Runnable() {
            @Override
            public void run() {
                onLoadImage();
            }
        }).start();
    }


    private void onLoadImage()
    {
        String content = "page=1";
        String address = "http://wx.pinet.cc:8081/plife2/index.php/Api/Api/Popular";
        HttpURLConnection conn = null;
        String proxyHost = android.net.Proxy.getDefaultHost();
        if(proxyHost != null)
        {
            java.net.Proxy p = new java.net.Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress(android.net.Proxy.getDefaultHost(),
                            android.net.Proxy.getDefaultPort()));
            try {
                conn = (HttpURLConnection) new URL(address).openConnection(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                conn = (HttpURLConnection) new URL(address).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(conn == null )
        {
            Log.i(TAG,"这个数据的封装出现问题");
            return ;
        }
        conn.setConnectTimeout(4*1000);
        conn.setDoInput(true);
        try {

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type",
                    "application/x-www-form-urlencoded");
            OutputStream os = conn.getOutputStream();
            os.write(content.getBytes());
            os.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        int code = 0;
        try {
            code = conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(code == HttpURLConnection.HTTP_OK)
        {
            InputStream is = null;
            try {
                is = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int len = -1;
                while ((len = is.read(data)) != -1) {
                    bos.write(data, 0, len);
                }
                String text = bos.toString();

                TestData datas = new Gson().fromJson(text,TestData.class);
                TestData.DataBean mDataBean = datas.getData();
                if(mDataBean == null)
                {
                    Log.i(TAG,"获取的数据的图片没有！");
                    return ;
                }
                List<TestData.DataBean.ListBean> mList  = mDataBean.getList();
                 if(mList.isEmpty())
                 {
                    return;
                 }
                for(TestData.DataBean.ListBean item: mList)
                {
                    String duffPath = item.getLogopath();
                    if(duffPath.isEmpty())
                        continue;
                    mImageList.add("http://wx.pinet.cc:8081/plife2/" + duffPath);
                    Log.i(TAG,"请求图片路径： "+ "http://wx.pinet.cc:8081/plife2/" + duffPath);
                }
                ImageActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setData(mImageList);
                    }
                });

                Log.i(TAG,"请求成功的数据： "+ text);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }




    private class ImageAdapter extends BaseAdapter{

        private Context mContext;
        private List<String> mList = new ArrayList<>();
        private DisplayImageOptions mOption;
        private ImageLoadingListener mLoadListener;
        public ImageAdapter(Context context,List<String> mList)
        {
            this.mContext = context;
            if(mList != null)
            {
                this.mList = mList;
            }
            init();
        }


        private void init()
        {
           // CircleBitmapDisplayer  mCircleDisplay = new CircleBitmapDisplayer(Color.WHITE,5);
            mOption = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .showImageForEmptyUri(R.mipmap.ic_empty)
                    .showImageOnFail(R.mipmap.ic_error)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)

                    .displayer(new SimpleBitmapDisplayer())
                    .build();
            mLoadListener = new LoadAnimationListener();
        }

        public void setData(List<String> mList)
        {
            if(mList == null || mList.isEmpty())
            {
                //如果有刷新操作，请结束该刷新的内容
                return ;
            }
            this.mList = mList;
            this.notifyDataSetChanged();
        }






        @Override
        public int getCount() {
            return this.mList.size();
        }

        @Override
        public Object getItem(int position) {
            return this.mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if(convertView == null)
            {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image_layout,null);
                holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_image_icon);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_image_title);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }
            //加载数据
            String content = mList.get(position);
            if(content == null )
            {
                content = "http://wx.pinet.cc:8081/plife2/Upload/Img/201706/5948d86f779bd.png";
            }
            //ImageLoaderConfiguration
            ImageLoader.getInstance().displayImage(content,holder.ivImage,mOption,mLoadListener);

            return convertView;
        }


        class ViewHolder{

            ImageView ivImage;
            TextView tvTitle;


        }

        class LoadAnimationListener extends SimpleImageLoadingListener {

            private List<String> mImageURL = Collections.synchronizedList(new LinkedList<String>());

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                super.onLoadingComplete(imageUri, view, loadedImage);
                if(loadedImage != null)
                {
                    ImageView mImageView = (ImageView) view;
                    boolean isDisplay = !mImageURL.contains(imageUri);
                    if(isDisplay)
                    {
                        FadeInBitmapDisplayer.animate(mImageView,500);//时间
                        mImageURL.add(imageUri);
                    }
                }
            }
        }

    }


}
