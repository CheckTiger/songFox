package cn.sxh.eventbus;

import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.sxh.eventbus.meta.SubscriberInfoIndex;

/**
 * @package-name: cn.sxh.songfox.eventbus
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/31 0031 : 11 :21
 * @project-name: songFox
 */
public class EventBusBuilder {

    private final static ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    boolean logSubscriberExceptions = true;
    boolean logNoSubscriberMessages = true;
    boolean sendSubscriberExceptionEvent = true;
    boolean sendNoSubscriberEvent = true;
    boolean throwSubscriberException;
    boolean eventInheritance = true;
    boolean ignoreGeneratedIndex;
    boolean strictMethodVerification;
    ExecutorService executorService = DEFAULT_EXECUTOR_SERVICE;
    List<Class<?>> skipMethodVerificationForClasses;
    List<SubscriberInfoIndex> subscriberInfoIndexes;
    Logger logger;
    MainThreadSupport mainThreadSupport;

    public EventBusBuilder() {
    }

    public EventBusBuilder logSubscriberExceptions(boolean logSubscriberExceptions) {
        this.logSubscriberExceptions = logSubscriberExceptions;
        return this;
    }

    public EventBusBuilder logNoSubscriberMessages(boolean logNoSubscriberMessages) {
        this.logNoSubscriberMessages = logNoSubscriberMessages;
        return this;
    }

    public EventBusBuilder sendSubscriberExceptionEvent(boolean sendSubscriberExceptionEvent) {
        this.sendSubscriberExceptionEvent = sendSubscriberExceptionEvent;
        return this;
    }

    public EventBusBuilder sendNoSubscriberEvent(boolean sendNoSubscriberEvent) {
        this.sendNoSubscriberEvent = sendNoSubscriberEvent;
        return this;
    }

    public EventBusBuilder throwSubscriberException(boolean throwSubscriberException) {
        this.throwSubscriberException = throwSubscriberException;
        return this;
    }

    public EventBusBuilder eventInheritance(boolean eventInheritance) {
        this.eventInheritance = eventInheritance;
        return this;
    }

    public EventBusBuilder executorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    public EventBusBuilder skipMethodVerificationForClasses(Class<?> classes) {
        if (skipMethodVerificationForClasses == null) {
            skipMethodVerificationForClasses = new ArrayList<>();
        }
        skipMethodVerificationForClasses.add(classes);
        return this;
    }

    public EventBusBuilder ignoreGeneratedIndex(boolean ignoreGeneratedIndex) {
        this.ignoreGeneratedIndex = ignoreGeneratedIndex;
        return this;
    }

    public EventBusBuilder strictMethodVerification(boolean strictMethodVerification) {
        this.strictMethodVerification = strictMethodVerification;
        return this;
    }

    public EventBusBuilder addIndex(SubscriberInfoIndex index) {
        if (subscriberInfoIndexes == null) {
            subscriberInfoIndexes = new ArrayList<>();
        }
        subscriberInfoIndexes.add(index);
        return this;
    }

    public EventBusBuilder logger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public Logger getLogger() {
        if (logger != null) {
            return logger;
        } else {
            return Logger.AndroidLogger.isAndroidLogAvailable() && getAndroidMainLooperOrNull() != null
                    ? new Logger.AndroidLogger("EventBus") : new Logger.SystemOutLogger();
        }
    }


    MainThreadSupport getMainThreadSupport(){
        if (mainThreadSupport != null) {
            return mainThreadSupport;
        } else if (Logger.AndroidLogger.isAndroidLogAvailable()) {
            Object looperOrNull = getAndroidMainLooperOrNull();
            return looperOrNull == null ? null :
                    new MainThreadSupport.AndroidHandlerMainThreadSupport((Looper) looperOrNull);
        } else {
            return null;
        }
    }

    private Object getAndroidMainLooperOrNull(){
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public EventBus installDefaultEventBus(){
        synchronized (EventBus.class) {
            if (EventBus.defaultInstance != null) {
                throw new EventBusException("Default instance already exists." +
                        " It may be only set once before it's used the first time to ensure consistent behavior.");
            }
            EventBus.defaultInstance = build();
            return EventBus.defaultInstance;
        }
    }

    public EventBus build(){
        return new EventBus(this);
    }

}
