package cn.sxh.songfox.API;

import android.app.Application;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * @package-name: cn.sxh.songfox.API
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/24 0024 : 16 :53
 * @project-name: songFox
 */
public class RetrofitManager {

    private static volatile OkHttpClient client;
    private static OkHttpClient getOkHttpClient(){
        if (client == null) {
            synchronized (RetrofitManager.class) {
                client = new OkHttpClient();
//                client.newCall()
            }
        }
        return client;
    }

}
