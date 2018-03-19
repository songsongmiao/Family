package com.library.nestrefresh;

import android.view.View;

/**
 * Created by Administrator on 2017/11/21.
 */

public interface RefreshControl {

    enum SwipeModel {
        SWIPE_BOTH,
        SWIPE_ONLY_REFRESH,
        SWIPE_ONLY_LOADINN,
        SWIPE_NONE
    }

    enum SwipeStatus {
        // 上拉刷新
        SWIPE_HEAD_OVER,                        //提示: 松开刷新
        SWIPE_HEAD_TOAST,                       //提示: 下拉刷新
        SWIPE_HEAD_LOADING,                     //提示: 刷新中
        SWIPE_HEAD_COMPLETE,                    //提示: 刷新完成
        // 下拉加载
        SWIPE_FOOT_LOADING,
        SWIPE_FOOT_COMPLETE;
    }

    // 头部刷新View
    View getSwipeHead();

    // 底部加载View
    View getSwipeFoot();

    // 头部刷新View过度拉伸尺度      getSwipeHead得到的View得到高度后  减去此高度后  为松开刷新的实际高度
    int getOverScrollHei();

    // 状态改变回掉
    void onSwipeStatue(SwipeStatus status, int visibleHei, int wholeHei);

}
