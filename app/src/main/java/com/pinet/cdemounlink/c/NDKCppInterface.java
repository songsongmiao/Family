package com.pinet.cdemounlink.c;

/**
 * Created by Administrator on 2018-01-12.
 */

public class NDKCppInterface {

    //1、c++中的构造函数
    public native void excuteCppCustruct();


    //2.析构函数
    public native void executeCppDesctruct();


    //3. C++拷贝函数
    public native void executeCppCopyFun();

    //4. C++ 中的拷贝函数调用
    public native void executeCppScenes();


    //5. C++ 中前拷贝和深拷贝
    public native void executeCppCopy();
}
