package com.fwtai.controller;

import com.fwtai.tool.ToolClient;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 基于注解实现 webflux 的方式,已解决IE8请求时出现下载的bug
 * @注意 返回值 Mono的个数 0和或1个;而Flux则可以是0个或N个
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021/2/7 1:24
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
@RestController
@RequestMapping("/annotated")
public class WebfluxAnnotatedController{

    // 获取请求参数,仅能接收简单的类型???复杂类型用'函数接口'来处理? http://127.0.0.1:701/annotated/get/10
    @GetMapping(value = "/get/{id}",produces = MediaType.TEXT_HTML_VALUE)//todo 解决IE8请求时出现下载的bug
    public Mono<String> getRest(@PathVariable final String id){//可以指定name @PathVariable(name = "id") final String id
        return ToolClient.responseAnnotatedMsg("基于注解实现Webflux,id="+id);
    }

    // http://127.0.0.1:701/annotated/json
    @GetMapping(value = "/json")
    public Mono<Void> json(final ServerHttpResponse response){//todo 解决IE8请求时出现下载的bug,推荐使用
        final String json = "{\"code\":200,\"msg\":\"操作成功\"}";
        return ToolClient.responseAnnotatedJson(json,response);
    }

    // http://127.0.0.1:701/annotated/listjson
    @GetMapping(value = "/listjson")
    public Mono<Void> list1(final ServerHttpResponse response){
        response.getHeaders().add("Content-Type","text/html;charset=utf-8");//todo 解决IE8请求时出现下载的bug,推荐使用
        final String msg = "{\"code\":200,\"msg\":\"操作成功\"}";
        final DataBuffer db = response.bufferFactory().wrap(msg.getBytes());
        return response.writeWith(Mono.just(db));
    }

    @GetMapping(value = "/get",produces = MediaType.TEXT_HTML_VALUE)//解决IE8请求时出现下载的bug,推荐使用这种
    public Mono<String> get(final String id){//可以指定name @PathVariable(name = "id") final String id
        return ToolClient.responseAnnotatedMsg(id);
    }

    // 获取请求头,http://127.0.0.1:701/annotated/list 一切转为json字符串再返回响应客户端,不推荐使用这种
    @GetMapping(value = "/list",produces = MediaType.TEXT_HTML_VALUE)//解决IE8请求时出现下载的bug
    public Flux<String> list(@RequestHeader(name = "accessToken",required = false) String header){
        final ArrayList<String> list = new ArrayList<>();
        list.add("object");
        list.add(",基于注解实现 webflux 的方式,header->"+header);
        return Flux.fromIterable(list);
    }

    // http://127.0.0.1:701/annotated/jsonList 一切转为json字符串再返回响应客户端,推荐使用这种
    @GetMapping(value = "/jsonList",produces = MediaType.TEXT_HTML_VALUE)//解决IE8请求时出现下载的bug
    public Mono<String> jsonList(@RequestHeader(name = "accessToken",required = false) String header){
        final ArrayList<String> list = new ArrayList<>();
        list.add("object");
        list.add("基于注解实现 webflux 的方式,header->"+header);
        final String json = ToolClient.queryJson(list);
        return ToolClient.responseAnnotatedJson(json);
    }

    // http://127.0.0.1:701/annotated/listMap 一切转为json字符串再返回响应客户端,推荐使用这种
    @GetMapping(value = "/listMap",produces = MediaType.TEXT_HTML_VALUE)//解决IE8请求时出现下载的bug
    public Mono<String> listMap(@RequestHeader(name = "accessToken",required = false) String header){
        final ArrayList<HashMap<String,String>> list = new ArrayList<>();
        final HashMap<String,String> map = new HashMap<>();
        map.put("kid","19850117");
        map.put("addr","基于注解实现 webflux 的方式,header->");
        map.put("header",header);
        list.add(map);
        final String json = ToolClient.queryJson(list);
        return ToolClient.responseAnnotatedJson(json);
    }

    // http://127.0.0.1:701/annotated/map 一切转为json字符串再返回响应客户端,推荐使用这种
    @GetMapping(value = "/map",produces = MediaType.TEXT_HTML_VALUE)//解决IE8请求时出现下载的bug
    public Mono<String> map(@RequestHeader(name = "accessToken",required = false) String header){
        final HashMap<String,String> map = new HashMap<>();
        map.put("kid","19850117");
        map.put("addr","基于注解实现 webflux 的方式,header->");
        map.put("header",header);
        final String json = ToolClient.queryJson(map);
        return ToolClient.responseAnnotatedJson(json);
    }
}