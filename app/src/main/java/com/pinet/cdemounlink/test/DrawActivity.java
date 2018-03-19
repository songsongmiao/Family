package com.pinet.cdemounlink.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pinet.cdemounlink.R;

/**
 * Created by Administrator on 2017/12/15.
 */

public class DrawActivity extends AppCompatActivity {

    private SolveClickTouchConflictLayout mTouch;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        mTouch = (SolveClickTouchConflictLayout) findViewById(R.id.solve_layout);
        mTouch.setmSetOnSlideListener(mSlideListener);
    }


    private SolveClickTouchConflictLayout.OnSlideListener mSlideListener = new SolveClickTouchConflictLayout.OnSlideListener(){
        @Override
        public void onRightToLeftSlide() {

        }

        @Override
        public void onLeftToRightSlide() {

        }

        @Override
        public void onUpToDownSlide() {

        }

        @Override
        public void onDownToUpSlide() {

        }
    };
}
