package cn.sxh.eventbus.meta;

import cn.sxh.eventbus.ThreadMode;

public class SubscriberMethodInfo {

    final String methodName;
    final ThreadMode threadMode;
    final Class<?> eventType;
    final int priority;
    final boolean sticky;

    public SubscriberMethodInfo(String methodName, ThreadMode threadMode,
                                Class<?> eventType, int priority, boolean sticky) {
        this.methodName = methodName;
        this.threadMode = threadMode;
        this.eventType = eventType;
        this.priority = priority;
        this.sticky = sticky;
    }

    public SubscriberMethodInfo(String methodName, Class<?> eventType) {
        this(methodName, ThreadMode.POSTING, eventType, 0, false);
    }

    public SubscriberMethodInfo(String methodName,ThreadMode threadMode, Class<?> eventType) {
        this(methodName, threadMode, eventType, 0, false);
    }
}
