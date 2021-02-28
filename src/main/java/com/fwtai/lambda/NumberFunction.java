package com.fwtai.lambda;

/**
 * 自定义函数式接口,接口类型有且只有一个抽象方法,可以有多个非抽象方法或静态方法!!!
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-06-12 16:34
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@FunctionalInterface
public interface NumberFunction{

    Integer getValue(final Integer number);

    default Integer value1(){
        return 1;
    }

    default Integer value2(){
        return 1;
    }

    static Integer fun1(){
        return 2;
    }

    static Integer fun2(){
        return 2;
    }
}