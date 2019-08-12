package cn.sxh.songfox.di.module;

import android.content.Context;

import cn.sxh.songfox.AppContext;
import cn.sxh.songfox.di.scope.ContextLife;
import cn.sxh.songfox.di.scope.PerApp;
import dagger.Module;
import dagger.Provides;

/**
 * @package-name: cn.sxh.songfox.di.module
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/26 0026 : 14 :30
 * @project-name: songFox
 */
@Module
public class ApplicationModule {
    private AppContext mAppContext;
    public ApplicationModule(AppContext application){ mAppContext = application;}

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext(){
        return mAppContext.getApplicationContext();
    }
}
