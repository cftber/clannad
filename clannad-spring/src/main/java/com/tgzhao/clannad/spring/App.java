package com.tgzhao.clannad.spring;

import com.tgzhao.clannad.spring.common.ApplicationContextHolder;
import com.tgzhao.clannad.spring.service.ICommonService;
import com.tgzhao.clannad.spring.service.impl.*;

/**
 * Created by tgzhao on 2016/8/26.
 */
public class App {

    public static void main(String[] args) {

        getBeans();
    }

    /**
     * 当impl唯一时，可通过ICommonService getBean
     * 当impl不唯一时，可通过具体的Impl klazz getBean
     * 也可通过具体的Impl对应的@Service("commonService") name getBean
     */
    public static void getBeans() {
         /* ICommonService service = ApplicationContextHolder.getBean(CommonServiceImpl2.class);
        ICommonService serviceimpl = ApplicationContextHolder.getBean(CommonServiceImpl.class);
        */
        ICommonService service = ApplicationContextHolder.getBean("commonService");
        ICommonService serviceimpl = ApplicationContextHolder.getBean("commonService2");


        System.out.println(service.getTipMsgs("app service"));
        System.out.println(serviceimpl.getTipMsgs("app serviceimpl"));
    }
}
