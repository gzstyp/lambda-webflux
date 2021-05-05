package com.fwtai.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 常用的jdk8自带的函数接口,箭头→的左边的输入(参数),右边是输出(返回值)
 * 一般情况下,若有2个参数时是既有输入参数又有返回结果，即输入是T，返回的是R,Function<T,R>
 * 高阶函数:即返回函数的函数
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021/2/6 11:08
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
public final class JDK8Lambda{

    public static void main(final String[] args){
        fun3();
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

    //既有输入参数又有返回结果，输入是T，返回的是R,若有2个参数时是既有输入参数又有返回结果，即输入是T，返回的是R,Function<T,R>
    protected static void function(){
        final Function<String,String> function1 = (result)-> result;
        final String r = function1.apply("既有输入参数又有返回结果，输入是T，返回的是R");//传入参数,r接收的是返回值
        final Function<Integer,Integer> function2 = (result)-> result * result;
        final Function<Integer,Integer> function = (result)-> {return result * result;};//todo 也是ok
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
        final Predicate<Integer> predicate = (params) -> check(params);//可以写成 final Predicate<Integer> predicate = JDK8Lambda::check;这种写法仅支持一个参数
        final boolean b = predicate.test(1);
        System.out.println("b = " + b);
    }

    protected static boolean check(final Integer params){
        return params > 0;
    }

    //级联表达式,参数为‘函数接口’,而参数又是'函数接口'，解释:第1个t是第1个Function<T,R>的第1个输入参数T;r是返回值且又是函数接口类型Function<T,R>
    //柯里化：把多个参数的函数转换为只有一个参数的函数
    protected static void fun2(){
        final Function<Integer,Function<Integer,Integer>> fun = t -> r -> t + r;//箭头→的左边的输入(参数),右边是输出(返回值)
        final Integer result = fun.apply(10).apply(20);
        System.out.println("result = " + result);// result = 30
    }

    //级联表达式和柯里化,参数里又是函数接口的参数
    protected static void fun3(){
        final Function<Integer,Function<Integer,Function<Integer,Integer>>> fun = x -> y -> z -> x + y + z;
        final Integer result = fun.apply(10).apply(20).apply(30);
        System.out.println("result = " + result);// result = 60
        final int[] arrays = {2,3,4};
        Function fn = fun;//高阶函数:即返回函数的函数
        for(int x = 0; x < arrays.length; x++){
            if(fn instanceof Function){
                final Object obj = fn.apply(arrays[x]);
                if(obj instanceof Function){
                    fn = (Function)obj;
                }else{
                    System.out.println("调用结束,其值 obj = " + obj);// 9
                }
            }
        }
    }

    //lambda示例代码,实战
    /*
     protected static void start(){
        final ConfigStoreOptions config = new ConfigStoreOptions()
          .setType("file")
          .setFormat("json")
          .setConfig(new JsonObject().put("path","config.json"));//当然也可以再创建一个
        final ConfigRetrieverOptions opts = new ConfigRetrieverOptions()
          .addStore(config);//当然可以根据上面再创建多个可以添加多个
        final ConfigRetriever cfgRetrieve = ConfigRetriever.create(vertx,opts);

        //方式1,参数类型:void getConfig(Handler<AsyncResult<JsonObject>> completionHandler);//都是函数接口类型,仅有输入参数无返回输出,ok
            cfgRetrieve.getConfig(asyncResult ->{
            this.configHandle(start,router,asyncResult);
        });

        final Handler<AsyncResult<JsonObject>> handler = asyncResult -> configHandle(start,router,asyncResult);
        //方式2,参数类型:void getConfig(Handler<AsyncResult<JsonObject>> completionHandler);//都是函数接口类型,仅有输入参数无返回输出,ok
        cfgRetrieve.getConfig(handler);
     }

     protected void configHandle(final Promise<Void> start,final Router router,final AsyncResult<JsonObject> asyncResult){
        if(asyncResult.succeeded()){
          final JsonObject jsonObject = asyncResult.result();//请注意json文件格式数据,{"http":{"port":803}}
          final JsonObject http = jsonObject.getJsonObject("http");// {"port":803}
          final Integer httpPort = http.getInteger("port",801);
          vertx.createHttpServer().requestHandler(router).listen(httpPort);
          start.complete();
        }else{
          start.fail("应用启动失败");
        }
      }
    */
}