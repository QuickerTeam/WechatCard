package com.dsb.weChat.serviceImpl;

import com.dsb.weChat.service.CardCreateService;

import java.io.InputStream;

/**
 * Created by Max on 2016/7/27.
 */
public class CardCreateServiceImpl implements CardCreateService {

    /**
     *
     * @param inputStream 图片logo的输入流
     * @param access_token 接口调用凭证
     * @return  logo的URL地址
     */
    @Override
    public String uploadCardLogo(InputStream inputStream, String access_token) {


        return null;
    }

    /**
     *
     * @param cardEntity 卡券实体信息
     * @param access_token 接口调用凭证
     * @return 创建成功/失败json字符串
     */
    @Override
    public String createCard(Object cardEntity, String access_token) {


        return null;
    }
}
