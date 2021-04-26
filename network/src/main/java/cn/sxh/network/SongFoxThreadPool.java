package cn.sxh.network;

import android.os.Looper;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class SongFoxThreadPool {

    private static final int THREAD_POOL_SIZE = 10;
    private static final String THREAD_POOL_NAME = "snow-thread-pool";
    private static ScheduledThreadPoolExecutor mThreadPoolExecutor;

    public synchronized static ScheduledThreadPoolExecutor getThreadPool(){
        if (mThreadPoolExecutor == null || mThreadPoolExecutor.isShutdown()) {
            mThreadPoolExecutor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE, new ThreadFactory() {

                private final AtomicInteger mAtomicInteger = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(THREAD_POOL_NAME+"-child-"+mAtomicInteger.getAndIncrement());
                }
            });
            mThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
            mThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
        }
        mThreadPoolExecutor.purge();
        return mThreadPoolExecutor;
    }

    public synchronized static void destroyThreadPool(){
        cn.sxh.network.SongFoxThreadPool.destroyThreadPool();
    }

    public synchronized static void cancelTaskFuture(ScheduledFuture<?> taskFuture, boolean mayInterruptIfRunning) {
        cn.sxh.network.SongFoxThreadPool.cancelTaskFuture(taskFuture,mayInterruptIfRunning);
    }

    public synchronized static void executeRunnable(Runnable runnable) {
        cn.sxh.network.SongFoxThreadPool.executeRunnable(runnable);
    }


    public synchronized static void executeOnIO(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            executeRunnable(runnable);
        }
    }



}
