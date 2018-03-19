package com.pinet.cdemounlink.nested;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.pinet.cdemounlink.R;

/**
 * Created by Administrator on 2017/11/16.
 */

public class NestParent extends LinearLayout implements NestedScrollingParent{

    private NestedScrollingParentHelper mParentHelper;
    private View mTopView;
    private View mCenterView;
    private ViewGroup mBottomView;
    private int mTopHeight;
    private OverScroller mScroller;


    public NestParent(Context context) {
        super(context);
        init(context);
    }

    public NestParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NestParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext)
    {
        setOrientation(LinearLayout.VERTICAL);
        mScroller = new OverScroller(mContext);
        mParentHelper =  new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = findViewById(R.id.nestparent_top);
        mCenterView = findViewById(R.id.nestparent_center);
        mBottomView = (ViewGroup) findViewById(R.id.nestparent_bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTopHeight = mTopView.getMeasuredHeight();
        ViewGroup.LayoutParams mBottomParams = mBottomView.getLayoutParams();
        mBottomParams.height = getMeasuredHeight() - mCenterView.getMeasuredHeight();
        mBottomView.setLayoutParams(mBottomParams);
    }

    @Override
    public void scrollTo(int x, int y) {
       // super.scrollTo(x, y);
        if(y<0)
        {
            y = 0;
        }
        if(y>mTopHeight)
        {
            y = mTopHeight;
        }
        super.scrollTo(x,y);
    }

    @Override
    public void computeScroll() {
       // super.computeScroll();
        if(mScroller.computeScrollOffset())
        {
            scrollTo(0,mScroller.getCurrY());
            invalidate();
        }
    }

    public void fling(int velocityY)
    {
        mScroller.fling(0,getScrollY(),0,velocityY,0,0,0,mTopHeight);
        invalidate();
    }


    //NetScrollParent---------------------


    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        //super.onNestedScrollAccepted(child, target, axes);
        mParentHelper.onNestedScrollAccepted(child,target,axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        //
       // super.onStartNestedScroll(child, target, nestedScrollAxes);
        return true;
    }


    @Override
    public void onStopNestedScroll(View child) {
        //super.onStopNestedScroll(child);
        mParentHelper.onStopNestedScroll(child);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //super.onNestedPreScroll(target, dx, dy, consumed);
        boolean hideTop = dy > 0 && getScrollY() < mTopHeight;
        boolean showTop = dy < 0 && getScrollY() >=0  && !ViewCompat.canScrollVertically(target,-1);
        if(hideTop || showTop)
        {
            scrollBy(0,dy);
            consumed[1] = dy ;
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }


    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        //super.onNestedPreFling(target, velocityX, velocityY);
        if(getScrollY() < mTopHeight)
        {
            fling((int) velocityY);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        //return super.onNestedFling(target, velocityX, velocityY, consumed);
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        //return super.getNestedScrollAxes();
        return mParentHelper.getNestedScrollAxes();
    }
}
