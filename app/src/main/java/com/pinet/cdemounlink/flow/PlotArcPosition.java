package com.pinet.cdemounlink.flow;

import android.graphics.PointF;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PlotArcPosition extends ArcPosition {
    public PlotArcPosition()
    {
    }

    public void saveAngle(float radius,float offsetAngle,
                          float currentAngle,float selectedOffset)
    {
        mRadius = radius;
        mOffsetAngle = offsetAngle;
        mCurrentAngle = currentAngle;
        mSelectedOffset = selectedOffset;
    }

    //当前记录在数据源中行号
    public void savePlotDataID(int num)
    {
        saveDataID(num);
    }

    //当前记录所属数据集的行号
    public void savePlotDataChildID(int num)
    {
        saveDataChildID(num);
    }


    public void savePlotCirXY(float x,float y)
    {
        if(null == mCirXY)
            mCirXY = new PointF();

        mCirXY.x =  x;
        mCirXY.y =  y;
    }


    public boolean compareF(float x, float y)
    {
        return compareRange(x,y);
    }


}
