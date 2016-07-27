package com.dsb.weChat.serviceImpl;

import com.dsb.weChat.service.CheckCardService;

/**
 * Created by Max on 2016/7/27.
 */
public class CheckCardServiceImpl implements CheckCardService{


    /**
     *
     * @param access_token 接口调用凭证
     * @param code 单张卡券的code码标识
     * @param card_id 卡券ID
     * @param check_consume 是否校验code核销状态，填入true和false时的code异常状态返回数据不同。
     * @return 单张卡券的状态json信息
     */
    @Override
    public String queryCardStage(String access_token, String code, String card_id, boolean check_consume) {



        return null;
    }
}
