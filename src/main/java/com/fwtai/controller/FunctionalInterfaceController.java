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

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//基于函数接口 FunctionalInterface 实现 webflux 的方式,推荐!!!
@Component
public class FunctionalInterfaceController{

    // http://127.0.0.1:701/api/object
    @Bean
    public RouterFunction<ServerResponse> object(){
        return RouterFunctions.route().GET("/api/object",new HandlerFunction<ServerResponse>(){
            @Override
            public Mono<ServerResponse> handle(final ServerRequest request){
                final Optional<String> cityParamOptional = request.queryParam("userName");
                if (!cityParamOptional.isPresent()){
                    return ServerResponse.status(500).bodyValue("参数有误");
                }
                final String userName = cityParamOptional.get();
                final String json = ToolClient.json200("基于函数接口 FunctionalInterface 实现 webflux 的方式,userName->"+userName);
                return ToolClient.responseJson(json);
            }
        }).build();
    }

    // http://127.0.0.1:701/api/login?userName=typ
    @Bean
    public RouterFunction<ServerResponse> login(){
        return RouterFunctions.route().GET("/api/login",request -> {
            final Optional<String> cityParamOptional = request.queryParam("userName");
            if (!cityParamOptional.isPresent()){
                return ServerResponse.status(500).bodyValue("参数有误");
            }
            final String userName = cityParamOptional.get();
            final String json = ToolClient.json200("login,基于函数接口 FunctionalInterface 实现 webflux 的方式,userName->"+userName);
            return ToolClient.responseJson(json);
        }).build();
    }

    // http://127.0.0.1:701/api/logout
    @Bean
    public RouterFunction<ServerResponse> logout(){
        return RouterFunctions.route().GET("/api/logout",request -> {
            return ToolClient.responseJson(ToolClient.json200("logout,基于函数接口 FunctionalInterface 实现 webflux 的方式"));
        }).build();
    }

    // http://127.0.0.1:701/api/register
    @Bean
    public RouterFunction<ServerResponse> register(){
        return RouterFunctions.route().GET("/api/register",request -> ToolClient.responseJson(ToolClient.json200("register,基于函数接口 FunctionalInterface 实现 webflux 的方式"))).build();
    }

    // 多路由分发 http://127.0.0.1:701/multiple/login?userName=typ http://127.0.0.1:701/multiple/logout
    @Bean
    public RouterFunction<ServerResponse> multipleRouter(){
        // 即用什么方法去处理它!!!可以参考本项目目录下的 项目 webflux
        return route(GET("/multiple/login"),request -> {
            final Optional<String> userNameOptional = request.queryParam("userName");
            if(!userNameOptional.isPresent()){
                return ToolClient.responseJson(ToolClient.json199("请求参数有误,多路由分发,基于函数接口 FunctionalInterface 实现 webflux 的方式"));
            }
            final String userName = userNameOptional.get();
            return ToolClient.responseJson(ToolClient.json200("login,多路由分发,基于函数接口 FunctionalInterface 实现 webflux 的方式,userName->"+userName));
        })
        // 即用什么方法去处理它!!!可以参考本项目目录下的 项目 webflux
        .andRoute(GET("/multiple/logout"),request -> {
            return ToolClient.responseJson(ToolClient.json200("logout,多路由分发,基于函数接口 FunctionalInterface 实现 webflux 的方式"));
        });
    }
}