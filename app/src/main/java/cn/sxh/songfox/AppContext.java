package cn.sxh.songfox;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        initCrashStrategy();//crash异常检测上报（腾讯开源库Bugly）
    }

    private void initCrashStrategy() {
        // 获取当前包名
        String packageName = instance.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(instance);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(instance, "6cf1734e65", true,strategy);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
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

}
