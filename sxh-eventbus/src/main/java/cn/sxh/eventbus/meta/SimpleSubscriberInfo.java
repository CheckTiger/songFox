package cn.sxh.eventbus.meta;

import cn.sxh.eventbus.SubscriberMethod;

public class SimpleSubscriberInfo extends AbstractSubscriberInfo{

    private final SubscriberMethodInfo[] methodInfos;
    public SimpleSubscriberInfo(Class subscriberClass, SubscriberMethodInfo[] methodInfos,
                                boolean shouldCheckSuperClass) {
        super(subscriberClass, null, shouldCheckSuperClass);
        this.methodInfos = methodInfos;
    }

    @Override
    public synchronized SubscriberMethod[] getSubscriberMethods() {
        int length = methodInfos.length;
        SubscriberMethod[] methods = new SubscriberMethod[length];
        for (int i = 0; i < length; i++) {
            SubscriberMethodInfo methodInfo = methodInfos[i];
            methods[i] = createSubscriberMethod(methodInfo.methodName,
                    methodInfo.eventType, methodInfo.threadMode, methodInfo.priority, methodInfo.sticky);
        }
        return methods;
    }
}
