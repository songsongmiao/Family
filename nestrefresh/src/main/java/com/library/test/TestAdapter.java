package com.library.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.library.nestrefresh.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.StringHolder>{


    private Context mContext;
    private List<String> mList;

    public TestAdapter(Context mContext,List<String> mList)
    {
        this.mContext = mContext;
        this.mList = mList;
    }


    public void setData(List<String> mList)
    {
        if(mList == null)
        {
            return ;
        }
        this.mList.addAll(mList);
        this.notifyDataSetChanged();
    }




    @Override
    public StringHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.test_item,null);
        return new StringHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(StringHolder holder, int position) {

        String content = mList.get(position);
        holder.tvTitle.setText(content);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    class StringHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        public StringHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_test);
        }
    }




}
