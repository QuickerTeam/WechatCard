package com.dsb.weChat.service;

/**
 * Created by Max on 2016/7/28.
 */
public interface ManageCardService {

    //查看卡券的基本信息
    String queryCardInfo(String access_token,String card_id);

    //批量获取卡券列表

    String batchGet(String access_token, String json);


}

