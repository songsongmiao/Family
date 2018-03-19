package com.pinet.cdemounlink;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.util.Measure;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/12/5.
 */

public class SlideText extends FrameLayout {

    private String TAG = "SlideText";

    private Context mContext;
    private Paint mPaint;
    private View viewOther;
    private View viewAnother;


    private int mHeight;
    private int mWidth;

    private Scroller mScroller;


    public SlideText(Context context) {
        super(context);
    }

    public SlideText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //AutoCompleteTextView text  = new AutoCompleteTextView(context);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                    mScroller.startScroll(0,0,0,mHeight,300);
                    invalidate();
                    mHandler.sendEmptyMessageDelayed(2,1000);
                    break;
                case 1:
                    mScroller.startScroll(0,0,0,mHeight,300);
                    invalidate();
                    //再次进行位置重置
                    mHandler.sendEmptyMessageDelayed(3,1000);

                    break;
                case 2:
                    //重置状态：
                    viewAnother.layout(0,0,mWidth,mHeight);
                    viewOther.layout(0,mHeight,mWidth,2* mHeight);
                    SlideText.this.scrollTo(0,0);
                  //  Log.e(TAG,"第一次位置变化 ");
                    mHandler.sendEmptyMessageDelayed(1,4000);
                    break;

                case 3:
                    viewOther.layout(0,0,mWidth,mHeight);
                    viewAnother.layout(0,mHeight,mWidth,2* mHeight);
                    SlideText.this.scrollTo(0,0);
                  //  Log.e(TAG,"第二次位置变化 ");
                    mHandler.sendEmptyMessageDelayed(0,4000);
                    break;
            }
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight =viewOther.getMeasuredHeight();
        mWidth = viewOther.getMeasuredWidth();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count  = getChildCount();
        if(count !=2)
        {
            throw new IllegalArgumentException("请创建两个切换的布局");
        }
        viewOther = getChildAt(0);
        viewAnother = getChildAt(1);
        mContext = getContext();

        mPaint = new Paint();
        mScroller = new Scroller(mContext,new AccelerateInterpolator());

        mHandler.sendEmptyMessageDelayed(0,4000);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //super.onLayout(changed, left, top, right, bottom);
        viewOther.layout(0,0,mWidth,mHeight);
        viewAnother.layout(0,mHeight,mWidth,2* mHeight);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
       if(mScroller.computeScrollOffset())
       {
           this.scrollTo(0,mScroller.getCurrY());
           invalidate();
       }
    }



}
