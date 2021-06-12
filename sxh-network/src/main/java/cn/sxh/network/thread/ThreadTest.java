package cn.sxh.network.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {
    public static void main(String[] args) {
        executor();
    }

    static void executor(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "songxuehu";
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(callable);
        try {
            String result = future.get();
            System.out.println("thread with future "+result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread with runnable started");
            }
        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(runnable);

    }
}
