package com.pinet.cdemounlink.flow;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by Administrator on 2017/11/29.
 */

public class AnchorRender {

    private static AnchorRender instance = null;

    private RectF mRect= null;

    private Paint mPaintText = null;
    private Paint mPaintBg = null;

    public AnchorRender()
    {

    }

    public static synchronized AnchorRender getInstance()
    {
        if(instance == null)
        {
            instance = new AnchorRender();
        }
        return instance;
    }


    public void renderAnchor(Canvas canvas,
                             AnchorDataPoint pAnchor, float cx, float cy,float cradius,
                             float left,float top,float right, float bottom) {

        if(null == pAnchor) return;
        float radius = pAnchor.getRadius();

        switch(pAnchor.getAreaStyle())
        {
            case FILL:
                getBgPaint().setStyle(Paint.Style.FILL);
                break;
            case STROKE:
                getBgPaint().setStyle(Paint.Style.STROKE);
                break;
        }
        getBgPaint().setColor(pAnchor.getBgColor());

        float width = getBgPaint().getStrokeWidth();
        if( pAnchor.getLineWidth() > -1 )
        {
            getBgPaint().setStrokeWidth(pAnchor.getLineWidth());
        }

        switch (pAnchor.getAnchorStyle()) {
            case CAPRECT:
            case CAPROUNDRECT:
            case ROUNDRECT:
                renderRoundRect(canvas,pAnchor,cx, cy,radius);
                break;
            default:
                switch (pAnchor.getAnchorStyle()) {
                    case RECT:
                        renderRect(canvas,getBgPaint(),radius,cx, cy);
                        break;
                    case CIRCLE:
                        canvas.drawCircle(cx, cy, radius, getBgPaint());

                        break;
                    case VLINE:
                        //canvas.drawLine(cx, top,cx, bottom, getBgPaint());
                        DrawHelper.getInstance().drawLine(pAnchor.getLineStyle(),
                                cx, top,cx, bottom,
                                canvas, getBgPaint());
                        break;
                    case HLINE:
                        //canvas.drawLine(left, cy,right, cy, getBgPaint());
                        DrawHelper.getInstance().drawLine(pAnchor.getLineStyle(),
                                left, cy,right, cy,
                                canvas, getBgPaint());
                        break;
                    case TOBOTTOM:
                        //canvas.drawLine(cx, cy + cradius,cx,bottom, getBgPaint());
                        DrawHelper.getInstance().drawLine(pAnchor.getLineStyle(),
                                cx, cy + cradius,cx,bottom,
                                canvas, getBgPaint());
                        break;
                    case TOTOP:
                        //canvas.drawLine(cx, cy - cradius,cx, top, getBgPaint());
                        DrawHelper.getInstance().drawLine(pAnchor.getLineStyle(),
                                cx, cy - cradius,cx, top,
                                canvas, getBgPaint());
                        break;
                    case TOLEFT:
                        //canvas.drawLine(cx - cradius, cy,left, cy, getBgPaint());
                        DrawHelper.getInstance().drawLine(pAnchor.getLineStyle(),
                                cx - cradius, cy,left, cy,
                                canvas, getBgPaint());
                        break;
                    case TORIGHT:
                        //canvas.drawLine(cx + cradius, cy,right, cy, getBgPaint());
                        DrawHelper.getInstance().drawLine(pAnchor.getLineStyle(),
                                cx + cradius, cy,right, cy,
                                canvas, getBgPaint());
                        break;
                    default:
                }

                if(pAnchor.getAnchor().trim() != "" )
                {
                    getTextPaint().setColor(pAnchor.getTextColor());
                    getTextPaint().setTextSize(pAnchor.getTextSize());
                    canvas.drawText(pAnchor.getAnchor(), cx, cy, getTextPaint());
                }
        }

        getBgPaint().setStrokeWidth(width);
    }

