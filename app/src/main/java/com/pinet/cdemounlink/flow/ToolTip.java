package com.pinet.cdemounlink.flow;

import android.graphics.Paint;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ToolTip extends DyInfo {

    public ToolTip()
    {

    }

    /**
     * 设置是方形不是椭圆形框，其中CAPROUNDRECT风格无边框功能
     * @param style 框显示风格
     */
    public void setInfoStyle(XEnum.DyInfoStyle style)
    {
        setStyle(style);
    }

    /**
     * 设置椭圆形边框半径
     * @param x		半径
     * @param y		半径
     */
    public void setRoundRadius(float x,float y)
    {
        setStyle(XEnum.DyInfoStyle.ROUNDRECT);
        setRoundRectX(x);
        setRoundRectY(y);
    }

    /**
     * 信息框显示在哪个位置
     * @param align	位置
     */
    public void setAlign(Paint.Align align)
    {
        mPositionAlign = align;
    }

    /**
     * 点击位置坐标
     * @param x		x坐标
     * @param y		y坐标
     */
    public void setCurrentXY(float x,float y)
    {
        setCenterXY(x,y);
    }

    /**
     * 增加提示信息
     * @param text	文本
     * @param paint	绘制画笔
     */
    public void addToolTip(String text,Paint paint)
    {
        addInfo(text,paint);
    }

    /**
     * 增加提示信息
     * @param dotStyle	图案风格
     * @param text		文本
     * @param paint		绘制画笔
     */
    public void addToolTip(PlotDot dotStyle,String text,Paint paint)
    {
        addInfo(dotStyle,text,paint);
    }


}
