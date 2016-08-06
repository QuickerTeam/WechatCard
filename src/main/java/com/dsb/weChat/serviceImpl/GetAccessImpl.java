package com.dsb.weChat.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import com.dsb.weChat.service.GetAccess;
import com.dsb.weChat.util.http.HttpUtil;

/**
 * Created by Max on 2016/7/27.
 */

public class GetAccessImpl implements GetAccess{

    /**
     *
     * @param appId  第三方用户唯一凭证
     * @param secret 第三方用户唯一凭证密钥
     * @return access_token 接口调用凭证
     * 请求方式： GET
     */
    @Override
    public String getAccessToken(String appId, String secret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String,String> params = new HashMap<String,String>();
        params.put("grant_type","client_credential");
        params.put("appid",appId);
        params.put("secret",secret);
        String json = HttpUtil.doGetSSL(url,params);
        System.out.println("获取到的access_token为： " + json);
        return json;
    }
}
