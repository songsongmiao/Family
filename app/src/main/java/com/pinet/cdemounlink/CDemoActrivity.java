package com.pinet.cdemounlink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pinet.cdemounlink.c.NDKCPP;

/**
 * Created by Administrator on 2018-01-16.
 */

public class CDemoActrivity extends AppCompatActivity {

    private NDKCPP mCpp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_struct);
        mCpp = new NDKCPP();
    }
              //onStructConst
    public void onStructConst(View view)
    {
        mCpp.callCppConstruct();
    }

}
