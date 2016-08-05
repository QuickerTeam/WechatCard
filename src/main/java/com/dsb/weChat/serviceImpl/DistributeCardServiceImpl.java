package com.dsb.weChat.serviceImpl;

import com.dsb.utils.StaticConstant;
import com.dsb.weChat.service.DistributeCardService;
import com.dsb.weChat.util.http.AccessUtil;
import com.dsb.weChat.util.http.HttpUtil;
import org.json.JSONObject;

/**
 * Created by Max on 2016/8/1.
 */
public class DistributeCardServiceImpl implements DistributeCardService {

    /**
     * 获取卡券的二维码
     * @param QRCodejson 封装的json信息
     * @return 返回的json信息
     */
    @Override
    public String getQRCode(String QRCodejson) {
        String url = "https://api.weixin.qq.com/card/qrcode/create?access_token=" + StaticConstant.accessToken;
        String returnJson = HttpUtil.doPostSSL(url,QRCodejson);

        int errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("expire_seconds", jsonObject.getInt("expire_seconds"));
            json.put("url",  jsonObject.getString("url"));
            json.put("show_qrcode_url", jsonObject.getString("show_qrcode_url"));
            return json.toString();
        } else {

            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("expire_seconds",jsonObject.getInt("expire_seconds"));
                    json.put("url", jsonObject.getString("url"));
                    json.put("show_qrcode_url", jsonObject.getString("show_qrcode_url"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","生成二维码失败");
                    return json.toString();
                }
            }
            else {
                String returnJson1 = HttpUtil.doPostSSL(url,QRCodejson);
                JSONObject JSONObject1 = new JSONObject(returnJson1);
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("expire_seconds",jsonObject.getInt("expire_seconds"));
                    json.put("url", jsonObject.getString("url"));
                    json.put("show_qrcode_url", jsonObject.getString("show_qrcode_url"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","生成二维码失败");
                    return json.toString();
                }
            }
        }
    }
}
