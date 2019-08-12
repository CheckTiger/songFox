package cn.sxh.songfox.di.component;

import android.content.Context;

import cn.sxh.songfox.di.module.ApplicationModule;
import cn.sxh.songfox.di.scope.ContextLife;
import cn.sxh.songfox.di.scope.PerApp;
import dagger.Component;

/**
 * @package-name: cn.sxh.songfox.di
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/26 0026 : 14 :23
 * @project-name: songFox
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}
