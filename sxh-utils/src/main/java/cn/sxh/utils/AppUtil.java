package cn.sxh.utils;

import android.app.Application;
import android.content.Context;


/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/11 0011 : 14 :32
 * @project-name: songFox
 */
public class AppUtil {

    public static Context getContext(){
        return new Application();
    }
}
