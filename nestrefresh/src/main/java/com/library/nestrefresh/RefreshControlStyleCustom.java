package com.library.nestrefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/22.
 */

public class RefreshControlStyleCustom implements RefreshControl  {

    private Context mContent;

    private View headView;
    private View overHead;
    private ProgressBar statusPre;

    private ImageView statusComplete;
    private TextView textInfo;

    private View footView;
    private TextView loadProgressBar;
    private TextView textLoad;

    public RefreshControlStyleCustom(Context context) {
        this.mContent = context;
        LayoutInflater inflater = LayoutInflater.from(mContent);
        this.headView = inflater.inflate(R.layout.swipe_control_head, null);
        overHead = headView.findViewById(R.id.over_head);
        statusPre = (ProgressBar) headView.findViewById(R.id.image_info);

        statusComplete = (ImageView) headView.findViewById(R.id.ok);
        textInfo = (TextView) headView.findViewById(R.id.text_info);

        this.footView = inflater.inflate(R.layout.swipe_control_foot,null);
        loadProgressBar = (TextView) footView.findViewById(R.id.text_load_more_progress);
        textLoad = (TextView)footView.findViewById(R.id.text_load_more_over);
    }

    @Override
    public View getSwipeHead() {
        return headView;
    }

    @Override
    public View getSwipeFoot() {
        return footView;
    }

    @Override
    public int getOverScrollHei() {
        return overHead.getHeight();
    }

    @Override
    public void onSwipeStatue(SwipeStatus status, int visibleHei, int wholeHei) {
        switch (status) {
            case SWIPE_HEAD_OVER:
                statusPre.setVisibility(View.VISIBLE);
                statusComplete.setVisibility(View.INVISIBLE);
                if(!textInfo.getText().toString().equals("松开刷新")){
                    textInfo.setText("松开刷新");
                }

                break;
            case SWIPE_HEAD_TOAST:
                statusPre.setVisibility(View.VISIBLE);
                statusComplete.setVisibility(View.INVISIBLE);

                if(!textInfo.getText().toString().equals("上拉刷新")){
                    textInfo.setText("上拉刷新");
                }
                break;
            case SWIPE_HEAD_LOADING:
                statusPre.setVisibility(View.VISIBLE);
                statusComplete.setVisibility(View.INVISIBLE);
                if(!textInfo.getText().toString().equals("正在拼命刷新中")){
                    textInfo.setText("正在拼命刷新中");
                }
                break;
            case SWIPE_HEAD_COMPLETE:
                statusPre.setVisibility(View.INVISIBLE);

                statusComplete.setVisibility(View.VISIBLE);
                if(!textInfo.getText().toString().equals("刷新成功")){
                    textInfo.setText("刷新成功");
                }
                break;

            case SWIPE_FOOT_LOADING:
                loadProgressBar.setVisibility(View.VISIBLE);
                textLoad.setVisibility(View.GONE);
                break;
            case SWIPE_FOOT_COMPLETE:
                loadProgressBar.setVisibility(View.GONE);
                textLoad.setVisibility(View.VISIBLE);
                break;
        }
    }


}
