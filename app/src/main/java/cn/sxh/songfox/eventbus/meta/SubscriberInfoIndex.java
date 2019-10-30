package cn.sxh.songfox.eventbus.meta;

/**
 * @package-name: cn.sxh.songfox.eventbus.meta
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 16 :42
 * @project-name: songFox
 */
public interface SubscriberInfoIndex {
    SubscriberInfo getSubscriberInfo(Class<?> subscriberClass);
}
