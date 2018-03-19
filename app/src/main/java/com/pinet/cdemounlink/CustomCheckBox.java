package com.pinet.cdemounlink;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.util.Measure;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.transition.Slide;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.CompoundButton;
import android.widget.Scroller;

import java.util.jar.Attributes;

/**
 * Created by Administrator on 2017/11/22.
 * 单选项
 */

public class CustomCheckBox extends CompoundButton {

    private final String TAG = "CustomCheckBox";


    private static final int TOUCH_MODE_IDLE = 0;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private Drawable mSlideDrawable;
    private Drawable mBackgroundDrawable;
    private Drawable mMaskLayer;
    private Drawable mStateDrawable;

    private Rect backRect ;

    /***
     *
     */
    private int mWidth;
    /***
     * 控件的宽度
     */
    private int mMaxWidth;
    private int mHeight;
    /***
     * 滑动的距离
     */
    private int mSlideWidth ;
    private int mMinSlide;

    private Context mContext;

    private Paint mPaint ;


    private Handler mHandler = new Handler();

    private PorterDuffXfermode mDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    private int mMinSize = 0;

    private int mTempDistance;

    private SlideMaskLayer mSlideScroller;

    public CustomCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        //super(context, attrs, defStyleAttr);
        this(context,attrs);
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context mContext, AttributeSet attrs)
    {
        this.mContext = mContext;
        TypedArray mTypeArray = mContext.obtainStyledAttributes(attrs,R.styleable.Custom);
        mSlideDrawable = mTypeArray.getDrawable(R.styleable.Custom_slide_image);
        mBackgroundDrawable = mTypeArray.getDrawable(R.styleable.Custom_background_image);
        mMaskLayer = mTypeArray.getDrawable(R.styleable.Custom_masker_image);
        mStateDrawable = mTypeArray.getDrawable(R.styleable.Custom_black_image);

        mTypeArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if(mSlideDrawable == null ||  mBackgroundDrawable== null || mMaskLayer== null)
        {
            throw new IllegalArgumentException("Please set attribute : slide_image、background_image、masker_image");
        }
        mSlideDrawable.setBounds(0,0,this.mSlideDrawable.getIntrinsicWidth(),this.mSlideDrawable.getIntrinsicHeight());
        mSlideDrawable.setCallback(this);
        mMaskLayer.setBounds(0,0,this.mMaskLayer.getIntrinsicWidth(),this.mMaskLayer.getIntrinsicHeight());
        mMaskLayer.setCallback(this);
        mBackgroundDrawable.setBounds(0,0,mBackgroundDrawable.getIntrinsicWidth(),this.mBackgroundDrawable.getIntrinsicHeight());
        mBackgroundDrawable.setCallback(this);


        mHeight = mBackgroundDrawable.getIntrinsicHeight();
        mSlideWidth = (mBackgroundDrawable.getIntrinsicWidth() - mHeight)/2;
        mMaxWidth = mBackgroundDrawable.getIntrinsicWidth();
        mWidth = mSlideWidth + mHeight;
        ViewConfiguration config = ViewConfiguration.get(getContext());
        mMinSize = config.getScaledTouchSlop();
        mMinSlide = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4f,mContext.getResources().getDisplayMetrics());

        backRect = new Rect(0,0,mWidth,mHeight);


        //创建滚动控件
        mSlideScroller = new SlideMaskLayer(mContext,new AccelerateInterpolator());
    }





    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       setMeasuredDimension(mWidth,mHeight);
    }


       @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawCheck(canvas);
    }


    private void onDrawCheck(Canvas canvas)
    {
        BitmapDrawable mBitmapDrawableBackground = (BitmapDrawable) mBackgroundDrawable;
        BitmapDrawable mBitmapDrawableMaskLayer = (BitmapDrawable) mMaskLayer;
        BitmapDrawable mBitmapDrawableSlide = (BitmapDrawable) mSlideDrawable;
        BitmapDrawable mBitmapDrawableBlack = (BitmapDrawable) mStateDrawable;
        Bitmap mBackGroundBitmap = mBitmapDrawableBackground.getBitmap();
        Bitmap mMaskLayerBitmap = mBitmapDrawableMaskLayer.getBitmap();
        Bitmap mSlideBitmap = mBitmapDrawableSlide.getBitmap();
        Bitmap mBlackBitmap = mBitmapDrawableBlack.getBitmap();

        canvas.save();
        if(mMaskLayer != null && mBlackBitmap != null && !mBlackBitmap.isRecycled())
        {
            int src = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
            // 绘制遮罩层
            //mStateDrawable.draw(canvas);
            canvas.drawBitmap(mBlackBitmap, 0, 0, mPaint);
            // 绘制状态图片按并应用遮罩效果
            mPaint.setXfermode(mDuffXfermode);
            canvas.drawBitmap(mBackGroundBitmap, mTempDistance, 0, mPaint);
            mPaint.setXfermode(null);
            // 融合图层
            canvas.restoreToCount(src);
        }

        //绘制滑块
        canvas.drawBitmap(mSlideBitmap, mTempDistance, 0, mPaint);
        //绘制边框
        canvas.drawBitmap(mMaskLayerBitmap, 0, 0, mPaint);


        canvas.restore();
    }

    private float touchDownX;

    float x2 = 0;
    float y2 = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownX = event.getX();

                return true;//消费触摸事件
            case MotionEvent.ACTION_MOVE:
                float tempX = event.getX() - touchDownX;
                this.mTempDistance = (isChecked()? -mSlideWidth:  0) + (int) tempX;
                if(mTempDistance > 0 )
                {
                    mTempDistance = 0;
                }
                else if(mTempDistance < -mSlideWidth)
                {
                    mTempDistance = -mSlideWidth;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:;
                x2 = event.getX();
                y2 = event.getY();
                if(Math.abs(x2 - touchDownX) > mSlideWidth/2 )
                {
                    toggle();
                    mSlideScroller.startScroll(isChecked());//开始滑动
                }
                else if(Math.abs(x2 - touchDownX) > 5)
                {
                    mSlideScroller.startScroll(isChecked());//开始滑动

                }
                else
                {
                    toggle();
                    mSlideScroller.startScroll(isChecked());//开始滑动
                }

                break;
        }

        return super.onTouchEvent(event);
    }


    public float dip2px(Context mContext, int value)
    {
        return Resources.getSystem().getDisplayMetrics().density* value+ 0.5f;
    }

    public void setSlideX(int slideX)
    {
        if(slideX > 0 )
        {
            slideX = 0;

        }
        if(slideX < - mSlideWidth)
        {
            slideX = -mSlideWidth;
        }

        this.mTempDistance = slideX;
    }


    private class SlideMaskLayer implements Runnable{

        private Scroller mScroller ;

        public Scroller getScroller()
        {
           return mScroller;
        }

        public SlideMaskLayer(Context mContext,Interpolator mIntepolater)
        {
            mScroller = new Scroller(mContext,mIntepolater);
        }

        public void startScroll(boolean isCheck)
        {
            mScroller.startScroll(mTempDistance,0,isCheck? -mSlideWidth: mSlideWidth,500);
            post(this);
            invalidate();
        }


        @Override
        public void run() {
            if(mScroller.computeScrollOffset())
            {
                setSlideX(mScroller.getCurrX());
                invalidate();
                post(this);
            }
        }
    }


}
