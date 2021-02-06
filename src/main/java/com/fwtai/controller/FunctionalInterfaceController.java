package com.fwtai.controller;

import com.fwtai.tool.ToolClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

//基于函数接口 FunctionalInterface 实现 webflux 的方式
@Component
public class FunctionalInterfaceController{

    // http://127.0.0.1:701/functionalInterface/object
    @Bean
    public RouterFunction<ServerResponse> object(){
        return RouterFunctions.route().GET("/functionalInterface/object",new HandlerFunction<ServerResponse>(){
            @Override
            public Mono<ServerResponse> handle(final ServerRequest request){
                final String json = ToolClient.json("基于函数接口 FunctionalInterface 实现 webflux 的方式");
                return ToolClient.responseJson(json);
            }
        }).build();
    }
}