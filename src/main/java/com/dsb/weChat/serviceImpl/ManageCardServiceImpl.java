package com.dsb.weChat.serviceImpl;

import com.dsb.weChat.service.ManageCardService;
import com.dsb.weChat.util.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Max on 2016/7/28.
 */
public class ManageCardServiceImpl implements ManageCardService {


    /**
     * 查询卡券的基本信息
     * @param access_token 接口调用凭证
     * @param json 封装了查询单张基本信息的json
     * @return 返回卡券基本信息的json字符串
     */
    @Override
    public String queryCardInfo(String access_token, String json) {
        String url = "https://api.weixin.qq.com/card/get?access_token=" + access_token;
        String returnJson = HttpUtil.doPostSSL(url,json);
        return returnJson;
    }

    /**
     *
     * @param access_token 接口调用凭证
     * @param json 封装了批量查询的json
     * @return 返回查询的卡券列表json信息
     */
    @Override
    public String batchGet(String access_token, String json) {
        String url = "https://api.weixin.qq.com/card/batchget?access_token=" + access_token;
        String returnJson = HttpUtil.doPostSSL(url,json);
        System.out.println("batchGet()接口，微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }
}
