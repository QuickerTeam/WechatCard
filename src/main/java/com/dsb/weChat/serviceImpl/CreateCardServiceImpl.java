package com.dsb.weChat.serviceImpl;

import java.io.File;

import org.json.JSONObject;

import com.dsb.utils.StaticConstant;
import com.dsb.weChat.service.CreateCardService;
import com.dsb.weChat.util.http.AccessUtil;
import com.dsb.weChat.util.http.HttpUtil;

/**
 * Created by Max on 2016/7/27.
 */
public class CreateCardServiceImpl implements CreateCardService {

    /**
     * @param file         图片logow文件
     * @return logo的URL地址
     */
    @Override
    public String uploadCardLogo(File file) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="
                + StaticConstant.accessToken;;
        String returnJson = HttpUtil.doPostSSL(file, url);
        int errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("url",  jsonObject.getString("url"));
            return json.toString();
        } else {
            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("url", jsonObject.getString("url"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg", "上传图片失败");
                    return json.toString();
                }
            }
            else {
            	url = "https://api.weixin.qq.com/card/batchget?access_token="
						+ StaticConstant.accessToken;
                String returnJson1 = HttpUtil.doPostSSL(file, url);
                JSONObject JSONObject1 = new JSONObject(returnJson1);
                errCode = JSONObject1.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("url", jsonObject.getString("url"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg", "上传图片失败");
                    return json.toString();
                }
            }
        }
}


    /**
     * @param javaBeenJson 封封装了创建卡券基本信息的java been对象的json
     * @return 创建成功/失败json字符串
     */
    @Override
    public String createCard(String javaBeenJson) {
        String url = "https://api.weixin.qq.com/card/create?access_token=" + StaticConstant.accessToken;
        String returnJson = HttpUtil.doPostSSL(url, javaBeenJson);
        int errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("card_id", jsonObject.getString("card_id"));
            return json.toString();
        } else {

            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("card_id", jsonObject.getString("card_id"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg", "创建卡券失败");
                    return json.toString();
                }
            }
            else {
                String returnJson1 = HttpUtil.doPostSSL(url, javaBeenJson);
                JSONObject JSONObject1 = new JSONObject(returnJson1);
                errCode = JSONObject1.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("card_id", jsonObject.getString("card_id"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg", "创建卡券失败");
                    return json.toString();
                }
            }
        }
    }
}
