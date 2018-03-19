package com.pinet.cdemounlink.test;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/12/14.
 */

public class SolveClickTouchConflictLayout extends FrameLayout {

    private final String TAG = "SolveClickTouch";


    public SolveClickTouchConflictLayout(Context context) {
        this(context, null);
    }

    public SolveClickTouchConflictLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SolveClickTouchConflictLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void initView() {

    }

    private boolean mScrolling;
    private float touchDownX;
    private float touchDownY;

    private boolean isClick;

    //拦截触摸事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownX = event.getX();
                touchDownY = event.getY();
                mScrolling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(touchDownX - event.getX()) >= ViewConfiguration.get(getContext()).getScaledTouchSlop()||Math.abs(touchDownY - event.getY()) >= ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    mScrolling = true;
                } else {
                    mScrolling = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                mScrolling = false;

                break;
        }
        return mScrolling;
    }

    float x2 = 0;
    float y2 = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       // Log.e(TAG,"onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;//消费触摸事件
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                //左滑
                if (touchDownX - x2 > dip2px(getContext(), 40)) {
                    if(mOnSlideListener!=null){
                        mOnSlideListener.onRightToLeftSlide();
                        Log.e(TAG,"左滑事件");
                    }
                }
                //右滑
                if (touchDownX - x2 < -dip2px(getContext(), 40)) {
                    if(mOnSlideListener!=null){
                        mOnSlideListener.onLeftToRightSlide();
                        Log.e(TAG,"右滑事件");
                    }
                }
                //上滑
                if (touchDownY - y2 > dip2px(getContext(), 40)) {
                    if(mOnSlideListener!=null){
                        mOnSlideListener.onDownToUpSlide();
                        Log.e(TAG,"上滑事件");
                    }
                }
                //下滑
                if (touchDownY - y2 < -dip2px(getContext(), 40)) {
                    if(mOnSlideListener!=null){
                        mOnSlideListener.onUpToDownSlide();
                        Log.e(TAG,"下滑事件");
                    }
                }

                if(Math.abs(touchDownX - x2) < 5)
                {
                    Log.e(TAG,"onInterceptTouchEvent 触发点击事件");
                   // isClick = true;
                }
                Log.e(TAG,"onInterceptTouchEvent 滑动的距离： "+ Math.abs(touchDownX - x2));
                break;
        }

        return super.onTouchEvent(event);
    }

    private OnSlideListener mOnSlideListener;

    public OnSlideListener getOnSlideListener() {
        return mOnSlideListener;
    }

    public void setmSetOnSlideListener(OnSlideListener mOnSlideListener) {
        this.mOnSlideListener = mOnSlideListener;
    }

    public interface OnSlideListener{
        void onRightToLeftSlide();
        void onLeftToRightSlide();
        void onUpToDownSlide();
        void onDownToUpSlide();
    }


    public float dip2px(Context mContext, int value)
    {
        return Resources.getSystem().getDisplayMetrics().density* value+ 0.5f;
    }

}
