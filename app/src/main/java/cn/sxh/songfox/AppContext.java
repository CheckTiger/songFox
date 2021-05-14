package cn.sxh.songfox;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDex;

import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.sxh.network.NetWorkApi;
import cn.sxh.songfox.Interface.NetWorkRequestInfoImpl;
import cn.sxh.songfox.di.component.ApplicationComponent;
import cn.sxh.songfox.di.component.DaggerApplicationComponent;
import cn.sxh.songfox.di.module.ApplicationModule;

/**
 * @package-name: cn.sxh.songfox
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/26 0026 : 14 :01
 * @project-name: songFox
 */
@RequiresApi(api = Build.VERSION_CODES.P)
public class AppContext extends Application {
    private static AppContext instance;
    private ApplicationComponent mApplicationComponent;
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        AppContext appContext = (AppContext) context.getApplicationContext();
        return appContext.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (AppContext) getApplicationContext();
        initLeakCanary();
        initStrictMode();
        KLog.init(BuildConfig.DEBUG);
        initApplicationComponent();
        NetWorkApi.init(new NetWorkRequestInfoImpl());
//        Bugly.init(instance, "da9e68e13a", false);
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }

    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog() // 在logcat中打印违规异常信息
                            .build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }
    }

    private RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

    public static AppContext getInstance() {
        return instance;
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
