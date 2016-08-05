package com.dsb.weChat.service;

import java.io.File;

/**
 * Created by Max on 2016/7/27.
 */
public interface CreateCardService {

    //创建卡券第一步：上传卡券logo，得到url
    String uploadCardLogo(File file);

    //创建卡券第二步：向微信服务器发送json数据，返回创建信息
    String createCard(String javaBeenJson);

}
