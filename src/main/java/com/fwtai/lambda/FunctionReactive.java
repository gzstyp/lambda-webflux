package com.fwtai.lambda;

import reactor.core.publisher.Flux;

import java.util.Comparator;

public class FunctionReactive{

    //返回一个包含每种类型最贵的数,采用响应式编程实现
    public static Flux<Book> getMostBooksByReactive(final Flux<Book> books){
        return books.collectMultimap(Book ::getCategory)
            .flatMapMany(map -> Flux.fromIterable(map.entrySet()))
            .flatMap(entry -> Flux.fromIterable(entry.getValue())
                .sort(Comparator.comparing(Book::getPrice).reversed())
                .next());
    }

    public static void main(final String[] args){
        final Flux<Book> pipeline = Flux.just(InMemoryDataSource.kooks);
        final Flux<Book> books = getMostBooksByReactive(pipeline);
        Flux<Book> bookFlux = books.doOnNext(System.out::println);
        System.out.println("什么都不会发生,直到pipeline开始");
        bookFlux.subscribe();
    }
}