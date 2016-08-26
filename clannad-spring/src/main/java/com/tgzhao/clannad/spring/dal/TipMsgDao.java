package com.tgzhao.clannad.spring.dal;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by tgzhao on 2016/8/26.
 */
@Component
public class TipMsgDao {

    @PostConstruct
    public void init() {
        System.out.println("tipmsg dao inited!");
    }

    public String getTipMsg(String id) {
        return "tipmsg dao return msg is : " + id;
    }
}
