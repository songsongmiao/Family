//
// Created by Administrator on 2018-01-12.
//

#include <iostream>
#include <stdlib.h>
#include <android/log.h>
#include "Company.h"


using namespace std;


Company ::Company() {
//    this->name = "谭洲教育";
//    this->age = 100;
//    this->name = (char*)malloc(sizeof(char*)*1000);
//    strcpy(this->name,"谭洲教育");
    this->age = 100;
    //开辟一块堆内存
    this->name = (char*)malloc(sizeof(char*)*1000);
    strcpy(this->name,"谭洲教育");
    __android_log_print(ANDROID_LOG_INFO,"main","调用——构造函数");
}

Company ::Company(int age) {
    this->age = age;
    __android_log_print(ANDROID_LOG_INFO,"main","调用——构造函数");
}

Company ::Company(int age, char* name) {
//    this->age= age;
//    this->name = name;
    this->age = age;
    //开辟一块堆内存
    this->name = (char*)malloc(sizeof(char*)*1000);
    strcpy(this->name,name);
    __android_log_print(ANDROID_LOG_INFO,"main","调用——构造函数");
}

Company ::~Company() {
    //析构函数中释放堆内存
    this->name = NULL;
    free(this->name);
    __android_log_print(ANDROID_LOG_INFO,"main","调用——析构函数");
}

//自己重写系统默认的拷贝函数(系统自动调用，不需要我们管理)
Company ::Company(const Company &obj) {
    //浅拷贝
    //系统本身其实就是干了一件赋值的操作
//    this->name= obj.name;
//    this->age = obj.age;
//    __android_log_print(ANDROID_LOG_INFO,"main","调用——拷贝函数");

    //深拷贝
    //开辟一块堆内存
    this->name = (char*)malloc(sizeof(char) * 1000);
    strcpy(this->name,obj.name);
    this->age = obj.age;
    __android_log_print(ANDROID_LOG_INFO,"main","调用——拷贝函数");

}


void Company::setAge(int age) {
    this->age = age;
    __android_log_print(ANDROID_LOG_INFO,"main","调用——构造函数");
}

int Company::getAge() {
    return this->age;
    __android_log_print(ANDROID_LOG_INFO,"main","调用——getAge函数");
}

void Company::setName(char *name) {
    this->name = name;
    __android_log_print(ANDROID_LOG_INFO,"main","调用——setName函数");
}

char* Company::getName() {
    return this->name;
    __android_log_print(ANDROID_LOG_INFO,"main","调用——getName函数");
}


void Company::work(Company company) {
    __android_log_print(ANDROID_LOG_INFO,"main","%s和%s",this->getName(),company.getName());
}