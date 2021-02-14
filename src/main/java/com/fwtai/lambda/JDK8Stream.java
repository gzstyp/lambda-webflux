package com.fwtai.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Stream的应用,在流编辑操作中,'终止符'有且只有1个,而'中间操作'符可以有0个或N个;需求是 通过筛选某个词并各个字母排序
 * 使用注解的返回值中Mono的个数 0和或1个;而Flux则可以是0个或N个
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021-02-06 12:29
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
 */
public class JDK8Stream{

    public static void main(final String[] args){
        handle();
    }

    protected final static String[] arr = {"foo","webflux","redis","mysql","","oracle","vue"};
    //1.通过数组创建 Stream
    protected static void byArray(){
        final Stream<String> arrays = Arrays.stream(arr);
        arrays.forEach((params)->System.out.println(params));//参数含有括号
    }

    //2.通过List创建 Stream
    protected static void byList(){
        final List<String> list = Arrays.asList(arr);
        final Stream<String> arrays = list.stream();
        arrays.forEach(t->System.out.println(t));//参数没有括号
    }

    //3.通过Stream.of创建
    protected static void byStreamOf(){
        final Stream<String> arrays = Stream.of(arr);
        arrays.forEach((t)->System.out.println(t));//参数没有括号
    }

    //4.通过迭代器来创建,需求是 打印1到10的数字
    protected static void byIterate(){
        final Stream<Integer> stream = Stream.iterate(1,(t) -> t + 1).limit(10);
        stream.forEach((t)->System.out.println(t));
    }

    //5.通过手动 generate 创建,需求是打印10以内的随机数
    protected static void byGenerate(){
        final Stream<Integer> stream = Stream.generate(() -> new Random().nextInt(10)).limit(10);
        stream.forEach((t)->System.out.println(t));
    }

    //没有输入仅有输出,和Consumer是相反的
    protected static void supplier(){
        final Supplier<String> supplier = ()-> "我是没有输入仅有输出,和Consumer是相反的";
        final String s = supplier.get();
        System.out.println("s = " + s);
    }

    //仅有输入没有输出,和Supplier是相反的
    protected static void consumer2(){
        final Consumer<String> consumer = (params)-> {
            System.out.println(params);
        };
        consumer.accept("我是仅有输入没有输出,和Supplier是相反的,和consumer1写法不一样");
    }

    //实战,在流编辑操作中,'终止符'有且只有1个,而'中间操作'符可以有0个或N个;需求是 通过筛选某个词并各个字母排序
    protected static void handle(){
        final String[] arrs = {"react","","react","vue","ba_el","mysql","ba_el"};
        Arrays.stream(arrs)
            .filter((params) -> !params.isEmpty())//过滤空字符串,是true才返回,此处的参数断言类型的函数接口,参数类型(函数接口)：Stream<T> filter(Predicate<? super T> predicate);
            .distinct()//去除重复元素
            .sorted()//排序
            .limit(1)//获取第1个
            .map((t)->t.replace("_",""))//替换下划线 _ ,参数类型(函数接口)：<R> Stream<R> map(Function<? super T, ? extends R> mapper);
            .flatMap(params->Stream.of(params.split("")))//通过拆分，组成新的 Stream
            .sorted()//再次排序
            .forEach((t)->System.out.println(t));

    }
}