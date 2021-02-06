package com.fwtai.controller;

import com.fwtai.tool.ToolClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

//基于注解实现 webflux 的方式,已解决IE8请求时出现下载的bug
@RestController
@RequestMapping("/annotated")
public class WebfluxAnnotatedController{

    // http://127.0.0.1:701/annotated/object/10
    @GetMapping(value = "/object/{id}",produces = MediaType.TEXT_HTML_VALUE)//解决IE8请求时出现下载的bug
    public Mono<String> user(@PathVariable String id){
        final String json = ToolClient.json("基于注解实现Webflux,id="+id);
        return Mono.just(json);
    }

    // http://127.0.0.1:701/annotated/list
    @GetMapping(value = "/list",produces = MediaType.TEXT_HTML_VALUE)//解决IE8请求时出现下载的bug
    public Flux<String> list(){
        final ArrayList<String> list = new ArrayList<>();
        list.add("object");
        list.add(",基于注解实现 webflux 的方式");
        return Flux.fromIterable(list);
    }
}