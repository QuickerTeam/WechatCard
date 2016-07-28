package com.dsb.weChat.serviceImpl;

import com.dsb.weChat.service.GetAccess;
import com.dsb.weChat.util.http.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Max on 2016/7/27.
 */
public class GetAccessImpl implements GetAccess{

    CloseableHttpClient client;
    HttpGet get;
    CloseableHttpResponse response;
    RequestConfig requestConfig;
    HttpEntity entity;

    public GetAccessImpl() {
        client = HttpClients.createDefault();
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(15000)
                .setConnectTimeout(15000)
                .setConnectionRequestTimeout(15000)
                .build();
    }



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

    public static void main(String[] args) {
        GetAccessImpl getAccess = new GetAccessImpl();
        String appID = "wxa6e1bbd8c3101e94";
        String secret = "b46bd37e33f913a528141e32286e27be";
        getAccess.getAccessToken(appID,secret);
    }
}
