//
// Created by Administrator on 2018-01-12.
//

#include <iostream>
#include <android/log.h>
#include "com_pinet_cdemounlink_c_NDKCppInterface.h"
#include "bin/Company.h"
using namespace std;

JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKCppInterface_excuteCppCustruct
(JNIEnv *, jobject)
{
    Company company ;
//    company.setName("潭州教育");
    __android_log_print(ANDROID_LOG_INFO,"main","公司的年龄：%d",company.getAge());
    __android_log_print(ANDROID_LOG_INFO,"main","公司的名称：%s",company.getName());
//    Company company (100,"");
//    Company company1();
//    Company company2(100);

//    Company* company1 = new Company(100,"");

}


//2. C++中的析构函数
JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKCppInterface_executeCppDesctruct
    (JNIEnv *, jobject)
{
    //析构函数在什么时候调用:
    //总结： 析构函数在释放对象之前调用
    //作用： 善后处理（释放对象）
//    Company company(100,"潭州教育");
    //问题二： new 关键字实例化与不适用new 关键字实例化有什么区别？
    //总结：使用new关键字实例化析构函数西药自己进行管理，不使用new 关键字实例化，那么虚拟机自动帮致我们管理
    //按照正常情况应该正我们函数执行完毕释放内存之前调用析构函数，但是没有调用，需要我们手动调用西沟函数
    Company* company = new Company();
    __android_log_print(ANDROID_LOG_INFO,"main","公司的年龄：%d",company->getAge());
    __android_log_print(ANDROID_LOG_INFO,"main","公司的名称：%s",company->getName());
    company->~Company();
    __android_log_print(ANDROID_LOG_INFO,"main","调用堆内存");

}


//3. C++ 拷贝函数
JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKCppInterface_executeCppCopyFun
        (JNIEnv *env , jobject jobj)
{
    //拷贝函数：实例化一个新的对象
//    Company company1(100,"谭洲教育");
//    Company company2 = company1;
//    __android_log_print(ANDROID_LOG_INFO,"main","公司1的年龄：%d",company2.getAge());
//    __android_log_print(ANDROID_LOG_INFO,"main","公司2的名称：%s",company2.getName());
    //第二种方式 ： 赋值操作
    Company company1(100,"潭州教育");
    Company company2(200,"阿里巴巴");
    //如果已经是创建了得对象，那么我们要拷贝，这个时候只会进行复制操作。
    //这个是一个单纯的赋值操作而已，并不会创建一个对象
    company2 = company1;
    __android_log_print(ANDROID_LOG_INFO,"main","公司1的年龄：%d",company2.getAge());
    __android_log_print(ANDROID_LOG_INFO,"main","公司2的名称：%s",company2.getName());

}



JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKCppInterface_executeCppScenes
        (JNIEnv *, jobject)
{
    //场景一： 被赋值的对象本身是空对象，这个时候会进行创建的对象拷贝
//    Company company1(100,"潭州教育");
//    Company company2 = company1;
//    __android_log_print(ANDROID_LOG_INFO,"main","公司1的年龄：%d",company2.getAge());
//    __android_log_print(ANDROID_LOG_INFO,"main","公司2的名称：%s",company2.getName());


    //场景二：实参初始化行参，这个时候也会进行场景对象拷贝
    //公司之间进行合作

//    Company company1(100,"潭州教育");
//    Company company2(200,"阿里巴巴");
//    company1.work(company2);

    //场景三:直接调用析构函数
//    Company company1(100,"谭洲教育");
//    Company company2(company1);


    //场景四： 匿名对象



}

//5.C++ 浅拷贝和深拷贝
JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKCppInterface_executeCppCopy
        (JNIEnv *, jobject)
{
    //浅拷贝：默认就是浅拷贝
    Company company (100,"潭州教育");
    Company company1 = company;
    __android_log_print(ANDROID_LOG_INFO,"main","公司2的名称：%s",company1.getName());

    //有一种这样的场景
    //一下代码会报错（ARGUMENT IS INVALIED HEAP ADDRESS IN dlfree addr=0x89dd448）
    //重复释放内存导致的（两个变量释放同一块内存区域）

    //这个问题就是因为浅拷贝只是单纯拷贝值，所以导致该问题
    //解决方案： 深拷贝
    Company company2;
    Company company3 = company2;
    char *name = company2.getName();
    char *name1 = company3.getName();
    __android_log_print(ANDROID_LOG_INFO,"main","name的地址：：%p",name);
    __android_log_print(ANDROID_LOG_INFO,"main","name1的地址：%p",name1);

}



