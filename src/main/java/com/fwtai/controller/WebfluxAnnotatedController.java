package com.fwtai.controller;

import com.fwtai.tool.ToolClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

//基于注解实现 webflux 的方式
@RestController
@RequestMapping("/annotated")
public class WebfluxAnnotatedController{

    // http://127.0.0.1:701/annotated/object
    @GetMapping("/object")
    public Mono<String> user(final ServerRequest request){
        final String json = ToolClient.json("基于注解实现Webflux");
        return Mono.just(json);
    }

    // http://127.0.0.1:701/annotated/list
    @GetMapping("/list")
    public Flux<String> list(final ServerRequest request){
        final ArrayList<String> list = new ArrayList<>();
        list.add("object");
        list.add(",基于注解实现 webflux 的方式");
        return Flux.fromIterable(list);
    }
}