package cn.sxh.songfox.di.component;

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
@Component(modules = ApplicationComponent.class)
public interface ApplicationComponent {
}
