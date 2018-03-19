package com.pinet.cdemounlink.scroller;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/12/12.
 */

public interface Scrollable {

    public View getScrollableView();
    public int superComputeVerticalScrollExtent();

    public int superComputeVerticalScrollOffset();

    public int superComputeVerticalScrollRange();

    public void superOnTouchEvent(MotionEvent event);

    public void superDraw(Canvas canvas);

    public boolean superAwakenScrollBars();

    public boolean superOverScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                     int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent);
}
