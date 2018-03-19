package com.pinet.cdemounlink.flow;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ToolTipRender extends ToolTip {

    public ToolTipRender()
    {

    }

    public void renderInfo(Canvas canvas)
    {
        drawInfo(canvas);
        clear();
    }

}
