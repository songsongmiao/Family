package com.pinet.cdemounlink;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Administrator on 2017/11/27.
 */

public class CircleView  extends View{

    private Drawable mMaskDrawLayer;
    private Drawable mBackLayer;

    private Drawable mHeader;

    private int mHeight;
    private int mWidth;
    private Paint mPaint;

    private Paint mDstPaint;
    private Paint mSrcPaint;

    private Path path;

    public CircleView(Context context) {
     //   super(context);
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
        //super(context, attrs);
        init(context,attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);

    }

    private void init(Context mContext,AttributeSet attrs)
    {

        mDstPaint = new Paint();
        mSrcPaint = new Paint();
        mDstPaint.setColor(Color.YELLOW);
        mSrcPaint.setColor(Color.BLUE);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        mBackLayer =  getResources().getDrawable(R.mipmap.switch_backgroud);
        mMaskDrawLayer = getResources().getDrawable(R.mipmap.switch_masker);
        mWidth = mMaskDrawLayer.getIntrinsicWidth();
        mHeight = mMaskDrawLayer.getIntrinsicHeight();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setDither(true);
        mDstPaint.setColor(Color.YELLOW);
        mDstPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setDither(true);
        mSrcPaint.setColor(Color.BLUE);
        mSrcPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mHeader = getResources().getDrawable(R.mipmap.timg);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        path = new Path();
//        RectF mMaskRect = new RectF(40,40,140,140);
//        RectF mBackRect = new RectF(60,60,160,160);
//        BitmapDrawable mBitmapLayer = (BitmapDrawable) mBackLayer;
//        BitmapDrawable mBitmapBack = (BitmapDrawable) mMaskDrawLayer;
//        canvas.drawColor(Color.GREEN);
//        canvas.translate(40, 40);
//        canvas.drawBitmap(mBitmapLayer.getBitmap(), 0, 0, mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
//        canvas.drawBitmap(mBitmapBack.getBitmap(), 0, 0, mPaint);
//        mPaint.setXfermode(null);
//        canvas.restore();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.RED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            path.addArc(100, 100, 500, 500, -225, 225);
//            path.arcTo(200, 100, 800, 500, -180, 225, false);
            path.addArc(200, 200, 400, 400, -225, 225);//画弧
            path.arcTo(400, 200, 600, 400, -180, 225, false);//三阶贝塞尔曲线
            path.lineTo(400, 550);//连接
        }
        BitmapDrawable mHeaderDrawable  = (BitmapDrawable) mHeader;
        Bitmap mHeaderBitmap = mHeaderDrawable.getBitmap();
        int saveLayer = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawPath(path, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mHeaderBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);



    }






}
