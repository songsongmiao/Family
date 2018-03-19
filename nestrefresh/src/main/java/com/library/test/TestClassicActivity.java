package com.library.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.library.classic.CanRecyclerViewHeaderFooter;
import com.library.classic.CanRefreshLayout;
import com.library.nestrefresh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 * 这里数显下拉字母使用的style 是 3，3
 * 函数布局将使用test_classic_char
 * 初始化时，我们需要设置下拉的字母：
 *  StoreHouseRefreshView storeHouseRefreshView = (StoreHouseRefreshView) findViewById(R.id.can_refresh_header);
 storeHouseRefreshView.initWithString("pull to refresh");
 *
 *
 */

public class TestClassicActivity extends AppCompatActivity {

    private CanRefreshLayout mRefreshLayout;

    private CanRecyclerViewHeaderFooter mHeader;
    private CanRecyclerViewHeaderFooter mFooter;
    private RecyclerView mRecycler;
    private TestAdapter mAdapter;

    private ImageView mImageHeader;
    private ProgressBar mProgress;
    private TextView mTextLoadMore;

    private int  i ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_classic_view);
        mRefreshLayout = (CanRefreshLayout) findViewById(R.id.refresh);
        mHeader = (CanRecyclerViewHeaderFooter) findViewById(R.id.header);
        mFooter = (CanRecyclerViewHeaderFooter) findViewById(R.id.footer);
        mRecycler = (RecyclerView) findViewById(R.id.can_content_view);

        mImageHeader = (ImageView) findViewById(R.id.iv_head);
        mProgress = (ProgressBar) findViewById(R.id.pb);
        mTextLoadMore = (TextView) findViewById(R.id.tv_loadmore);

        mRefreshLayout.setStyle(0,0);
        int mMaxHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100f,getResources().getDisplayMetrics());
        mRefreshLayout.setMaxHeaderHeight(mMaxHeight);
        mRefreshLayout.setOnStartUpListener(new CanRefreshLayout.OnStartUpListener() {
            @Override
            public void onUp() {
                Log.e("RefreshClassicActivity", "onUp");
            }

            @Override
            public void onReset() {
                Log.e("RefreshClassicActivity", "onUp");
            }
        });
        mRefreshLayout.setOnRefreshListener(new CanRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // mAdapter.setList(MainBean.getList());
                        List<String> StringList = new ArrayList<>();
                        for( int j = i*10;j<(i+1)*10;j++)
                        {
                            StringList.add("这是第" + j+ "条数据！");
                        }
                        mAdapter.setData(StringList);

                        mRefreshLayout.refreshComplete();

                        mTextLoadMore.setVisibility(View.GONE);
                        mProgress.setVisibility(View.VISIBLE);
                        mFooter.setLoadEnable(true);

                    }
                }, 1000);
            }
        });

        mRecycler.setLayoutManager(new LinearLayoutManager(TestClassicActivity.this));
        mRecycler.setItemAnimator(new DefaultItemAnimator());

        mHeader.attachTo(mRecycler,true);
        mFooter.attachTo(mRecycler,false);

        mFooter.setLoadMoreListener(new CanRecyclerViewHeaderFooter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {


                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        adapter.addMoreList(MainBean.getList());
                        List<String> StringList = new ArrayList<>();
                        for( int j = i*10;j<(i+1)*10;j++)
                        {
                            StringList.add("这是第" + j+ "条数据！");
                        }
                        mAdapter.setData(StringList);
                        i++;

                        if (i == 2) {
                            mTextLoadMore.setVisibility(View.VISIBLE);
                            mProgress.setVisibility(View.GONE);
                            mFooter.setLoadEnable(false);
                        }


                        mFooter.loadMoreComplete();
                    }
                }, 1000);



            }
        });

        List<String> StringList = new ArrayList<>();
        for(int i=0;i<11;i++)
        {
            StringList.add("这是第" + i+ "条数据！");
        }
        mAdapter = new TestAdapter(TestClassicActivity.this,StringList);

        mRecycler.setAdapter(mAdapter);

    }
}
