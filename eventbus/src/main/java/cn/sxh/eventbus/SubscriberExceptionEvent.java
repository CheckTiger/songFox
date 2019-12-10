package cn.sxh.eventbus;

/**
 * @package-name: cn.sxh.songfox.eventbus
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 20 :28
 * @project-name: songFox
 */
public final class SubscriberExceptionEvent {

    /**The {@link EventBus} instance to with the original event was posted to.*/
    public final EventBus eventBus;
    public final Throwable throwable;
    /** The original event that could not be delivered to any subscriber. */
    public final Object causingEvent;
    /** The subscriber that threw the Throwable. */
    public final Object causingSubscriber;

    public SubscriberExceptionEvent(EventBus eventBus, Throwable throwable, Object causingEvent, Object causingSubscriber) {
        this.eventBus = eventBus;
        this.throwable = throwable;
        this.causingEvent = causingEvent;
        this.causingSubscriber = causingSubscriber;
    }
}
