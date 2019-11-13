package cn.sxh.songfox.util;

import android.content.Context;

import cn.sxh.songfox.AppContext;

/**
 * @package-name: cn.sxh.songfox.util
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/11 0011 : 14 :32
 * @project-name: songFox
 */
public class AppUtil {

    public static Context getContext(){
        return AppContext.getInstance();
    }
}
