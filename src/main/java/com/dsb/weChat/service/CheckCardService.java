package com.dsb.weChat.service;

/**
 * Created by Max on 2016/7/27.
 */
public interface CheckCardService {

    //查询单张卡券状态信息
    String queryCardStage(String access_token, String code, String card_id, boolean check_consume);

}
