package cn.sxh.eventbus.meta;

import cn.sxh.eventbus.SubscriberMethod;

/**
 * @package-name: cn.sxh.songfox.eventbus.meta
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 16 :29
 * @project-name: songFox
 * 观察者主要信息包括类名 方法 等等
 */
public interface SubscriberInfo {

    Class<?> getSubscriberClass();

    SubscriberMethod[] getSubscriberMethods();

    SubscriberInfo getSuperSubscriberInfo();

    boolean shouldCheckSuperclass();
}
