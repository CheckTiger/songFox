package cn.sxh.songfox;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@interface Route {

    /**
     *
     *路由的路径
     * @return
     */
    String path();

    /**
     * 将路由节点进行分组，可实现动态加载
     * @return
     */
    String group() default "";
}
