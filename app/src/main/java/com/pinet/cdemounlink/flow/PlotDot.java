package com.pinet.cdemounlink.flow;

import android.graphics.Color;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PlotDot {

    //private Paint mPaintDot = null;

    private int mColor = Color.BLACK;
    private int mRingInnerColor = Color.WHITE;
    private int mRing2InnerColor = Color.RED;

    //Triangle 三角
    //线上的点为圆或角
    protected XEnum.DotStyle mDotStyle = XEnum.DotStyle.DOT;

    private float mRadius = 10.0f;

    private int mAlpha = 255;

    public PlotDot()
    {
    }

    /**
     * 设置颜色
     * @param color	颜色
     */
    public void setColor(int color)
    {
        mColor = color;
    }

    /**
     * 返回颜色
     * @return 颜色
     */
    public int getColor()
    {
        return mColor;
    }

    /**
     * 设置点形状为环形时，内部所填充的颜色.仅对环形有效
     * @param color 内部填充颜色
     */
    public void setRingInnerColor(int color)
    {
        mRingInnerColor = color;
    }

    /**
     * 设置点形状为环形时，最内部所填充的颜色.仅对环形有效
     * @param color 最内部填充颜色
     */
    public void setRing2InnerColor(int color)
    {
        mRing2InnerColor = color;
    }


    /**
     * 设置当前环形点内部填充颜色
     * @return 内部填充颜色
     */
    public int getRingInnerColor()
    {
        return mRingInnerColor;
    }

    /**
     * 设置当前环形点最内部填充颜色
     * @return 内部填充颜色
     */
    public int getRing2InnerColor()
    {
        return mRing2InnerColor;
    }


    /**
     * 设置点的显示风格
     * @param style 显示风格
     */
    public void setDotStyle( XEnum.DotStyle style)
    {
        mDotStyle = style;
    }

    /**
     * 返回点的显示风格
     * @return 显示风格
     */
    public XEnum.DotStyle getDotStyle()
    {
        return mDotStyle;
    }

    /**
     * 设置点的绘制半径大小，会依此指定的半径来绘制相关图形
     * @param radius 半径
     */
    public void setDotRadius(float radius)
    {
        mRadius = radius;
    }

    /**
     * 返回点的绘制半径大小
     * @return 半径
     */
    public float getDotRadius()
    {
        return mRadius;
    }

    /**
     * 设置透明度
     * @param alpha 透明度
     */
    public void setAlpah(int alpha)
    {
        mAlpha = alpha;
    }

    /**
     * 返回当前透明度
     * @return 透明度
     */
    public int getAlpha()
    {
        return mAlpha;
    }


}
