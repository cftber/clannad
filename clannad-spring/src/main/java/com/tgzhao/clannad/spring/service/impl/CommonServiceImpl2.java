package com.tgzhao.clannad.spring.service.impl;

import com.tgzhao.clannad.spring.service.ICommonService;

/**
 * Created by tgzhao on 2016/8/26.
 */
public class CommonServiceImpl2 implements ICommonService {
    @Override
    public String getTipMsgs(String tip) {
        return "CommonServiceImpl2 " + tip;
    }
}
