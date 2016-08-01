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
    public String uploadCardLogo(String access_token, File file ) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token;;
        String json = HttpUtil.doPostSSL(file, url);
        System.out.println("uploadCardLogo()接口，微信服务器返回的logo图片地址为:" + json);
        return json;
    }

    /**
     * @param json 封封装了创建卡券基本信息的java been对象的json
     * @param access_token 接口调用凭证
     * @return 创建成功/失败json字符串
     */
    @Override
    public String createCard(String access_token, String json ) {
        String url = "https://api.weixin.qq.com/card/create?access_token=" + access_token;;
        String returnJson = HttpUtil.doPostSSL(url,json);
        System.out.println("createCard()接口，微信服务器返回的json字段为：" + returnJson);
        return returnJson;
    }
}
