package com.dsb.weChat.serviceImpl;

import com.dsb.weChat.service.DistributeCardService;
import com.dsb.weChat.util.http.HttpUtil;
import org.json.JSONObject;

/**
 * Created by Max on 2016/8/1.
 */
public class DistributeCardServiceImpl implements DistributeCardService {

    /**
     * 获取卡券的二维码
     * @param json 封装的json信息
     * @param access_token 接口调用凭证
     * @return 返回的json信息
     */
    @Override
    public String getQRCode(String json, String access_token) {
        String url = "https://api.weixin.qq.com/card/qrcode/create?access_token=" + access_token;
        String returnJson = HttpUtil.doPostSSL(url,json);
        System.out.println("getQRCode()接口，微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }
}
