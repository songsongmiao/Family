package com.pinet.cdemounlink.flow;

import android.graphics.RectF;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PlotBarPosition extends RectPosition {

    public PlotBarPosition()
    {
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


    public void savePlotRectF(float left,float top,float right,float bottom)
    {
        saveRectF(left, top, right, bottom);
    }

    public void savePlotRectF(RectF r)
    {
        saveRectF(r);
    }

    public  boolean compareF(float x, float y)
    {
        // TODO Auto-generated method stub

        return compareRange(x,y);
    }


}
