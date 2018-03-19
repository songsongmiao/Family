#include <jni.h>
#include <string>
#include "LogUtils.h"
#include <stdbool.h>
#include <stdio.h>
#include <string>
using namespace std;


void my()
{
    __android_log_print(ANDROID_LOG_ERROR,"JNI","这是我们第一个JNI日志！");
    LOGE("这是我们的一个日志信息,我们使用LogUtils打印日志！");
    double a= 1;
    double* p =  &a;
    LOGE("%#x %lf",p,p);


}



extern "C"
jstring
Java_com_pinet_cdemounlink_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    my();
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
