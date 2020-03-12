package cn.sxh.eventbus;

/**
 * @package-name: cn.sxh.songfox.eventbus
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 20 :34
 * @project-name: songFox
 */
public final class NoSubscriberEvent {

    /**The {@link EventBus} instance to with the original event was posted to.*/
    public final EventBus eventBus;
    /** The original event that could not be delivered to any subscriber. */
    public final Object originalEvent;

    public NoSubscriberEvent(EventBus eventBus, Object originalEvent) {
        this.eventBus = eventBus;
        this.originalEvent = originalEvent;
    }
}
