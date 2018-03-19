package com.pinet.cdemounlink.flow;

import android.graphics.PointF;

/**
 * Created by Administrator on 2017/11/29.
 */

public class PlotArcLabelInfo extends PlotDataInfo {

    public float Radius = 0.0f;

    public float OffsetAngle = 0.0f;
    public float CurrentAngle = 0.0f;

    private PointF mLabelPointF = null;

    public PlotArcLabelInfo(){};


    public PlotArcLabelInfo(int id,float x,float y,
                            float radius,float offsetAngle,float currentAngle)
    {
        ID = id;
        X = x;
        Y = y;
        Radius = radius;
        OffsetAngle = offsetAngle;
        CurrentAngle = currentAngle;
    }


    public float getRadius() {
        return Radius;
    }


    public void setRadius(float radius) {
        Radius = radius;
    }


    public float getOffsetAngle() {
        return OffsetAngle;
    }


    public void setOffsetAngle(float offsetAngle) {
        OffsetAngle = offsetAngle;
    }


    public float getCurrentAngle() {
        return CurrentAngle;
    }


    public void setCurrentAngle(float currentAngle) {
        CurrentAngle = currentAngle;
    }


    public PointF getLabelPointF() {
        return mLabelPointF;
    }


    public void setLabelPointF(PointF point) {
        this.mLabelPointF = point;
    };


}
