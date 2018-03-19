//
// Created by Administrator on 2018-01-15.
//

#include <iostream>
#include <stdlib.h>
#include "com_pinet_cdemounlink_c_NDKOverLoad.h"
#include <android/log.h>
//在C++中命名空间（类似于Java的包）
//std:代表标准的命名空间
using namespace std;

//C++中的常量
//C++中的常的值不能够修改（不管你是简介修改还是直接修改，都是不允许）
JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKOverLoad_executeConst
        (JNIEnv *, jobject)
{
    const  int a = 100;
    //常量不能修改，但是可以间接修改
    int *p =(int*) &a;
    *p = 200;
    __android_log_print(ANDROID_LOG_INFO,"main","修改值：%d ", a);

}


// 2. 指针的引用
struct User{
    char *name;
    int age;
};

//二级指针的引用
void update_user(User** u)
{
    User *user = (User*) malloc(sizeof(User));
    user->name = "Dream";
    user->age = 100;
    *u  = user;

}

//指针的引用
void update_User(User* &u)
{
    u= (User*)malloc(sizeof(User));
    u->name="Crish";
    u->age = 18;
}

void get(char a )
{

}



JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKOverLoad_executeCppPointer
        (JNIEnv * env, jobject jobj)
{
    User *user = NULL;
//    int a = 100;
//    int *p = &a;
//    int **p2 = &p;
//  二级指针的使用
    update_user(&user);
     __android_log_print(ANDROID_LOG_INFO,"main","二级指针引用 result : %s",user->name);

    //指针的引用(编译器自动将User 转成&User)
    update_User(user);
    __android_log_print(ANDROID_LOG_INFO,"main","指针引用 result : %s",user->name);

    char b= 100;
    get(b);

}

void const_init()
{
    int a = 100;
    int b= 200;
    //常引用写法： const  int &c
    const int &c  = a;
    __android_log_print(ANDROID_LOG_INFO,"main","值： %d",c);

    //第二种写法：字面量
    const int &d = 200;
    __android_log_print(ANDROID_LOG_INFO,"main","字面量：%d",d);

}
//重点： 场引用作为函数的参数使用
struct Company{
    char* name;
    char* address;
    int age;
};

//类似于Java中的final
void const_fun_param(const Company &cp)
{
    //不能修改该（只读）
//    cp.age = 100;
    __android_log_print(ANDROID_LOG_INFO,"main","公司地址： %s ",cp.address);
}

void const_printf()
{
    Company company;
    company.address = "湖南省熙盛科技园10栋";
    company.name="谭洲教育科技";
    company.age = 10;
    const_fun_param(company);
}


JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKOverLoad_executeCppConstsPointer
        (JNIEnv *, jobject)
{
//    const_init();
    const_printf();
}


//4.内联函数
//在编译的时候，编译器将函数调用处的代码换成函数体
//内联函数：编译的时候调用
//宏定义：预编译
//例如：买书   买书之前准备钱（预编译） 找书店过程（编译） 找到书店，买书（运行） 在使用上面和普通的函数没有什么区别，但是执行效率比一般的函数高

inline void printf_inline()
{
    char* address = "Dream";
    __android_log_print(ANDROID_LOG_INFO,"main","内容： %s",address);
}


//宏定义和内力那函数的区别？
//需求；求一个数的积？
//总结：这两种情况一般都不这么写（自减、自加）
#define GET_RESULT(A) A*A


inline int get_result(int a )
{
    return a * a ;
}

void printf_result()
{
    int a = 2;
    //分析： int b = (++a) * (++a);
    //第一次： a= 3* （++a）;
    //第二次： a = 4;
    //结果： 3* 4  = 12；
//    int b = GET_RESULT(++a);
//    a = 2;
    //内联函数和普通的函数一样
    //结果 a * a ,然而 a = 3 结果： 9

   int b = get_result(++ a);
    __android_log_print(ANDROID_LOG_INFO,"main","结果： %d",b);
}



JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKOverLoad_executeCppInlineFun
        (JNIEnv *, jobject)
{
    //拷贝（编译的时候）

    printf_inline();
    printf_result();
}


//5. C ++ 中的函数参数
//函数参数默认值
//void fun_param(int a = 100)
//{
//    __android_log_print(ANDROID_LOG_INFO,"main","result: %d",a);
//}
//
//void fun_param(int a ,int b)
//{
//    __android_log_print(ANDROID_LOG_INFO,"main","result : a= %d  ,b = %d",a,b);
//}

//C++中函数可变参数
//API 都会提示用户传递参数类型

void fun_param(int a ,...)
{
    __android_log_print(ANDROID_LOG_INFO,"main","result : %d",a );
    //获取可变参数
    va_list args_p;
    //首先指定可变参数开始的位置
    va_start(args_p,a);
    //一个个获取
    int arg_int = va_arg(args_p, int);
    __android_log_print(ANDROID_LOG_INFO,"main","第一个参数： %d",arg_int);
    //第二个参数char类型
    char arg_char = va_arg(args_p, char);
    __android_log_print(ANDROID_LOG_INFO,"main","第二个参数： %c", arg_char);
    //第三个参数float类型
    float arg_float = va_arg(args_p, float);
    __android_log_print(ANDROID_LOG_INFO,"main","第二个参数： %f", arg_float);
    //结束
    va_end(args_p);

}

void fun_param_init(int a ,...)
{
    //定义可变参数指针
    va_list args_p;
    //首先指定可变参数开始的位置
    va_start(args_p,a);
    int i=0 ;
//    while (true)
//    {
//        i = va_arg(args_p, int);
//        if(i == 0)//告诉程序读取完毕
//            break;
//        __android_log_print(ANDROID_LOG_INFO,"main","参数： %d",i);
//    }
    __android_log_print(ANDROID_LOG_INFO,"main","下一次循环输出：");
    int len = 5;
    int result = 0;i = 0;
    for(;i<len;i++)
    {
        result = va_arg(args_p, int);
        __android_log_print(ANDROID_LOG_INFO,"main","参数： %d",result);
    }
    //结束
    va_end(args_p);

}


int get_min(int a , int b, int c ,int d)
{
    return a<b ? a:b;
}
//函数指针别名
typedef int(GET_MIN_P) (int,int,int,int);

//函数重载（在Java中的方法重载：参数个数不同，参数类型不同，与返回值无关）
//总结： 定义函数重载需要注意不能够存在函数歧义
JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKOverLoad_executeCppFunParam
        (JNIEnv * env, jobject jobj)
{
//    fun_param();
    //注意c语言中第二个参数默认时，存在函数歧义
//    fun_param(10,20);
//    fun_param(10,40,'a',12.4f);
//    fun_param_init(10,20,30,40,50,60);


    //?????????????????
//    GET_MIN_P  p = get_min;
//    int result = p(10,20,30,40);
//    __android_log_print(ANDROID_LOG_INFO,"main","最小值： %d",result);
}





//7.C++中了哦的额定义
#include "bin/Computer.h"
JNIEXPORT void JNICALL Java_com_pinet_cdemounlink_c_NDKOverLoad_executeFunClass
        (JNIEnv *, jobject)
{
//    Computer computer;
//    computer.setCPU("inter");
//    computer.setDisplay("独立显卡");
//    computer.setName("MacBook pro 2016");
//    computer.setAge(6);
//    __android_log_print(ANDROID_LOG_INFO,"main","显卡： %s " ,computer.getDisplay());
    Computer* computer = new Computer();
    computer->setCPU("Inter");
    computer->setName("MacBook Pro 2016");
    computer->setDisplay("独立显卡");
    computer->setAge(6);
        __android_log_print(ANDROID_LOG_INFO,"main","显卡： %s " ,computer->getDisplay());
}
