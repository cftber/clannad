package com.tgzhao.clannad.annotation;

import com.tgzhao.clannad.annotation.anno.Component;

/**
 * Created by tgzhao on 2016/8/24.
 */
@Component(identifier="upper")
public class UpperCaseComponent {

    public String doWork(String input) {
        if(input != null) {
            return input.toUpperCase();
        } else {
            return null;
        }
    }
}
