//
// Created by Administrator on 2018-01-15.
//

#include <stdlib.h>
#include <string.h>
#include <android/log.h>
#include "com_pinet_cdemounlink_c_NDKCConst.h"


/*
 * Class:     com_pinet_cdemounlink_c_NDKCConst
 * Method:    executeCConst
 * Signature: ()V
 */
//C语言中的常量不可以修改
//总结：常量的值在C++语言是可以修改的，但是与编译环境有关，
//例如：在android studio 中不可以，但是在Visual Studio 就可以修改
// 然而C 语言不管在任何的编译环境中都不允许修改
JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKCConst_executeCConst
        (JNIEnv *env, jobject jobj)
{
    const int a = 100;
    int *p = (int*)&a;
    *p = 200;
    __android_log_print(ANDROID_LOG_INFO,"main","C语言： %d",a);
}