package cn.sxh.songfox.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @package-name: cn.sxh.songfox.di.scope
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/26 0026 : 14 :35
 * @project-name: songFox
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)

public @interface ContextLife {
    String value() default "Activity";
}
