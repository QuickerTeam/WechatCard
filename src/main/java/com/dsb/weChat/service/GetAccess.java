package com.dsb.weChat.service;

/**
 * Created by Max on 2016/7/27.
 */
public interface GetAccess {

    //获取微信调用接口凭证access_token
    String getAccessToken(String appId, String secret);
}
