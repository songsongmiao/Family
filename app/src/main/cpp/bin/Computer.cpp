//
// Created by Administrator on 2018-01-16.
//

#include <iostream>
#include "Computer.h"


void Computer::setCPU(char* cpu)
{
    this->cpu = cpu;
}

char* Computer::getCPU()
{
    return this->cpu;
}

void Computer::setDisplay(char* display)
{
    this->display = display;
}

char* Computer::getDisplay()
{
    return this->display;
}

void Computer::setName(char* name)
{
    this->name = name;

}

char* Computer::getName()
{
    return this->name;
}

void Computer::setAge(int age)
{
    this->age = age;
}

int Computer::getAge()
{
    return this->age;

}


