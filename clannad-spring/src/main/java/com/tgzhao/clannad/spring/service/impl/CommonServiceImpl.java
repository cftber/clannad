package com.tgzhao.clannad.spring.service.impl;

import com.tgzhao.clannad.spring.dal.TipMsgDao;
import com.tgzhao.clannad.spring.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tgzhao on 2016/8/26.
 */
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private TipMsgDao tipMsgDao;

    @Override
    public String getTipMsgs(String tip) {
        return tipMsgDao.getTipMsg(tip);
    }
}
