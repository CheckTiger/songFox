package cn.sxh.network.thread;

public class SynchronizedDemo {

    public  synchronized void test0(){
        System.out.println("普通方法0，作用域为普通方法，锁住的是当前条用的对象");
    }

    public  synchronized void test1(){
        System.out.println("普通方法1，作用域为普通方法，锁住的是当前条用的对象");
    }

    public  synchronized void test2(){
        System.out.println("普通方法2，作用域为普通方法，锁住的是当前条用的对象");
    }


    public  synchronized void test3(){
        System.out.println("普通方法3，作用域为普通方法，锁住的是当前条用的对象");
    }
}
