package com.pinet.cdemounlink.scroller;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/12/12.
 */

public class TestScroller extends ScrollView implements Scrollable {

    private SlideScroller mSlideScroller;

    public TestScroller(Context context) {
        super(context);
        createScroller();
    }

    public TestScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        createScroller();
    }

    public TestScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createScroller();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestScroller(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        createScroller();
    }


    private void createScroller()
    {
        mSlideScroller = new SlideScroller(this);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mSlideScroller.onInterceptTouchEvent(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mSlideScroller.onTouchEvent(ev)) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void draw(Canvas canvas) {
        mSlideScroller.draw(canvas);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                   int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return mSlideScroller.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY,
                maxOverScrollX, maxOverScrollY, isTouchEvent);
    }




    @Override
    public View getScrollableView() {
        return this;
    }

    @Override
    public int superComputeVerticalScrollExtent() {
//        return 0;
        return super.computeVerticalScrollExtent();
    }

    @Override
    public int superComputeVerticalScrollOffset() {
//        return 0;
        return super.computeVerticalScrollOffset();
    }


    @Override
    public int superComputeVerticalScrollRange() {
//        return 0;
        return super.computeVerticalScrollRange();
    }



    @Override
    public void superOnTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
    }

    @Override
    public void superDraw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public boolean superAwakenScrollBars() {
//        return false;
        return super.awakenScrollBars();
    }


    @Override
    public boolean superOverScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        return false;
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                maxOverScrollY, isTouchEvent);
    }
}
