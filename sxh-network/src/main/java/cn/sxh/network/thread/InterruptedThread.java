package cn.sxh.network.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断测试
 */
public class InterruptedThread {

    public static void main(String[] args) throws InterruptedException{

        MoonRunner runner = new MoonRunner();
        Thread thread = new Thread(runner, "MoonThread");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        runner.cancel();
    }

    static class MoonRunner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on) {
                i++;
                System.out.println("i=" + i);
            }
            System.out.println("stop");
        }

        public void cancel(){
            on = false;
        }
    }
}
