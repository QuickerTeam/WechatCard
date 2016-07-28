package com.dsb.weChat.service;

import java.io.InputStream;

/**
 * Created by Max on 2016/7/27.
 */
public interface CreateCardService {

    //创建卡券第一步：上传卡券logo，得到url
    String uploadCardLogo(InputStream inputStream, String access_token);

    //创建卡券第二步：向微信服务器发送json数据，返回创建信息
    String createCard(Object cardEntity, String access_token);

}
