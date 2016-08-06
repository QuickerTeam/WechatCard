package com.dsb.weChat.serviceImpl;

import com.dsb.utils.StaticConstant;
import com.dsb.weChat.service.ManageCardService;
import com.dsb.weChat.util.http.AccessUtil;
import com.dsb.weChat.util.http.HttpUtil;
import org.json.JSONObject;

/**
 * Created by Max on 2016/7/28.
 */
public class ManageCardServiceImpl implements ManageCardService {


    /**
     * 查询卡券的基本信息
     * @param cardJson 封装了查询单张基本信息的json
     * @return 返回卡券基本信息的json字符串
     */
    @Override
    public String queryCardInfo(String cardJson) {
        String url = "https://api.weixin.qq.com/card/get?access_token=" + StaticConstant.accessToken;
        String returnJson = HttpUtil.doPostSSL(url,cardJson);
        int errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("card",jsonObject.get("card"));
            return json.toString();
        } else {

            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("card",jsonObject.get("card"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","查询卡券基本信息失败");
                    return json.toString();
                }
            }
            else {
                String returnJson1 = HttpUtil.doPostSSL(url,cardJson);
                JSONObject JSONObject1 = new JSONObject(returnJson1);
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("card",jsonObject.get("card"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","查询卡券基本信息失败");
                    return json.toString();
                }
            }
        }
    }

    /**
     *
     * @param cardJson 封装了批量查询的json
     * @return 返回查询的卡券列表json信息
     */
    @Override
    public String batchGet(String cardJson) {
        String url = "https://api.weixin.qq.com/card/batchget?access_token=" + StaticConstant.accessToken;
        String returnJson = HttpUtil.doPostSSL(url,cardJson);
        int errCode;
        JSONObject json;

        JSONObject jsonObject = new JSONObject(returnJson);

        //不存在errcode字段，则意味着发送成功
        if (jsonObject.isNull("errcode")) {
            json = new JSONObject();
            json.put("status", true);
            json.put("card_id_list", jsonObject.getJSONArray("card_id_list"));
            json.put("total_num", jsonObject.getInt("total_num"));
            return json.toString();
        } else {

            //判断access_token是否有效
            if (AccessUtil.isValid(jsonObject)) {
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("card_id_list", jsonObject.getJSONArray("card_id_list"));
                    json.put("total_num", jsonObject.getInt("total_num"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","批量查询卡券列表失败");
                    return json.toString();
                }
            }
            else {
                String returnJson1 = HttpUtil.doPostSSL(url,cardJson);
                JSONObject JSONObject1 = new JSONObject(returnJson1);
                errCode = jsonObject.getInt("errcode");
                if (errCode == 0) {
                    json = new JSONObject();
                    json.put("status", true);
                    json.put("card_id_list", jsonObject.getJSONArray("card_id_list"));
                    json.put("total_num", jsonObject.getInt("total_num"));
                    return json.toString();
                }
                else {
                    json = new JSONObject();
                    json.put("status", false);
                    json.put("errmsg","批量查询卡券列表失败");
                    return json.toString();
                }
            }
        }
    }

    /**
     * 删除卡券接口
     * @param cardJson 封装了card id的json
     * @return 完成情况
     */
    @Override
    public String deleteCard(String cardJson) {
        String url = "https://api.weixin.qq.com/card/delete?access_token=" + StaticConstant.accessToken;
        String returnJson = HttpUtil.doPostSSL(url,cardJson);
        int errCode;
        JSONObject jsonObject = new JSONObject(returnJson);
        errCode = jsonObject.getInt("errcode");
        JSONObject json = new JSONObject();
        if (errCode == 0) {
            json.put("status",true);
            json.put("errmsg","删除成功");
        }
        else {
            json.put("status",false);
            json.put("errmsg","删除失败");
        }
        return json.toString();
    }
}
