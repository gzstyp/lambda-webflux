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

//基于函数接口 FunctionalInterface 实现 webflux 的方式,推荐!!!
@Component
public class FunctionalInterfaceController{

    // http://127.0.0.1:701/api/object
    @Bean
    public RouterFunction<ServerResponse> object(){
        return RouterFunctions.route().GET("/api/object",new HandlerFunction<ServerResponse>(){
            @Override
            public Mono<ServerResponse> handle(final ServerRequest request){
                final String json = ToolClient.json("基于函数接口 FunctionalInterface 实现 webflux 的方式");
                return ToolClient.responseJson(json);
            }
        }).build();
    }

    // http://127.0.0.1:701/api/login
    @Bean
    public RouterFunction<ServerResponse> login(){
        return RouterFunctions.route().GET("/api/login",request -> {
            final String json = ToolClient.json("login,基于函数接口 FunctionalInterface 实现 webflux 的方式");
            return ToolClient.responseJson(json);
        }).build();
    }

    // http://127.0.0.1:701/api/logout
    @Bean
    public RouterFunction<ServerResponse> logout(){
        return RouterFunctions.route().GET("/api/logout",request -> {
            return ToolClient.responseJson(ToolClient.json("logout,基于函数接口 FunctionalInterface 实现 webflux 的方式"));
        }).build();
    }

    // http://127.0.0.1:701/api/register
    @Bean
    public RouterFunction<ServerResponse> register(){
        return RouterFunctions.route().GET("/api/register",request -> ToolClient.responseJson(ToolClient.json("register,基于函数接口 FunctionalInterface 实现 webflux 的方式"))).build();
    }
}