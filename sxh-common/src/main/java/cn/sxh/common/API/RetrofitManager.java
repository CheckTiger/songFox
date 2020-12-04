package cn.sxh.common.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @package-name: cn.sxh.common.API
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/24 0024 : 16 :53
 * @project-name: songFox
 */
public class RetrofitManager {

    private static volatile OkHttpClient client;
    private static volatile songFoxApi foxApi;
//    private static final String HOST = "http://v.juhe.cn/";
//    private static final String HOST = "http://120.27.248.248:8601/hexinifs/rs/cms/ad/";
    private static final String HOST = "http://203.174.48.144:8601/hexinifs/rs/cms/ad/";
    public static songFoxApi getInstance(){
        if (foxApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(HOST)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            foxApi = retrofit.create(songFoxApi.class);
        }
        return foxApi;
    }

    private static OkHttpClient getOkHttpClient(){
        if (client == null) {
            synchronized (RetrofitManager.class) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20*1000, TimeUnit.MILLISECONDS)
                .readTimeout(20*1000,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true);
                client = builder.build();
            }
        }
        return client;
    }

}
