package com.fwtai.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 常用的jdk8自带的函数接口,箭头→的左边的输入(参数),右边是输出(返回值)
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021/2/6 11:08
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
public class JDK8Lambda{

    public static void main(String[] args){
        fun();
    }

    //没有输入仅有输出,和Consumer是相反的
    protected static void supplier(){
        final Supplier<String> supplier = ()-> "我是没有输入仅有输出,和Consumer是相反的";
        final String s = supplier.get();
        System.out.println("s = " + s);
    }

    //仅有输入没有输出,和Supplier是相反的
    protected static void consumer1(){
        final Consumer<String> consumer = System.out::println;
        consumer.accept("我是仅有输入没有输出,和Supplier是相反的,和consumer2写法不一样");
    }

    //仅有输入没有输出,和Supplier是相反的
    protected static void consumer2(){
        final Consumer<String> consumer = (params)-> {
            System.out.println(params);
        };
        consumer.accept("我是仅有输入没有输出,和Supplier是相反的,和consumer1写法不一样");
    }

    //既有输入参数又有返回结果，输入是T，返回的是R
    protected static void function(){
        final Function<String,String> function1 = (result)-> result;
        final String r = function1.apply("既有输入参数又有返回结果，输入是T，返回的是R");//传入参数,r接收的是返回值
        final Function<Integer,Integer> function2 = (result)-> result * result;
        System.out.println("function1 = " + r);
        final Integer apply = function2.apply(9);
        System.out.println("既有输入参数又有返回结果，输入是T，返回的是 apply,function2 = " + apply);
    }

    //专门针对Function<T,R>的输入和输出都是同一个类型的话,可以使用 UnaryOperator 代替
    protected static void unaryOperator(){
        final UnaryOperator<Integer> operator = (params) -> params * params;
        final Integer r = operator.apply(8);
        System.out.println("接口是可以继承的呢,专门针对Function<T,R>的输入和输出都是同一个类型的话,可以使用 UnaryOperator 代替!! r = " + r);
    }

    //既有2个输入参数又有返回结果，输入是两个参数T,U，返回的是R
    protected static void biFunction(){
        final BiFunction<Integer,Integer,Integer> biFunction = (t,u) -> t * u;
        final Integer r = biFunction.apply(8,9);
        System.out.println("既有输入参数又有返回结果，但输入是两个参数T,U，返回的 r = " + r);
    }

    //这个是重点,含使用方法!!!!
    protected static void predicate(){
        final Predicate<Integer> predicate = (params) -> bl(params);//可以写成 final Predicate<Integer> predicate = JDK8::bl;
        final boolean b = predicate.test(1);
        System.out.println("b = " + b);
    }

    protected static boolean bl(final Integer params){
        return params > 0;
    }

    //级联表达式,参数为‘函数接口’,而参数又是'函数接口'，解释:第1个t是第1个Function<T,R>的第1个输入参数T;r是返回值且又是函数接口类型Function<T,R>
    protected static void fun(){
        final Function<Integer,Function<Integer,Integer>> fun = t -> r -> t + r;//箭头→的左边的输入(参数),右边是输出(返回值)
        final Integer result = fun.apply(10).apply(20);
        System.out.println("result = " + result);// result = 30
    }
}