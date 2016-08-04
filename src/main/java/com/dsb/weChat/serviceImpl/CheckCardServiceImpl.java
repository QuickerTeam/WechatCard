package com.dsb.weChat.serviceImpl;

import com.dsb.weChat.service.CheckCardService;
import com.dsb.weChat.util.http.HttpUtil;
import org.json.JSONObject;

/**
 * Created by Max on 2016/7/27.
 */
public class CheckCardServiceImpl implements CheckCardService{


    /**
     *查询单张卡券状态信息：
     * NORMAL 正常 CONSUMED 已核销 EXPIRE 已过期
     * GIFTING 转赠中GIFT_TIMEOUT 转赠超时 DELETE 已删除，UNAVAILABLE 已失效；
     *
     * @param access_token 接口调用凭证
     * @param json 封装了查询单张卡券的json
     * @return 单张卡券的状态json信息
     */
    @Override
    public String queryCardStage(String access_token, String json) {
        String url = "https://api.weixin.qq.com/card/code/get?access_token=" + access_token;
        String returnJson = HttpUtil.doPostSSL(url,json);
        System.out.println("queryCardStage()接口，微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }

    /**
     * 根据code标识码来核销卡券
     * @param access_token 接口调用凭证
     * @param json 封装了核销Code接口的json
     * @return 返回一个json字符串，主要包含：card_id 和 openId
     */
    @Override
    public String consumeCard(String access_token,String json) {
        String url = "https://api.weixin.qq.com/card/code/consume?access_token=" + access_token;
        String returnJson = HttpUtil.doPostSSL(url,json);
        System.out.println("consumeCard()接口，微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }
}
