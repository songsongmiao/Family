package com.pinet.cdemounlink.flow;

import android.graphics.PointF;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PointPosition extends RectPosition {
    protected PointF mPoint = null;

    public PointPosition()
    {
    }

    public PointF getPosition()
    {
        return mPoint;
    }


    public String getPointInfo()
    {
        if(null == mPoint)return "";
        String info = "x:"+Float.toString(mPoint.x)+" y:"+Float.toString(mPoint.y);
        return info;
    }


}
