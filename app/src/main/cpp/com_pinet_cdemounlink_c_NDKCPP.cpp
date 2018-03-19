//
// Created by Administrator on 2018-01-19.
//
#include <android/log.h>
#include "com_pinet_cdemounlink_c_NDKCPP.h"


JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKCPP_callCppConstruct
(JNIEnv *, jobject)
{
    __android_log_print(ANDROID_LOG_INFO,"main","NDK");
}