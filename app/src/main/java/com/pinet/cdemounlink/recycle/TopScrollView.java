package com.pinet.cdemounlink.recycle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/10/18.
 */

public class TopScrollView extends ScrollView {

    private boolean isScroll ;

    public TopScrollView(Context context) {
        super(context);
    }

    public TopScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean isScroll() {
        return isScroll;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if(isScroll)
        {
            requestDisallowInterceptTouchEvent(true);
            return true;
        }
        else
        {
            return super.dispatchTouchEvent(ev);
        }
    }
}
