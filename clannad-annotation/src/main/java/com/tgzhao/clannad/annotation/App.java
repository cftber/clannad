package com.tgzhao.clannad.annotation;

import com.tgzhao.clannad.annotation.anno.Component;
import sun.applet.Main;

/**
 * Created by tgzhao on 2016/8/24.
 */
public class App {

    public static void main(String[] args) {
        try {
            Class commonentClass = Class.forName("com.tgzhao.clannad.annotation.UpperCaseComponent");
            if (commonentClass.isAnnotationPresent(Component.class)) {
                Component component = (Component)commonentClass.getAnnotation(Component.class);
                String identifier = component.identifier();
                System.out.println(String.format("Identifier for "
                        + "com.tgzhao.clannad.annotation.UpperCaseComponent is ' %s '", identifier));

            } else {
            System.out.println("com.tgzhao.clannad.annotation.UpperCaseComponent is not annotated by"
                    + " com.tgzhao.clannad.annotation.Component");
        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
