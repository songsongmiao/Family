package com.library.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.library.nestrefresh.NestedRefresh;
import com.library.nestrefresh.R;
import com.library.nestrefresh.RefreshControlStyleCustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class NestRefresh extends AppCompatActivity {

    private NestedRefresh mRefresh;
    private RecyclerView mRecycler;
    private TestAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_nest_view);
        mRefresh = (NestedRefresh) findViewById(R.id.refresh_container);
        mRefresh.setSwipeControl(new RefreshControlStyleCustom(this));
        mRecycler = (RecyclerView) findViewById(R.id.recycler_item);
        mRecycler.setLayoutManager(new LinearLayoutManager(NestRefresh.this));
        mRecycler.setItemAnimator(new DefaultItemAnimator());

        List<String> StringList = new ArrayList<>();
        for(int i=0;i<31;i++)
        {
            StringList.add("这是第" + i+ "条数据！");
        }
        //mAdapter = new RefreshAdapter(ReFreshActivity.this,new ArrayList<String>());
        mAdapter = new TestAdapter(NestRefresh.this,StringList);
        mRecycler.setAdapter(mAdapter);
        mRefresh.setOnRefreshListener(new NestedRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.finishRefresh();
            }

            @Override
            public void onLoading() {
                // mRefresh.hiddenLoadMore();
                mRefresh.setIsLoadComplete(true);//设置加载结束内容
            }
        });
    }
}
