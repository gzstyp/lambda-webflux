package com.fwtai.bean;
/*
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021-05-25 23:00
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
public final class Dog{

    private String name = "哮天犬";

    //todo 狗叫,静态方法,输入的是Dog是否。输出是void空的,它也是消费者
    public static void bark(final Dog dog){
        System.out.println(dog + "叫了");
    }

    @Override
    public String toString(){
        return this.name;
    }
}