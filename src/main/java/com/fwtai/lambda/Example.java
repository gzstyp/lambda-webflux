package com.fwtai.lambda;

/**
 * lambda示例代码
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021-02-28 10:25
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
public final class Example{

    @FunctionalInterface
    interface Persion{
        void say();
        default void fun(){
            System.out.println("我是lambda函数接口编程的默认方法,默认方法可以有多个");
        }
    }

    public static void main(String[] args){
        //常规的写法
        final Persion jerry = new Persion(){
            @Override
            public void say(){
                System.out.println("常规的写法");
            }
        };

        //lambda的写法1
        final Persion tom = ()->{
            System.out.println("lambda的写法1");
        };

        //lambda的写法2,当且仅当只有一行方法体时可以省略{}
        final Persion mary = () -> System.out.println("lambda的写法2");

        jerry.say();

        tom.say();
        tom.fun();

        mary.say();
    }
}