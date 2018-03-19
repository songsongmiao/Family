package com.pinet.cdemounlink.test;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by Administrator on 2017/12/14.
 */

public class PathEvaluator implements TypeEvaluator<PointF> {
    private PointF pointF;

    public PointF getPointF()
    {
        return pointF;
    }


    public PathEvaluator() {
        pointF = new PointF();
    }
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        //以正玄曲线从左边运行到右边
        //x轴：直线运动

        float x;
        float y;
//        if (fraction == 0) {
//            x = startValue.x;
//        } else {
//            x = fraction * endValue.x;
//        }
        int radius = (int) Math.sqrt((startValue.x - 200)* (startValue.x - 200) + (startValue.y - 200)* (startValue.y - 200));
        x = (float) ((fraction * 30 + 30) * Math.cos(15* fraction + 30 ))+ radius ;
        y = (float) ((fraction * 30 + 30)* Math.sin(15 * fraction + 30)) + radius;
//        Log.e("evaluator","fraction: "+ fraction);
        pointF.x = x;
        pointF.y = y;
        return pointF;
    }


}
