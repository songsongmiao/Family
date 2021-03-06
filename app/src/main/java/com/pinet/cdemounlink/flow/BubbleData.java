package com.pinet.cdemounlink.flow;

import android.graphics.Paint;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class BubbleData {

    //线上每个点的值
    private List<PointD> mPointMap ;
    private List<Double> mBubble ;

    //标签画笔
    private Paint mLabelPaint = null;

    //标签轴用的到值
    private String mLabel = "";
    //是否在点上显示标签
    private boolean mLabelVisible = false;
    //气泡颜色
    private int mColor = 0;
    private int mBorderColor = -1;

    //标签文字旋转角度
    private float mItemLabelRotateAngle = 0.0f;


    public BubbleData(){}

    /**
     * 气泡数据
     * @param key			对应的键值
     * @param dataSeries	对应的数据序列
     * @param bubble		气泡大小
     * @param color			气泡颜色
     */
    public BubbleData(String key,
                      List<PointD> dataSeries,
                      List<Double> bubble,
                      int color) {
        // TODO Auto-generated constructor stub

        setKey(key);
        setDataSet(dataSeries);
        setBubble(bubble);
        setColor(color);
    }


    /**
     * 设置绘制线的数据序列,由x与y坐标构建
     * @param dataSeries <X坐标值，Y坐标值>
     */
    public void setDataSet( List<PointD>  dataSeries)
    {
        mPointMap = dataSeries;
    }

    /**
     * 返回绘制线的数据序列
     * @return 线的数据序列<X坐标值，Y坐标值>
     */
    public List<PointD> getDataSet() {
        return mPointMap;
    }


    /**
     * 设置气泡数据集
     * @param bubble
     */
    public void setBubble(List<Double>   bubble)
    {
        mBubble = bubble;
    }

    /**
     * 返回气泡数据集
     * @return 数据集
     */
    public List<Double> getBubble()
    {
        return mBubble;
    }


    /**
     * 设置是否在线上显示标签
     * @param visible 是否显示
     */
    public void setLabelVisible(boolean visible)
    {
        mLabelVisible = visible;
    }

    /**
     * 返回是否在线上显示标签
     * @return 是否显示
     */
    public boolean getLabelVisible()
    {
        return mLabelVisible;
    }

    /**
     * 设置标签
     * @param value 标签内容
     */
    public void setLabel(String value)
    {
        mLabel = value;
    }

    /**
     * 返回标签
     * @return 标签
     */
    public String getLabel() {
        return mLabel;
    }


    /**
     * 设置当前记录的Key值
     * @param value Key值
     */
    public void setKey(String value)
    {
        mLabel = value;
    }

    /**
     * 返回Key值
     * @return Key值
     */
    public String getKey() {
        return mLabel;
    }

    /**
     * 开放标签画笔
     * @return 画笔
     */
    public Paint getDotLabelPaint()
    {
        if(null == mLabelPaint)
            mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        return mLabelPaint;
    }

    /**
     * 设置气泡颜色
     * @param color 颜色
     */
    public void setColor(int color)
    {
        mColor = color;
    }

    /**
     * 得到气泡颜色
     * @return 颜色
     */
    public int getColor()
    {
        return mColor;
    }

    /**
     * 设置气泡边框颜色
     * @param color 颜色
     */
    public void setBorderColor(int color)
    {
        mBorderColor = color;
    }

    /**
     * 得到气泡边框颜色
     * @return 颜色
     */
    public int getBorderColor()
    {
        return mBorderColor;
    }

    /**
     * 返回标签在显示时的旋转角度
     * @return 旋转角度
     */
    public float getItemLabelRotateAngle() {
        return mItemLabelRotateAngle;
    }

    /**
     * 设置标签在显示时的旋转角度
     * @param rotateAngle 旋转角度
     */
    public void setItemLabelRotateAngle(float rotateAngle) {
        this.mItemLabelRotateAngle = rotateAngle;
    }


}
