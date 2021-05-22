package com.fwtai.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionProframming001{

    //返回一个包含每种类型最贵的数,采用非函数式编程实现
    public static List<Book> getMostBooksByCategory(){
        final HashMap<String,Book> map = new HashMap<>();
        for(final Book book : InMemoryDataSource.kooks){
            final Book _book = map.get(book.getCategory());
            if(_book != null){
                if(book.getPrice().compareTo(_book.getPrice()) > 0){
                    map.put(book.getCategory(),book);
                }
            }else{
                map.put(book.getCategory(),book);
            }
        }
        return new ArrayList<>(map.values());
    }

    //返回一个包含每种类型最贵的数,采用函数式编程实现
    public static List<Book> getMostBooksByCategoryFunction(){
        return Stream.of(InMemoryDataSource.kooks)
            .collect(Collectors.groupingBy(Book::getCategory))
            .entrySet()
            .stream()
            .map((k)->{
            return k.getValue()
                .stream()
                .sorted(Comparator.comparing(Book::getPrice).reversed()).findFirst().get();
        }).collect(Collectors.toList());
    }

    public static void main(String[] args){
        final List<Book> list1 = getMostBooksByCategory();
        list1.forEach(System.out::println);
        System.out.println("--------------------------");
        final List<Book> list2 = getMostBooksByCategoryFunction();
        list2.forEach(System.out::println);
    }
}