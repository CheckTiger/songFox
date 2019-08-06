package cn.sxh.songfox;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.sxh.songfox.di.component.ApplicationComponent;

/**
 * @package-name: cn.sxh.songfox
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/26 0026 : 14 :01
 * @project-name: songFox
 */
public class App extends Application {
    private static Context mContext;
    public static Handler handler;
    public static int mainThreadId;
    private ApplicationComponent mApplicationComponent;
}
