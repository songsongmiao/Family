package com.pinet.cdemounlink.test;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Scroller;

import com.pinet.cdemounlink.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/13.
 */

public class CustomAnimation extends View {

    private final String TAG = "CustomAnimation";
    //小球半径
    private int radius=10;
    //保存小球移动过程中的坐标
    private PointF currentPointF;
    //高度和宽度
    private int halfHeight;
    private int width;

    private Paint mPaint;
    private Bitmap mBitmap;

    private Path mPath ;

    //private PathEvaluator mPathEvaluator;
    private PointF mStartPoint;

//    private List<PointF> mPointList = new ArrayList<>();

    public CustomAnimation(Context context)
    {
        super(context);
        init(context,null);

    }

    public CustomAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CustomAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomAnimation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }


    private void init(Context mContext,AttributeSet attrs)
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2f);
        mPaint.setDither(true);
        mPath= new Path();
        currentPointF = new PointF(200,80);
        startAnimator();

    }
    /**
     * 启动动画
     */
    private void startAnimator() {
        final PathEvaluator mPathEvaluator = new PathEvaluator();
        ValueAnimator mAnimator = ValueAnimator.ofObject(mPathEvaluator,new PointF(124,90),new PointF(124,90));
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPointF = (PointF) animation.getAnimatedValue();
                invalidate();
//                if(currentPointF.x != 0)
//                {
//                    Log.e("Custom","x : "+ mStartPoint.x+"\t y:"+ mStartPoint.y);
//                }
               //
            }
        });
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.setStartDelay(200);//推迟启动200毫秒
        mAnimator.setDuration(10 * 1000).start();//启动
        mPath.reset();
        mPath.moveTo(124,90);

      //  Log.e("Custom","x : "+ mStartPoint.x+"\t y:"+ mStartPoint.y);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(currentPointF.x,currentPointF.y,radius,mPaint);
      //
       // Log.e("Custom","x : "+ currentPointF.x+"\t y:"+ currentPointF.y);
//        mPath.lineTo(currentPointF.x,currentPointF.y);
//        canvas.drawPath(mPath,mPaint);
    }


}
