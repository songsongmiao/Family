package com.pinet.cdemounlink.flow;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Administrator on 2017/11/29.
 */

public class DyLine {

    private Paint mPaintLine = null;
    protected PointF mCenterXY = null;
    //设置交叉线显示风格
    private XEnum.DyLineStyle mDyLineStyle = XEnum.DyLineStyle.Cross;

    //线绘制为实线，虚实线 	等哪种风格
    private XEnum.LineStyle mLineDrawStyle = XEnum.LineStyle.SOLID;

    private float mOldX = 0.0f,mOldY =0.0f;

    public DyLine()
    {

    }

    /**
     * 线的画笔
     * @return
     */
    public Paint getLinePaint()
    {
        if(null == mPaintLine)
        {
            mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
            //mPaintLine.setStyle(Style.STROKE);
            mPaintLine.setColor(Color.rgb(215, 10, 10)); //50, 165, 238));
        }

        return mPaintLine;
    }

    /**
     * 点击位置坐标
     * @param x		x坐标
     * @param y		y坐标
     */
    public void setCurrentXY(float x,float y) //setCurrentXY
    {
        if(null == mCenterXY) mCenterXY = new PointF();
        mCenterXY.x = x;
        mCenterXY.y = y;
    }

    /**
     * 是否需要重绘
     * @return 是否允许重绘
     */
    public boolean isInvalidate()
    {
        if(null == mCenterXY) return false;
        if(Float.compare(Math.abs(mCenterXY.x - mOldX ) , 5.f ) == 1
                || Float.compare(Math.abs(mCenterXY.y - mOldY), 5.f) == 1)
        {
            mOldX = mCenterXY.x;
            mOldY = mCenterXY.y;
            return true;
        }
        return false;
    }


    /**
     * 设置线的风格
     * @param style 线的交叉风格
     */
    public void setDyLineStyle(XEnum.DyLineStyle style)
    {
        mDyLineStyle = style;
    }

    /**
     * 返回线的风格
     * @return  线的交叉风格
     */
    public XEnum.DyLineStyle getDyLineStyle()
    {
        return mDyLineStyle;
    }


    /**
     * 设置线绘制为实线，虚实线 	等哪种风格
     * @param style 线的类型
     */
    public void setLineDrawStyle(XEnum.LineStyle style)
    {
        mLineDrawStyle = style;
    }

    /**
     * 返回线的绘制类型(实线，虚实线等)
     * @return 线的绘制类型
     */
    public XEnum.LineStyle getLineDrawStyle()
    {
        return mLineDrawStyle;
    }



}
