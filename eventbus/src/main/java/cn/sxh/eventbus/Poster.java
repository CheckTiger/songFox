package cn.sxh.eventbus;

/**
 * @package-name: cn.sxh.songfox.eventbus
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 11 :34
 * @project-name: songFox
 */
public interface Poster {

    /**
     *
     * @param subscription 订阅者也就是观察者，将会接收到事件通知
     * @param event 事件将会被发送到被观察者
     */
    void enqueue(Subscription subscription, Object event);
}
