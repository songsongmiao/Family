package com.pinet.cdemounlink.flow;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PlotGridRender extends PlotGrid {

    private boolean mMajorTickLine = false;
    private final int BLOB_WIDTH =  2;;

    public PlotGridRender()
    {
    }

    /**
     * 是否为主Tick对应的网格线,如果是,在划线时需加粗
     * @param primary 是否为主Tick如是则会加粗显示
     */
    public void setPrimaryTickLine(boolean primary)
    {
        mMajorTickLine = primary;
    }

    /**
     * 绘制奇数行填充
     * @param left		左边X坐标
     * @param top		顶上Y坐标
     * @param right		右边X坐标
     * @param bottom	底上Y坐标
     */
    public void renderOddRowsFill(Canvas canvas,
                                  float left,float top,float right,float bottom)
    {
        if(null != canvas && isShowOddRowBgColor())
        {
            canvas.drawRect( left,  bottom  ,right, top,getOddRowsBgColorPaint());
        }
    }

    /**
     * 绘制偶数行填充
     * @param left 		左边X坐标
     * @param top		顶上Y坐标
     * @param right		右边X坐标
     * @param bottom	 底上Y坐标
     */
    public void renderEvenRowsFill(Canvas canvas,
                                   float left,float top,float right,float bottom)
    {
        if(null != canvas && isShowEvenRowBgColor())
        {
            canvas.drawRect( left,  bottom  ,right, top,getEvenRowsBgColorPaint());
        }
    }



    /**
     * 绘制横向网格线
     * @param startX	起始点X坐标
     * @param startY	起始点Y坐标
     * @param stopX		终止点X坐标
     * @param stopY		终止点Y坐标
     */
    public void renderGridLinesHorizontal(Canvas canvas,
                                          float startX,float startY,float stopX,float stopY)
    {
        if(null != canvas &&this.isShowHorizontalLines())
        {
            float initWidth = 0.0f;
            if(mMajorTickLine)
            {
                initWidth = getHorizontalLinePaint().getStrokeWidth() ;
                getHorizontalLinePaint().setStrokeWidth( initWidth + BLOB_WIDTH );
            }

            DrawHelper.getInstance().drawLine(this.getHorizontalLineStyle(),
                    startX, startY, stopX, stopY,
                    canvas, getHorizontalLinePaint());

            if(mMajorTickLine)getHorizontalLinePaint().setStrokeWidth(initWidth);
        }
    }

    /**
     * 绘制竖向网格线
     * @param startX	起始点X坐标
     * @param startY	起始点Y坐标
     * @param stopX		终止点X坐标
     * @param stopY		终止点Y坐标
     */
    public void renderGridLinesVertical(Canvas canvas,
                                        float startX,float startY,float stopX,float stopY)
    {
        if(null != canvas && isShowVerticalLines())
        {
            float initWidth = 0.0f ;
            if(mMajorTickLine) //主线
            {
                initWidth = getVerticalLinePaint().getStrokeWidth() ;
                getVerticalLinePaint().setStrokeWidth( initWidth + BLOB_WIDTH );
            }

            //默认是minor
            DrawHelper.getInstance().drawLine(this.getVerticalLineStyle(),
                    startX, startY, stopX, stopY,
                    canvas, getVerticalLinePaint());

            if(mMajorTickLine)getVerticalLinePaint().setStrokeWidth(initWidth);

        }
    }


}
