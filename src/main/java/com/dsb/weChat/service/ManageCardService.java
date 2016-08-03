package com.dsb.weChat.service;

/**
 * Created by Max on 2016/7/28.
 */
public interface ManageCardService {

    //查看卡券的基本信息
    String queryCardInfo(String cardJson);

    //批量获取卡券列表

    String batchGet(String cardJson);


}

