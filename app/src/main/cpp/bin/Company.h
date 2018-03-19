//
// Created by Administrator on 2018-01-12.
//

#ifndef CDEMOUNLINK_COMPANY_H
#define CDEMOUNLINK_COMPANY_H

class Company{
private :
    int age;
    char *name;
public :
    void setAge(int age);
    int getAge();
    void setName(char* name);
    char* getName();

    Company();
    Company(int age);
    Company(int age,char* name);
//    Company(Company company);
    ~Company();
    //拷贝函数
    Company(const Company &obj);

    void work(Company company);



};
#endif //CDEMOUNLINK_COMPANY_H