    private void renderRoundRect(Canvas canvas,
                                 AnchorDataPoint pAnchor,
                                 float cirX,float cirY,float radius){

        float angleW = pAnchor.getCapRectW() / 2;//20.f;
        float angleH = pAnchor.getCapRectH(); //10.f;

        float fontH =  pAnchor.getCapRectHeight(); // angleH ; // + 5.f;
        float extW = angleW + radius;

        if(Float.compare(radius, angleW) == -1 || Float.compare(radius, angleW) == 0)
        {
            extW = angleW + 30.f;
        }

        String anchor = pAnchor.getAnchor().trim();
        if(pAnchor.getAnchor() != "")
        {
            float textHeight = DrawHelper.getInstance().getPaintFontHeight(getTextPaint()) + 30.f;
            if(Float.compare(textHeight, fontH) == 1) fontH = textHeight;
            float textWidth = DrawHelper.getInstance().getTextWidth(getTextPaint(), anchor);
            if( Float.compare(textWidth, extW ) == 1 ) extW = textWidth;
        }

        switch (pAnchor.getAnchorStyle()) {
            case CAPRECT:
                renderCapRect(canvas,pAnchor,cirX, cirY, radius,angleW,angleH,fontH,extW);
                break;
            case CAPROUNDRECT:
                renderCapRound(canvas,pAnchor,cirX,cirY,radius,angleW,angleH,fontH,extW);
                break;
            case ROUNDRECT:
                renderRound(canvas,pAnchor,cirX, cirY, radius,angleW,angleH,fontH,extW);
                break;
            default:
        }

        if(pAnchor.getAnchor() != "")
        {
            getTextPaint().setColor(pAnchor.getTextColor());
            getTextPaint().setTextSize(pAnchor.getTextSize());
            canvas.drawText(anchor, cirX,  cirY - angleH - fontH/3, getTextPaint());
        }
        mPaintText = null;
    }

    private void renderCapRound(Canvas canvas,AnchorDataPoint pAnchor,
                                float cirX,float cirY,float radius,
                                float angleW,float angleH,float fontH,float extW){
        //round Rect这种强制bgPaint为fill
        getBgPaint().setStyle(Paint.Style.FILL);
        renderRound(canvas,pAnchor,cirX,cirY,radius,angleW,angleH,fontH,extW);
        renderCap(canvas,pAnchor,cirX,cirY,radius,angleW,angleH,fontH,extW);
    }

    private void renderCapRect(Canvas canvas,AnchorDataPoint pAnchor,
                               float cirX,float cirY,float radius,
                               float angleW,float angleH,float fontH,float extW){

        Path path = new Path();
        path.moveTo(cirX, cirY);
        path.lineTo(cirX - angleW , cirY - angleH);
        path.lineTo(cirX - extW , cirY - angleH);
        path.lineTo(cirX - extW, cirY - angleH - fontH);
        path.lineTo(cirX + extW,  cirY - angleH - fontH);
        path.lineTo(cirX + extW,  cirY - angleH );
        path.lineTo(cirX + angleW,  cirY - angleH );
        path.lineTo(cirX, cirY);
        path.close();
        canvas.drawPath(path, getBgPaint());
        path.reset();
    }

    private void renderRound(Canvas canvas,AnchorDataPoint pAnchor,
                             float cirX,float cirY,float radius,
                             float angleW,float angleH,float fontH,float extW){

        if(null == mRect)mRect = new RectF();
        mRect.left =  cirX - extW;
        mRect.top =   cirY - angleH - fontH;
        mRect.right =  cirX + extW;
        mRect.bottom = cirY - angleH;

        getBgPaint().setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(mRect, pAnchor.getRoundRadius(),
                pAnchor.getRoundRadius(), getBgPaint());
        mRect.setEmpty();
    }

    private void renderCap(Canvas canvas,AnchorDataPoint pAnchor,
                           float cirX,float cirY,float radius,
                           float angleW,float angleH,float fontH,float extW){

        Path path = new Path();
        path.moveTo(cirX, cirY);
        path.lineTo(cirX - angleW , cirY - angleH);
        path.lineTo(cirX + angleW,  cirY - angleH );
        path.close();
        canvas.drawPath(path, getBgPaint());
        path.reset();
    }

    private void renderRect(Canvas canvas,Paint paint,
                            float radius,float cirX,float cirY )
    {
        if(null == mRect)mRect = new RectF();

        mRect.left =  (cirX - radius);
        mRect.top =   (cirY - radius);
        mRect.right =  (cirX + radius);
        mRect.bottom = (cirY + radius);
        canvas.drawRect(mRect,getBgPaint());
        mRect.setEmpty();
    }

    private Paint getTextPaint()
    {
        if(null == mPaintText)
        {
            mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaintText.setTextAlign(Paint.Align.CENTER);
        }
        return mPaintText;
    }

    private Paint getBgPaint()
    {
        if(null == mPaintBg) mPaintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBg.setStrokeWidth(2);
        return mPaintBg;
    }

}
