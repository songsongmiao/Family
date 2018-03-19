package com.pinet.cdemounlink.c;

/**
 * Created by  mss on 2018-01-15.
 */

public class NDKOverLoad {

    //1.学习C++ 中的常量（包括和C的常量的区别）
    public native void executeConst();


    //2.指针的引用
    public native void executeCppPointer();


    //3.常引用
    public native void executeCppConstsPointer();



    //4.内联函数
    public native void executeCppInlineFun();


    //5.函数参数
    public native void executeCppFunParam();


    //7.C++ 中类的定义：
    public native void executeFunClass();

}
