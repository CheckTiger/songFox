package cn.sxh.songfox.pattern.prototypepattern;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @package-name: cn.sxh.songfox.pattern
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/28 0028 : 17 :20
 * @project-name: songFox
 */
@SuppressLint("SimpleDateFormat")
public class pattern {


    private static final ScheduledExecutorService executor = new
            ScheduledThreadPoolExecutor(1, Executors.defaultThreadFactory());
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        WordDocument document = new WordDocument();
        document.setText("原型模式");
        document.addImages("稷下学社");
        document.addImages("东林党");
        document.addImages("新派学士");
        document.addImages("三国争霸");
        document.showDocument();
        System.out.println("===================================");

        WordDocument doc = document.clone();
        doc.showDocument();
        System.out.println("===================================");

        doc.setText("修改原型模式");
        doc.showDocument();

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallel()
                .reduce((a, b) -> {
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                }).ifPresent(System.out::println);
        System.out.println("===================================");
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (haveMsgAtCurrentTime()) {
                    System.out.println(df.format(new Date()));
                    System.out.println("⼤家注意了，我要发消息了");
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public static boolean haveMsgAtCurrentTime() {
        //查询数据库，有没有当前时间需要发送的消息
        //这⾥省略实现，直接返回true
        return true;
    }


}
