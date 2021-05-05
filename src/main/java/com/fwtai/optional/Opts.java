package com.fwtai.optional;

import java.util.Optional;

/**
 * Optional结合Lambda一起结合使用
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021/3/8 11:19
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
public final class Opts{

    public static void main(final String[] args){
        final String value = null;
        final boolean bl = Optional.ofNullable(value).map(v -> false).orElse(true);
        System.out.println(bl);
    }

    public static void normal(){
        final User user = new User(1,"田卓智");
        if(user.getName() != null){
            System.out.println(user.getName());
        }
    }

    //Optional的用法
    public static void optional(){
        final User user = new User(1,"say");
        final Optional<User> optionalUser = Optional.ofNullable(user);
        if (optionalUser.isPresent()){
            System.out.println(optionalUser.get().getName());
        }
        User user1 = null ;
        User createUser = Optional.ofNullable(user1).orElse(createUser());
        System.out.println(createUser.getName());
        User user2 = null ;
        Optional.ofNullable(user2).orElseThrow(()-> new RuntimeException("系统出现错误"));//模拟空指针异常
    }

    private static User createUser(){
        return new User(2,"田应平") ;
    }

    //Optional结合Lambda一起结合使用
    public static void optionalLambda(){
        // 1、map转换方法
        final User user = new User(99,null);//参数为 java 试试
        // user = null ;
        final String name = Optional.ofNullable(user).map(u -> u.getName()).orElse("MySQL");//todo
        System.out.println(name);
        // 2、过滤方法
        final Optional<User> optUser = Optional.ofNullable(user).filter(u -> u.getName() != null && u.getName().contains("Oracle"));
        // NoSuchElementException
        System.out.println(optUser.get().getName());//模拟空指针异常
    }
}

class User{

    private Integer id;
    private String name;

    public User(final Integer id,final String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}