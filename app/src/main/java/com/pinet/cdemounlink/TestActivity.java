package com.pinet.cdemounlink;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */

public class TestActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private StringAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mRecycler = (RecyclerView) findViewById(R.id.nestparent_bottom);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setLayoutManager(new LinearLayoutManager(TestActivity.this));
        List<String> mList = getList();
        mAdapter = new StringAdapter(TestActivity.this,mList);
        mRecycler.setAdapter(mAdapter);

    }

    private List<String> getList()
    {
        ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<200;i++)
        {
            mList.add("这是第"+ i+ "几条数据");
        }
        return mList;
    }



    private class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringHolder>{

        private Context mContext;
        private List<String> mList;

        public StringAdapter(Context mContext,List<String> mList)
        {
            this.mContext = mContext;
            this.mList = mList;
        }


        @Override
        public StringHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(mContext).inflate(R.layout.item_main,null);
            return new StringHolder(view);

        }

        @Override
        public void onBindViewHolder(StringHolder holder, int position) {
            String content = mList.get(position);
            if(!TextUtils.isEmpty(content))
            {
                holder.content.setText(content);
            }
        }

        @Override
        public int getItemCount() {
            return this.mList.size();
        }

        class StringHolder extends RecyclerView.ViewHolder {

            private TextView content;

            public StringHolder(View itemView) {
                super(itemView);
                content = (TextView) itemView.findViewById(R.id.tv_item_main);

            }
        }
    }
}
