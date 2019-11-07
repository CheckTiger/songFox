package cn.sxh.songfox.eventbus;

import android.support.annotation.Nullable;

/**
 * @package-name: cn.sxh.songfox.eventbus
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 11 :44
 * @project-name: songFox
 */
final class Subscription {
    final Object subscriber;
    final SubscriberMethod subscriberMethod;

    volatile boolean active;

    Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
        this.active = true;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Subscription) {
            Subscription otherSubscription = (Subscription) obj;
            return subscriber == otherSubscription.subscriber
                    && subscriberMethod.equals(otherSubscription.subscriberMethod);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return subscriber.hashCode() + subscriberMethod.methodString.hashCode();
    }
}
