package com.tgzhao.clannad.spring.service.impl;

import com.tgzhao.clannad.spring.service.ICommonService;
import org.springframework.stereotype.Service;

/**
 * Created by tgzhao on 2016/8/26.
 */
@Service("commonService2")
public class CommonServiceImpl2 implements ICommonService {
    @Override
    public String getTipMsgs(String tip) {
        return "CommonServiceImpl2 " + tip;
    }
}
