package com.dsb.weChat.serviceImpl;

import com.dsb.utils.StaticConstant;
import com.dsb.weChat.service.CheckCardService;
import com.dsb.weChat.util.http.AccessUtil;
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
     * @param cardJson 封装了查询单张卡券的json
     * @return 单张卡券的状态json信息
     */
    @Override
    public String queryCardStage(String cardJson) {
        String url = "https://api.weixin.qq.com/card/code/get?access_token=" + StaticConstant.accessToken;
        String returnJson = HttpUtil.doPostSSL(url,cardJson);
        String errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("errmsg","该卡券可以使用");
            return json.toString();
        } else {

            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = (String)jsonObject.get("errcode");
                if (errCode.equals("0")) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("errmsg","该卡券可以使用");
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","该卡券已被使用或已被转赠");
                    return json.toString();
                }
            }
            else {
                String returnJson1 = HttpUtil.doPostSSL(url,cardJson);
                JSONObject JSONObject1 = new JSONObject(returnJson1);
                errCode = (String)JSONObject1.get("errcode");
                if (errCode.equals("0")) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("errmsg","该卡券可以使用");
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","该卡券已被使用或已被转赠");
                    return json.toString();
                }
            }
        }
    }

    /**
     * 根据code标识码来核销卡券
     * @param cardJson 封装了核销Code接口的json
     * @return 返回一个json字符串，主要包含：card_id 和 openId
     */
    @Override
    public String consumeCard(String cardJson) {
        String url = "https://api.weixin.qq.com/card/code/consume?access_token=" + StaticConstant.accessToken;
        String returnJson = HttpUtil.doPostSSL(url,cardJson);
        String errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("errmsg","核销成功");
            return json.toString();
        } else {

            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = (String)jsonObject.get("errcode");
                if (errCode.equals("0")) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("errmsg","核销成功");
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","核销失败");
                    return json.toString();
                }
            }
            else {
                String returnJson1 = HttpUtil.doPostSSL(url,cardJson);
                JSONObject JSONObject1 = new JSONObject(returnJson1);
                errCode = (String)JSONObject1.get("errcode");
                if (errCode.equals("0")) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("errmsg","核销成功");
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","核销失败");
                    return json.toString();
                }
            }
        }
    }
}
