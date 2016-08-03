package com.dsb.weChat.util.http;

import com.dsb.utils.StaticConstant;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Max on 2016/8/3.
 */
public class GetAccessUtil {

    private static String appId;
    private static String appSecret;
    private static String url;

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        GetAccessUtil.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        GetAccessUtil.appSecret = appSecret;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        GetAccessUtil.url = url;
    }

    //当access_token失效后，重新获取access_token
    @PostConstruct
    public static String getAccess() {
        Map<String,String> params = new HashMap<String,String>();
        params.put("grant_type","client_credential");
        params.put("appid",appId);
        params.put("secret",appSecret);
        String json = HttpUtil.doGetSSL(url,params);
        JSONObject jsonObject = new JSONObject(json);
        String access_token = (String) jsonObject.get("access_token");
        StaticConstant.accessToken = access_token;
        System.out.println("初始化web容器后，获得的access_token为: " + access_token);
        return access_token;
    }
}
