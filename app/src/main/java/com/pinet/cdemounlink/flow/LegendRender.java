package com.pinet.cdemounlink.flow;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2017/11/29.
 */

public class LegendRender extends Legend {


    public LegendRender()
    {

    }

    public void setPlotWH(float width,float height)
    {
        setCenterXY(width * mXPercentage,height * mYPercentage);
    }

    public void renderInfo(Canvas canvas)
    {
        drawInfo(canvas);
    }
}
