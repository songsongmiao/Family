package com.pinet.cdemounlink.flow;

import android.graphics.Canvas;
import android.graphics.LinearGradient;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PlotAreaRender extends PlotArea implements IRender {
    public PlotAreaRender()
    {

    }

    /**
     * 绘制背景
     */
    protected void drawPlotBackground(Canvas canvas)
    {
        if(null == canvas) return;
        if(getBackgroundColorVisible())
        {
            if(getApplayGradient())
            {
                LinearGradient linearGradient ;
                if(getGradientDirection() == XEnum.Direction.VERTICAL)
                {
                    linearGradient = new LinearGradient(
                            0, 0, 0, getBottom() - getTop(),
                            getBeginColor(),getEndColor(),
                            getGradientMode());
                }else{
                    linearGradient = new LinearGradient(
                            getLeft(),getBottom(),getRight(),getTop(),
                            getBeginColor(),getEndColor(),
                            getGradientMode());
                }
                getBackgroundPaint().setShader(linearGradient);
            }else{
                getBackgroundPaint().setShader(null);
            }

            canvas.drawRect(mLeft,mTop,
                    mRight,mBottom, getBackgroundPaint());
        }
    }

    /**
     * 得到中心点X坐标
     * @return X坐标
     */
    public float getCenterX() {
        return Math.abs(mLeft + (mRight - mLeft)/2);
    }

    /**
     * 得到中心点Y坐标
     * @return Y坐标
     */
    public float getCenterY() {
        return (Math.abs(mBottom - (mBottom - mTop)/2));
    }



    /**
     * 设置绘图区的左边X坐标
     * @param left	X坐标
     */
    public void setLeft(float left) {
        this.mLeft = left;
    }

    /**
     * 设置绘图区的上面Y坐标
     * @param top	Y坐标
     */
    public void setTop(float top) {
        this.mTop = top;
    }

    /**
     * 设置绘图区的右边X坐标
     * @param right	X坐标
     */
    public void setRight(float right) {
        this.mRight = right;
    }

    /**
     * 设置绘图区的底部Y坐标
     * @param bottom	Y坐标
     */
    public void setBottom(float bottom) {
        this.mBottom = bottom;
    }

    /**
     * 返回实际绘图区最右边X值，包含了扩展绘图区范围
     * @return 绘图区最右边X值
     */
    @Override
    public float getPlotRight() {
        return mRight + getExtWidth();
    }


    //public float getPlotTop() {
    //	return mTop + getExtWidth();
    //}


    @Override
    public boolean render(Canvas canvas) throws Exception {
        // TODO Auto-generated method stub
        try{
            if(null == canvas) return false;
            drawPlotBackground(canvas);
        }catch( Exception e){
            throw e;
        }
        return false;
    }


}
