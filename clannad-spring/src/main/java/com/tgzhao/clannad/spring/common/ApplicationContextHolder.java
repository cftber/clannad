package com.tgzhao.clannad.spring.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tgzhao on 2016/8/26.
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static volatile ApplicationContext context;
    private static Object lockObj = new Object();

    public static ApplicationContext getContext() {
        if (context == null) {
            synchronized (lockObj) {
                if (context == null) {
                    context = new ClassPathXmlApplicationContext(
                            new String[] { "classpath:conf/spring/applicationContext.xml" }
                    );
                }
            }
        }
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.context = applicationContext;
    }

    public static <T> T getBean(Class<T> klazz) {
        return getContext().getBean(klazz);
    }

    public static <T> T getBean(String name, Class<T> klazz) {
        return getContext().getBean(name, klazz);
    }

    public static <T> T getBean(String name) {
        return (T) getContext().getBean(name);
    }
}
