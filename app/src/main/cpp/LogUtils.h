//
// 日志测试 2017/10/17.
//
#include "android/log.h"
#ifndef LOG_TAG
#define LOG_TAG "JNI"
#endif
#ifndef IS_DEBUG
#define IS_DEBUG true
#endif

#define LOG_NOOP (void) 0
#define LOG_PRINT(level,fmt,...) __android_log_print(level,LOG_TAG,"(%s:%u) %s:"fmt,__FILE__,__LINE__,__PRETTY_FUNCTION__,##__VA_ARGS__);

#if IS_DEBUG
#define LOGI(fmt,...) LOG_PRINT(ANDROID_LOG_INFO,fmt,##__VA_ARGS__)
#else
#define LOGI(...) LOG_NOOP
#endif

#if IS_DEBUG
#define LOGW(fmt,...) LOG_PRINT(ANDROID_LOG_WARN,fmt,##__VA_ARGS__)
#else
#define LOGW(...) LOG_NOOP
#endif

#if IS_DEBUG
#define LOGD(fmt,...) LOG_PRINT(ANDROID_LOG_DEBUG,fmt,##__VA_ARGS)
#else
#define LOGD(...) LOG_NOOP
#endif


#if IS_DEBUG
#define LOGE(fmt,...) LOG_PRINT(ANDROID_LOG_ERROR,fmt ,##__VA_ARGS__)
#else
#define LOGE(...) LOG_NOOP
#endif





#ifndef CDEMOUNLINK_LOGUTILS_H
#define CDEMOUNLINK_LOGUTILS_H





class LogUtils {

};


#endif //CDEMOUNLINK_LOGUTILS_H
