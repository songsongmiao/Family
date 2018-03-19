package com.pinet.cdemounlink.test;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.pinet.cdemounlink.R;



/**
 * Created by Administrator on 2017/12/15.
 */

public class CustomObjectAnimation extends FrameLayout {

    private final String TAG = "CustomObject";

    private Drawable mDrawable;
    private ObjectAnimator mObjectAnimator;

    private int mWidth;
    private int mHeight;
    private PointF mPointF;
    private Paint mPaint;

    private ImageView mImageView;


    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    };

    public CustomObjectAnimation(Context context) {
        super(context);
        init(context,null);
    }

    public CustomObjectAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CustomObjectAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomObjectAnimation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context mContext,AttributeSet attributeSet)
    {
        if(attributeSet == null)
        {
            mDrawable = mContext.getResources().getDrawable(R.mipmap.timg);
        }
        else
        {
            TypedArray mArray = mContext.obtainStyledAttributes(attributeSet,R.styleable.CustomObjectAnimator);
            mDrawable = mArray.getDrawable(R.styleable.CustomObjectAnimator_anim_image);
            mArray.recycle();
        }
        mPointF = new PointF();

        mPaint = new Paint();

        mImageView = new ImageView(mContext);
        mImageView.setImageDrawable(mDrawable);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //setScaleType
        this.addView(mImageView);

       mHandler.postDelayed(new Runnable() {
           @Override
           public void run() {
               startAnimation();

           }
       },1000);
    }

    private void startAnimation()
    {
        //Log.e(TAG,"startAnimation");
//        Toast.makeText(getContext(),"startAnimation",Toast.LENGTH_SHORT).show();
        if(mDrawable == null)
        {
            Toast.makeText(getContext(),"mDrawable is null",Toast.LENGTH_SHORT).show();
            return ;
        }
        int position = mWidth /2;
        int offset = dptopix(4);
        int rightBounds = offset;
//        mObjectAnimator = ObjectAnimator.ofObject(mDrawable,"translationX",new CustomEvaluate(),0,0);
        AnimatorSet mAnimator = new AnimatorSet();
        mAnimator.playTogether(mObjectAnimator);
        mAnimator.playSequentially(mObjectAnimator);
        mObjectAnimator = ObjectAnimator.ofFloat(mImageView, "translationX", 0, 0,rightBounds,0,rightBounds,0,rightBounds,0,rightBounds,0,rightBounds,0,rightBounds,0,rightBounds, rightBounds/2);
        mObjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
      //  mObjectAnimator.setEvaluator(new CustomTrackEvaluate());
        mObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //mPointF = (PointF) animation.getAnimatedValue();
                invalidate();
            }
        });
        mObjectAnimator.setDuration(2000);
//        mObjectAnimator.setRepeatMode(RESTART);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        mObjectAnimator.setRepeatMode(ValueAnimator.REVERSE);//
        mObjectAnimator.start();
    }

    private void cancelAnimation()
    {
        if(mObjectAnimator == null)
        {
            return ;
        }
        mObjectAnimator.cancel();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mPointF.set(mWidth/2,mHeight/2);
        int offset = dptopix(4);

        LayoutParams mParams = new LayoutParams(mWidth - 2 * offset,mHeight);
        mParams.setMargins(offset,0,offset,0);
        mImageView.setLayoutParams(mParams);
        Log.e("CustomObject","size ： "+ mWidth + "\t "+ mHeight);

    }

    private boolean isStart = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(isStart)
        {
            startAnimation();
        }
        else
        {
            cancelAnimation();
        }
        isStart = !isStart;

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         if(mDrawable == null)
         {
             return ;
         }
        BitmapDrawable mBitmapDrawable = (BitmapDrawable) mDrawable;
        Rect mRect = new Rect(0,0,mWidth,mHeight);
        mBitmapDrawable.setBounds(mRect);

        RectF mRectf = new RectF(0,0,mWidth,mHeight);
//        canvas.drawBitmap(mBitmapDrawable.getBitmap(),null,mRectf,null);
//        canvas.drawBitmap(mBitmapDrawable.getBitmap(),mPointF.x,0,mPaint);

    }


    private class CustomEvaluate implements TypeEvaluator<PointF>{

        private PointF mPoint;

        public CustomEvaluate()
        {
            mPoint = new PointF();
        }


        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            if(fraction == 0 )
            {
                mPoint.x = (float) (60 * Math.cos(0));
            }
            else
            {
                int degree = (int) (fraction * 360* 4);
                Log.e(TAG,"CustomEvaluate "+ degree );
                mPoint.x = (int) (60 * Math.cos(degree));
            }
            mPoint.y = 0;


            return mPoint;
        }
    }


    private class CustomTrackEvaluate implements TypeEvaluator<Float>{



        public CustomTrackEvaluate()
        {

        }



        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {

//            float x;
//            float y;
//            if (fraction == 0) {
//                x = startValue.x;
//            } else {
//                x = fraction * endValue.x;
//            }
//            int radius = (int) Math.sqrt((startValue.x - 200)* (startValue.x - 200) + (startValue.y - 200)* (startValue.y - 200));
//            x = (float) ((fraction * 30 + 30) * Math.cos(15* fraction + 30 ))+ radius ;
//            y = (float) ((fraction * 30 + 30)* Math.sin(15 * fraction + 30)) + radius;
//        Log.e("evaluator","fraction: "+ fraction);
//            pointF.x = x;
//            pointF.y = y;
//            return pointF;
            float track = 0;
            int tempFraction = (int) (100* fraction);

            if(fraction == 0 )
            {
                track = 0f;
            }
            else if(fraction == 1f)
            {
                track = 1f;
            }
            else
            {
                if(tempFraction %5 == 0 )
                {
                    return -6f;
                }
                else
                {
                    return  6f;
                }
            }
            return track;
//            int degree = (int) (fraction * 360* 4);
//            track = (float) (6*Math.cos(degree));
//            return track;
        }
    }



    public int dptopix(int value)
    {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return (int) (value * density + 0.5f);
    }








}
