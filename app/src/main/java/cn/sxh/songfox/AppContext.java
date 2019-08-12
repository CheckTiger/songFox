package cn.sxh.songfox;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.squareup.leakcanary.RefWatcher;

import cn.sxh.songfox.di.component.ApplicationComponent;

/**
 * @package-name: cn.sxh.songfox
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/26 0026 : 14 :01
 * @project-name: songFox
 */
public class AppContext extends Application {
    private static AppContext intance;
    private ApplicationComponent mApplicationComponent;
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        AppContext appContext = (AppContext) context.getApplicationContext();
        return appContext.refWatcher;
    }
}
