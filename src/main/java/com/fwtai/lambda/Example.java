package com.fwtai.lambda;

/**
 * lambda示例代码,接口类型有且只有一个抽象方法,可以有多个非抽象方法或静态方法!!!
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
        public void say();//抽象方法
        default void fun(){//默认方法
            System.out.println("我是lambda函数接口编程的默认方法,默认方法可以有多个");
        }
    }

    public static void main(final String[] args){
        //常规的写法
        final Persion jerry = new Persion(){
            @Override
            public void say(){
                System.out.println("常规的写法");
            }
        };

        //lambda的写法1,lambda是匿名函数的变体???
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