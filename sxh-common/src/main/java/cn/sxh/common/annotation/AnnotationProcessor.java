package cn.sxh.common.annotation;

import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void main(String[] args) {
        Method[] methods = AnnotationTest.class.getDeclaredMethods();
        for (Method m : methods) {
            GET get = m.getAnnotation(GET.class);
            System.out.println(get.value());
        }
    }
}
