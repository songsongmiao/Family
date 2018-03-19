package com.pinet.cdemounlink.flow;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/29.
 */

public class LabelBrokenLine {

    private  static final  String TAG = "LabelBrokenLine";

    private XEnum.LabelLinePoint mLinePoint = XEnum.LabelLinePoint.ALL;
    private float mRadius = 5.f;

    //当标签为Line类型时使用
    private Paint mPaintLabelLine = null;

    //画点
    private Paint mPaintPoint = null;

    //标签与点的转折线长度
    private float mLabelBrokenLineLength = 30.f;

    //BEZIERCURVE 贝塞尔曲线
    protected boolean mIsBZLine = false;

    //折线起始点(1 - 10)
    protected float mBrokenStartPoint =  3.f;

    public LabelBrokenLine()
    {
    }

    /**
     * 设置显示线条为贝塞尔曲线
     */
    public void isBZLine()
    {
        mIsBZLine = true;
    }

    /**
     * 设置显示线条为普通直线
     */
    public void isBeeLine()
    {
        mIsBZLine = false;
    }

    /**
     * 设置在线上点的风格
     * @param style 风格
     */
    public void setLinePointStyle(XEnum.LabelLinePoint style)
    {
        mLinePoint = style;
    }

    /**
     * 返回当前线上点的风格
     * @return 风格
     */
    public XEnum.LabelLinePoint getLinePointStyle()
    {
        return mLinePoint;
    }

    /**
     * 设置点的半径
     * @param radius	半径
     */
    public void setRadius(float radius)
    {
        mRadius = radius;
    }

    /**
     * 返回点的半径
     * @return 半径
     */
    public float getRadius()
    {
        return mRadius;
    }

    /**
     * 设置折线长度
     * @param len	长度
     */
    public void setBrokenLine(float len)
    {
        mLabelBrokenLineLength = len;
    }

    /**
     * 返回折线长度
     * @return	长度
     */
    public float getBrokenLine()
    {
        return mLabelBrokenLineLength;
    }


    /**
     * 开放标签线画笔(当标签为Line类型时有效)
     * @return 画笔
     */
    public Paint getLabelLinePaint()
    {
        if(null == mPaintLabelLine)
        {
            mPaintLabelLine = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaintLabelLine.setColor(Color.BLACK);
            mPaintLabelLine.setStrokeWidth(2);
        }
        return mPaintLabelLine;
    }

    /**
     * 返回折线点画笔
     * @return	画笔
     */
    public Paint getPointPaint()
    {
        if(null == mPaintPoint)
        {
            mPaintPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        return mPaintPoint;
    }


    /**
     * 折线转折起始点(1 - 10)比例
     * @param ratio  比例(1-10)
     */
    public void setBrokenStartPoint(float ratio)
    {
        if(Float.compare(ratio, 1) == -1 || Float.compare(ratio, 10) == 1)
        {
            Log.e(TAG,"值必须在1到10范围内.");
            return ;
        }else{
            mBrokenStartPoint = ratio;
        }
    }


}
