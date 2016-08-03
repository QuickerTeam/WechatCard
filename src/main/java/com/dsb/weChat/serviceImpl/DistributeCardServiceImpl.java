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

        String errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("expire_seconds",(String) jsonObject.get("expire_seconds"));
            json.put("url",(String) jsonObject.get("url"));
            json.put("show_qrcode_url",(String) jsonObject.get("show_qrcode_url"));
            return json.toString();
        } else {

            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = (String)jsonObject.get("errcode");
                if (errCode.equals("0")) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("expire_seconds",(String)  jsonObject.get("expire_seconds"));
                    json.put("url",(String) jsonObject.get("url"));
                    json.put("show_qrcode_url",(String) jsonObject.get("show_qrcode_url"));
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
                errCode = (String)JSONObject1.get("errcode");
                if (errCode.equals("0")) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("expire_seconds", (String) jsonObject.get("expire_seconds"));
                    json.put("url",(String) jsonObject.get("url"));
                    json.put("show_qrcode_url",(String) jsonObject.get("show_qrcode_url"));
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
