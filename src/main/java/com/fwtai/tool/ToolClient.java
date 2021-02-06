package com.fwtai.tool;

import com.alibaba.fastjson.JSONObject;

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
}