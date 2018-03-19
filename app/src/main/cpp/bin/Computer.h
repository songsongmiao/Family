//
// Created by Administrator on 2018-01-16.
//
//这个判断就是为了避免重复include
#ifndef CDEMOUNLINK_COMPUTER_H
#define CDEMOUNLINK_COMPUTER_H
//声明该类
class Computer{
private :
    char *cpu;
    char *display;
    char *name;
    int age;
public :
    void setCPU(char* cpu);
    char* getCPU();
    void setDisplay(char* display);
    char* getDisplay();
    void setName(char* name);
    char* getName();
    void setAge(int age);
    int getAge();
};


#endif //CDEMOUNLINK_COMPUTER_H
