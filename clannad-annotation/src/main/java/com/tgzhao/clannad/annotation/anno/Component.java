package com.tgzhao.clannad.annotation.anno;

import java.lang.annotation.*;

/**
 * Created by tgzhao on 2016/8/24.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Component {
    String identifier () default "";
}
