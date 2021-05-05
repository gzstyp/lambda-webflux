package com.fwtai.tool;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

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

    private final static String KEY_CODE = "code";
    private final static String KEY_MSG ="msg";
    private final static String KEY_DATA = "data";
    private final static String KEY_RECORD = "record";

    public static String json199(){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,199);
        json.put(KEY_MSG,"操作失败");
        return json.toJSONString();
    }

    public static String json199(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,199);
        json.put(msg,msg);
        return json.toJSONString();
    }

    public static String json200(){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,200);
        json.put(KEY_MSG,"操作成功");
        return json.toJSONString();
    }

    public static String json200(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,200);
        json.put(KEY_MSG,msg);
        return json.toJSONString();
    }

    public static String json201(){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,201);
        json.put(KEY_MSG,"暂无数据");
        return json.toJSONString();
    }

    public static String json201(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,201);
        json.put(msg,msg);
        return json.toJSONString();
    }

    public static String json202(){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,202);
        json.put(KEY_MSG,"请求参数不完整");
        return json.toJSONString();
    }

    public static String json202(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,202);
        json.put(msg,msg);
        return json.toJSONString();
    }

    public static String json204(){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,204);
        json.put(KEY_MSG,"系统出现错误");
        return json.toJSONString();
    }

    public static String json204(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,204);
        json.put(msg,msg);
        return json.toJSONString();
    }

    public static String json205(){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,205);
        json.put(KEY_MSG,"未登录或登录超时");
        return json.toJSONString();
    }

    public static String json205(final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,205);
        json.put(msg,msg);
        return json.toJSONString();
    }

    protected static String createJson(final int code,final String msg){
        final JSONObject json = new JSONObject(2);
        json.put(KEY_CODE,code);
        json.put(KEY_MSG,msg);
        return json.toJSONString();
    }

    public static String json401(){
        return createJson(401,"没有操作权限");
    }

    public static String json401(final String msg){
        return createJson(401,msg);
    }

    public static String executeRows(final int rows){
        final JSONObject json = new JSONObject(3);
        if(rows > 0){
            json.put(KEY_CODE,200);
            json.put(KEY_MSG,"操作成功");
            json.put(KEY_DATA,rows);
            return json.toJSONString();
        }else{
            json.put(KEY_CODE,199);
            json.put(KEY_MSG,"操作失败");
            return json.toJSONString();
        }
    }

    public static String executeRows(final int rows,final String success){
        final JSONObject json = new JSONObject(3);
        if(rows > 0){
            json.put(KEY_CODE,200);
            json.put(KEY_MSG,success);
            json.put(KEY_DATA,rows);
            return json.toJSONString();
        }else{
            json.put(KEY_CODE,199);
            json.put(KEY_MSG,"操作失败");
            return json.toJSONString();
        }
    }

    public static String executeRows(final int rows,final String success,final String failure){
        final JSONObject json = new JSONObject(3);
        if(rows > 0){
            json.put(KEY_CODE,200);
            json.put(KEY_MSG,success);
            json.put(KEY_DATA,rows);
            return json.toJSONString();
        }else{
            json.put(KEY_CODE,199);
            json.put(KEY_MSG,failure);
            return json.toJSONString();
        }
    }

    public static String queryJson(final Object object){
        if(object == null || object.toString().trim().length() <= 0){
            return json201();
        }
        final JSONObject json = new JSONObject(3);
        if (object instanceof Exception) {
            json.put(KEY_CODE,204);
            json.put(KEY_MSG,"系统出现错误");
            json.put(KEY_DATA,object);
            return json.toJSONString();
        }
        if(object instanceof Map<?,?>){
            final Map<?,?> map = (Map<?,?>) object;
            if(map.size() <= 0){
                json201();
            }else {
                json.put(KEY_CODE,200);
                json.put(KEY_MSG,"操作成功");
                json.put(KEY_DATA,object);
                return json.toJSONString();
            }
        }
        if(object instanceof List<?>){
            final List<?> list = (List<?>) object;
            if(list.size() <= 0){
                return json201();
            }else {
                if (list.get(0) == null){
                    return json201();
                }else {
                    json.put(KEY_CODE,200);
                    json.put(KEY_MSG,"操作成功");
                    json.put(KEY_DATA,object);
                    json.put(KEY_RECORD,((List<?>) object).size());
                    final String jsonObj = json.toJSONString();
                    final JSONObject j = JSONObject.parseObject(jsonObj);
                    final String listData = j.getString(KEY_DATA);
                    if (listData.equals("[{}]")){
                        return json201();
                    }
                    return jsonObj;
                }
            }
        }
        if(String.valueOf(object).toLowerCase().equals("null") || String.valueOf(object).replaceAll("\\s*", "").length() == 0){
            return json201();
        }else {
            json.put(KEY_CODE,200);
            json.put(KEY_MSG,"操作成功");
            json.put(KEY_DATA,object);
            final String jsonObj = json.toJSONString();
            final JSONObject j = JSONObject.parseObject(jsonObj);
            final String obj = j.getString(KEY_DATA);
            if (obj.equals("{}")){
                return json201();
            }
            return jsonObj;
        }
    }

    /**
     * 基于函数接口调用
     * @param json 是json格式字符串
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2021/2/7 17:33
    */
    public static Mono<ServerResponse> responseJson(final String json){
        return ServerResponse.ok().contentType(new MediaType("text","html",StandardCharsets.UTF_8)).header("Cache-Control","no-cache").bodyValue(json);
    }

    /**
     * 不推荐使用
     * @param json 是经过 Mono.just(json) 处理后的字符串
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2021/2/7 17:34
    */
    protected static Mono<ServerResponse> responseJson(final Mono<String> json){
        return ServerResponse.ok().contentType(new MediaType("text","html",StandardCharsets.UTF_8)).body(json,String.class);
    }

    /**
     * 基于注解且仅在在controller层调用
     * @param msg 是字符串
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2021/2/7 17:29
    */
    public static Mono<String> responseAnnotatedMsg(final String msg){
        if(msg == null || msg.isEmpty()){
            return Mono.justOrEmpty(json201());
        }
        return Mono.justOrEmpty(json200(msg));
    }

    /**
     * 基于注解且仅在在controller层调用
     * @param json 是json格式的字符串
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2021/2/7 17:30
    */
    public static Mono<String> responseAnnotatedJson(final String json){
        return Mono.justOrEmpty(json);
    }

    // todo 解决IE8请求时出现下载的bug,推荐使用
    public static Mono<Void> responseAnnotatedJson(final String json,final ServerHttpResponse response){
        response.getHeaders().add("Content-Type","text/html;charset=utf-8");
        final DataBuffer db = response.bufferFactory().wrap(json.getBytes());
        return response.writeWith(Mono.just(db));
    }
}