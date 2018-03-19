package com.pinet.cdemounlink.flow;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PlotTitleRender extends PlotTitle {


    //protected static final int mBorderSpadding = 5;

    public PlotTitleRender()
    {

    }

    /**
     * 绘制标题
     */
    public void renderTitle(float chartLeft,
                            float chartRight,
                            float chartTop,
                            float chartWidth,
                            float plotTop,
                            Canvas canvas)
    {
        //排除掉border width

        String title = getTitle();
        String subTitle = getSubtitle();

        float titleHeight = 0.f;
        float subtitleHeight = 0.f;
        float totalHeight = 0.f;

        float titleInitY = 0.0f;

        float titleX = 0.0f;
        float titleY = 0.0f;

        float subtitleX = 0.0f;
        float subtitleY = 0.0f;

        if(title.length() == 0 && subTitle.length() == 0) return;

        if(title.length() > 0 )
        {
            titleHeight = DrawHelper.getInstance().getPaintFontHeight(getTitlePaint());
        }
        if(title.length() > 0 )
        {
            subtitleHeight = DrawHelper.getInstance().getPaintFontHeight(getSubtitlePaint());
        }
        totalHeight = titleHeight + subtitleHeight;
        float pcHeight = Math.abs(plotTop - chartTop) ;

        //用来确定 titleY,需要Chart top的值
        switch(this.getVerticalAlign())
        {
            case TOP:
                titleInitY = chartTop + titleHeight;
                break;
            case MIDDLE:
                titleInitY = Math.round(chartTop + pcHeight/2 - totalHeight/2);
                break;
            case BOTTOM:
                titleInitY = plotTop - titleHeight;
                break;
        }


        switch(this.getTitleAlign())
        {
            case LEFT:
                titleX = chartLeft;
                titleY = titleInitY;

                subtitleX = chartLeft;
                subtitleY = titleY + subtitleHeight;

                getTitlePaint().setTextAlign(Paint.Align.LEFT);
                getSubtitlePaint().setTextAlign(Paint.Align.LEFT);
                break;
            case CENTER:

                titleX = Math.round(chartLeft + chartWidth / 2);
                titleY = titleInitY;

                getTitlePaint().setTextAlign(Paint.Align.CENTER);
                getSubtitlePaint().setTextAlign(Paint.Align.CENTER);
                break;
            case RIGHT:

                titleX = chartRight;
                titleY = titleInitY;

                getTitlePaint().setTextAlign(Paint.Align.RIGHT);
                getSubtitlePaint().setTextAlign(Paint.Align.RIGHT);
                break;
        }

        subtitleY = DrawHelper.getInstance().drawText(canvas, this.getTitlePaint(), title, titleX, titleY);
        subtitleX = titleX;
        subtitleY = titleY + subtitleHeight;

        DrawHelper.getInstance().drawText(canvas, this.getSubtitlePaint(), subTitle, subtitleX, subtitleY);

    }

}
