package com.pinet.cdemounlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.pinet.cdemounlink.c.NDKCConst;
import com.pinet.cdemounlink.c.NDKCppInterface;
import com.pinet.cdemounlink.c.NDKOverLoad;
import com.pinet.cdemounlink.recycle.DividerItemDecoration;
import com.pinet.cdemounlink.recycle.RecycleAdapter;
import com.pinet.cdemounlink.recycle.RefreshUtils;
import com.pinet.cdemounlink.recycle.TopScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    private NDKCppInterface mCppInterface;
    private NDKOverLoad mLoad;
    private NDKCConst mConst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        mCppInterface = new NDKCppInterface();
        mLoad = new NDKOverLoad();
        mConst = new NDKCConst();
        tv.setText(stringFromJNI());

        String a = new String("Dream");
        get(a);

    }


    private void get(String s)    {
        s = "100";
    }


    public void OnPrintLog(View view)
    {
        mCppInterface.excuteCppCustruct();
    }

    public void OnRelease(View view)
    {
        mCppInterface.executeCppDesctruct();
    }

    public void OnCopy(View view )
    {
        mCppInterface.executeCppCopyFun();
    }

    public void OnScenes(View view)
    {
        mCppInterface.executeCppScenes();
    }

    public void OnConst(View view)
    {
        mLoad.executeConst();
    }

    public void OnCConst(View view)
    {
        mConst.executeCConst();
    }

    public void OnDeepScenes(View view)
    {
        mCppInterface.executeCppCopy();
    }


    public void OnPointer(View view)
    {
        mLoad.executeCppPointer();
    }


    public void OnReference(View view)
    {
        mLoad.executeCppConstsPointer();
    }

    public void OnInLine(View view)
    {
        mLoad.executeCppInlineFun();
    }

    public void OnParams(View view)
    {
        mLoad.executeCppFunParam();
    }

    public void OnClass(View view )
    {
        mLoad.executeFunClass();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();



}
