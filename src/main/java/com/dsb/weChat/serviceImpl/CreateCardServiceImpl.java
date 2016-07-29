package com.dsb.weChat.serviceImpl;

import com.dsb.weChat.service.CreateCardService;
import com.dsb.weChat.util.http.HttpUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Max on 2016/7/27.
 */
public class CreateCardServiceImpl implements CreateCardService {

    /**
     *
     * @param file 图片logow文件
     * @param access_token 接口调用凭证
     * @return  logo的URL地址
     */
    @Override
    public String uploadCardLogo(File file, String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token;;
        String json = HttpUtil.doPostSSL(file, url);
        System.out.println("微信服务器返回的logo图片地址为:" + json);
        return json;
    }

    /**
     * @param cardEntity 卡券实体信息
     * @param access_token 接口调用凭证
     * @return 创建成功/失败json字符串
     */
    @Override
    public String createCard(Object cardEntity, String access_token) {
        String url = "https://api.weixin.qq.com/card/create?access_token=" + access_token;;
        JSONObject jsonObject = new JSONObject(cardEntity);
        String cardEntityJson = jsonObject.toString();
        String returnJson = HttpUtil.doPostSSL(url,cardEntityJson);
        System.out.println("微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }
}
