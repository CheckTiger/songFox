package cn.sxh.eventbus.meta;

import java.lang.reflect.Method;

import cn.sxh.eventbus.EventBusException;
import cn.sxh.eventbus.SubscriberMethod;
import cn.sxh.eventbus.ThreadMode;

public class AbstractSubscriberInfo implements SubscriberInfo{
    private final Class subscriberClass;
    private final Class<? extends SubscriberInfo> superSubscriberInfoClass;
    private final boolean shouldCheckSuperClass;

    public AbstractSubscriberInfo(Class subscriberClass, Class<? extends SubscriberInfo> superSubscriberInfoClass,
                                  boolean shouldCheckSuperClass) {
        this.subscriberClass = subscriberClass;
        this.superSubscriberInfoClass = superSubscriberInfoClass;
        this.shouldCheckSuperClass = shouldCheckSuperClass;
    }

    @Override
    public Class<?> getSubscriberClass() {
        return subscriberClass;
    }

    @Override
    public SubscriberMethod[] getSubscriberMethods() {
        return new SubscriberMethod[0];
    }

    @Override
    public SubscriberInfo getSuperSubscriberInfo() {
        if (superSubscriberInfoClass == null) {
            return null;
        }
        try {
            return superSubscriberInfoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean shouldCheckSuperclass() {
        return shouldCheckSuperClass;
    }

    protected SubscriberMethod createSubscriberMethod(String methodName, Class<?> eventType) {
        return createSubscriberMethod(methodName, eventType, ThreadMode.POSTING, 0, false);
    }

    protected SubscriberMethod createSubscriberMethod(String methodName, Class<?> eventType, ThreadMode threadMode) {
        return createSubscriberMethod(methodName, eventType, threadMode, 0, false);
    }

    protected SubscriberMethod createSubscriberMethod(String methodName, Class<?> eventType,
                                                    ThreadMode mode, int priority, boolean sticky) {
        try {
            Method method = subscriberClass.getDeclaredMethod(methodName, eventType);
            return new SubscriberMethod(method, mode, eventType, priority, sticky);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EventBusException("Could not find subscriber method in "
                    +subscriberClass+". Maybe a missing ProGuard rule?",e);
        }
    }
}
