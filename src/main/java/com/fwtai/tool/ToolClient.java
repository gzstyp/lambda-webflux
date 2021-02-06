package com.fwtai.tool;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 响应客户端
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021-02-06 18:50
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
 */
public final class ToolClient{

    public static String json(final String data){
        final JSONObject json = new JSONObject();
        json.put("code",200);
        json.put("data",data);
        return json.toString();
    }

    public static Mono<ServerResponse> responseJson(final String json){
         return ServerResponse.ok().contentType(new MediaType("text","html",StandardCharsets.UTF_8)).header("Cache-Control","no-cache").bodyValue(json);
    }

    protected Mono<ServerResponse> responseJson(final Mono<String> json){
        return ServerResponse.ok().contentType(new MediaType("text","html",StandardCharsets.UTF_8)).body(json,String.class);
    }
}