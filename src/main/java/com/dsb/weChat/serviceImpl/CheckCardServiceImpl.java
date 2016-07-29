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
     * @param code 单张卡券的code码标识
     * @param card_id 卡券ID
     * @param check_consume 是否校验code核销状态，填入true和false时的code异常状态返回数据不同。
     * @return 单张卡券的状态json信息
     */
    @Override
    public String queryCardStage(String access_token, String code, String card_id, boolean check_consume) {
        String url = "https://api.weixin.qq.com/card/code/get?access_token=" + access_token;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("card_id",card_id);
        jsonObject.put("check_consume",check_consume);
        String json = jsonObject.toString();
        String returnJson = HttpUtil.doPostSSL(url,json);
        System.out.println("微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }

    /**
     * 根据code标识码来核销卡券
     * @param access_token
     * @param code
     * @return 返回一个json字符串，主要包含：card_id 和 openId
     */
    @Override
    public String consumeCard(String access_token,String code) {
        String url = "https://api.weixin.qq.com/card/code/consume?access_token=" + access_token;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        String json = jsonObject.toString();
        String returnJson = HttpUtil.doPostSSL(url,json);
        System.out.println("微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }
}
