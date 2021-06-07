package cn.sxh.network.thread;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronizedTest {

    private static int queueSize = 10;
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);
    private static SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<Integer>();
    private static PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<Integer>();
    private static PriorityQueue delayQueue = new PriorityQueue();

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int a = synchronousQueue.take();
                    System.out.println(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            synchronousQueue.put(11111111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Consumer extends Thread{
        @Override
        public void run() {
            while (queue!=null&&!queue.isEmpty()) {
                try {
                    int a = queue.take();
                    System.out.println(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer extends Thread{
        @Override
        public void run() {
            while (queue.size() < 8) {
                try {
                    queue.put(1);
                    System.out.println("添加数据");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
