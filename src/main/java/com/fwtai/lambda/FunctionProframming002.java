package com.fwtai.lambda;

import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * 函数式编程就像使用Excel表格计算最大值、最小值、总和一样的道理,我们不关心是怎么实现的，我们仅想要什么样的结果即可
 * @param 
 * @作者 田应平
 * @QQ 444141300
 * @创建时间 2021/5/25 20:50
*/
public class FunctionProframming002{

    public static void main(final String[] args){
        //todo 带类型的断言或Stream
        final IntPredicate predicate = (i -> i > 0);
        final boolean b = predicate.test(2);
        System.out.println(b);

        //todo 带类型的断言或Stream
        final IntConsumer consumer = (i) -> System.out.println(i);
        consumer.accept(5);

        IntStream.of(10,58,26,-21,22,100);
    }

    protected static void excel(){
        final int[] nums = {10,58,26,-21,22,100};
        final IntStream stream = IntStream.of(nums).parallel();// 方法parallel()并行,多核CPU的优势了
        //long count = stream.count();//todo 仅能调用一次,否则报错 stream has already been operated upon or closed
        //int max = stream.max().getAsInt();
        //int min = stream.min().getAsInt();
        int sum = stream.sum();

        //System.out.println("count->"+count);//6
        //System.out.println("max->"+max);//100
        //System.out.println("min->"+min);//-21
        System.out.println("sum->"+sum);//195
    }
}