package com.pinet.cdemounlink;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/22.
 */

public class TestCustomCheckBox extends AppCompatActivity {


    private TextView tvPhone;
    private CustomCheckBox checkBox;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_custom_checkbox);

    }





}
