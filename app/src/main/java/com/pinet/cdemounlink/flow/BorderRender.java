package com.pinet.cdemounlink.flow;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by Administrator on 2017/11/29.
 */

public class BorderRender  extends Border  {

    private RectF mRect = new RectF();
    private Path mPath = null;


    public BorderRender()
    {
    }

    /**
     * 边框默认内边距
     * @return 内边距
     */
    public int getBorderSpadding()
    {
        return mBorderSpadding;
    }

    private void setPaintLineStyle()
    {
        switch(getBorderLineStyle())
        {
            case SOLID:
                break;
            case DOT:
                getLinePaint().setPathEffect(DrawHelper.getInstance().getDotLineStyle());
                break;
            case DASH:
                //虚实线
                getLinePaint().setPathEffect(DrawHelper.getInstance().getDashLineStyle());
                break;
        }
    }


    public void renderRect(Canvas canvas, RectF rect,
                           boolean showBoxBorder, boolean showBackground)
    {
        setPaintLineStyle();
        switch(getBorderRectType())
        {
            case RECT:
                if(showBackground)
                    canvas.drawRect(rect, getBackgroundPaint());

                if(showBoxBorder)
                    canvas.drawRect(rect, getLinePaint());
                break;
            case ROUNDRECT:
                if(showBackground)
                    canvas.drawRoundRect(rect, getRoundRadius(),
                            getRoundRadius(), getBackgroundPaint());
                if(showBoxBorder)
                    canvas.drawRoundRect(rect, getRoundRadius(),
                            getRoundRadius(), getLinePaint());
                break;
        }
    }


    public void renderCapRect(Canvas canvas,RectF rect,float capHeight,
                              boolean showBoxBorder,boolean showBackground)
    {

        setPaintLineStyle();

        if(null == mPath) mPath = new Path();

        float centerX = rect.left + rect.width() * 0.5f;
        float AngleH = capHeight;
        float AngleY = rect.bottom;

        mPath.moveTo(rect.left, rect.bottom);
        mPath.lineTo(rect.left, rect.top);
        mPath.lineTo(rect.right, rect.top);
        mPath.lineTo(rect.right, rect.bottom);
        mPath.lineTo( centerX + AngleH, AngleY);
        mPath.lineTo( centerX , AngleY + AngleH );
        mPath.lineTo( centerX - AngleH, AngleY);
        mPath.close();
        if(showBackground)
            canvas.drawPath(mPath, getBackgroundPaint());
        if(showBoxBorder)
            canvas.drawPath(mPath, getLinePaint());
        mPath.reset();
    }


    public void renderCapRound(Canvas canvas,RectF rect,float capHeight,
                               boolean showBoxBorder,boolean showBackground)
    {


        if(!showBackground)	return;
        setPaintLineStyle();

        float centerX = rect.left + rect.width() * 0.5f;
        float AngleH = capHeight;
        float AngleY = rect.bottom;


        if(null == mRect)mRect = new RectF();
        mRect.left = rect.left + mBorderSpadding;
        mRect.top = rect.top + mBorderSpadding;
        mRect.right = rect.right - mBorderSpadding;
        mRect.bottom = rect.bottom - mBorderSpadding;

        getBackgroundPaint().setStyle(Paint.Style.FILL);
        //getLinePaint().setStyle(Style.STROKE);
        canvas.drawRoundRect(mRect, this.getRoundRadius(), this.getRoundRadius(), getBackgroundPaint());


        if(null == mPath) mPath = new Path();

        float pHeight = DrawHelper.getInstance().getPaintFontHeight(getBackgroundPaint());
        mPath.moveTo( centerX + AngleH, AngleY - pHeight );
        mPath.lineTo( centerX , AngleY + AngleH );
        mPath.lineTo( centerX - AngleH, AngleY - pHeight);
        mPath.close();
        canvas.drawPath(mPath, getBackgroundPaint());
        mPath.reset();



		/*
		if(showBoxBorder)
		{
			mPath.moveTo(rect.left, rect.bottom);
			mPath.lineTo(rect.left, rect.top);
			mPath.lineTo(rect.right, rect.top);
			mPath.lineTo(rect.right, rect.bottom);
			mPath.lineTo( centerX + AngleH, AngleY);
			mPath.lineTo( centerX , AngleY + AngleH );
			mPath.lineTo( centerX - AngleH, AngleY);
			mPath.close();
			canvas.drawPath(mPath, getLinePaint());
			mPath.reset();
		}
		*/

    }


    public void renderRound(Canvas canvas,RectF rect,float capHeight,
                            boolean showBoxBorder,boolean showBackground)
    {

        setPaintLineStyle();

        if(null == mRect)mRect = new RectF();
        mRect.left = rect.left + mBorderSpadding;
        mRect.top = rect.top + mBorderSpadding;
        mRect.right = rect.right - mBorderSpadding;
        mRect.bottom = rect.bottom - mBorderSpadding;

        if(showBackground)
            canvas.drawRoundRect(mRect, this.getRoundRadius(), this.getRoundRadius(), getBackgroundPaint());

        if(showBoxBorder)
            canvas.drawRoundRect(mRect, this.getRoundRadius(), this.getRoundRadius(), getLinePaint());

    }


    /**
     * 绘制边
     * @param canvas	画布
     * @param left	左边距
     * @param top	上边距
     * @param right	右边距
     * @param bottom	底边距
     */
    public void renderBorder(String type ,Canvas canvas,
                             float left,float top,float right,float bottom)
    {

        mRect.left = left + mBorderSpadding;
        mRect.top = top + mBorderSpadding;
        mRect.right = right - mBorderSpadding;
        mRect.bottom = bottom - mBorderSpadding;

        setPaintLineStyle();

        switch(getBorderRectType())
        {
            case RECT:
                if(type.equals("CHART"))
                {
                    if(null != mPaintBackground)
                        canvas.drawRect(mRect, mPaintBackground);
                }else{ //BORDER
                    canvas.drawRect(mRect, getLinePaint());
                }
                break;
            case ROUNDRECT:
                if(type.equals("CHART"))
                {
                    if(null != mPaintBackground)
                        canvas.drawRoundRect(mRect, getRoundRadius(),
                                getRoundRadius(), mPaintBackground);
                }else{ //BORDER
                    canvas.drawRoundRect(mRect, getRoundRadius(),
                            getRoundRadius(), getLinePaint());
                }
                break;
        }
    }


}
