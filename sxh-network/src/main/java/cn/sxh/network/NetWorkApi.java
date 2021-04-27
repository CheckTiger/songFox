package cn.sxh.network;

import java.util.HashMap;

import cn.sxh.network.bean.ThsNewsBean;
import cn.sxh.network.inter.INetworkRequestInfo;
import cn.sxh.network.interceptor.CommonRequestInterceptor;
import cn.sxh.network.interceptor.CommonResponseInterceptor;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkApi {

    private static final String HOST = "http://120.27.248.248:8601/hexinifs/rs/cms/ad/";

    private static INetworkRequestInfo iNetworkRequestInfo;
    private static HashMap<String,Retrofit> map = new HashMap<>();

    public static void init(INetworkRequestInfo requestInfo){
        iNetworkRequestInfo = requestInfo;
    }

    public static <T> T getService(Class<T> service) {
        return getRetrofit(service).create(service);
    }

    public static Retrofit getRetrofit(Class service) {
        if (map.get(HOST + service.getName()) != null) {
            return map.get(HOST + service.getName());
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(HOST);
        builder.client(getOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        map.put(HOST + service.getName(), retrofit);
        return retrofit;
    }

    public static <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(NetWorkApi.<T>getAppErrorHandler());
                observable.subscribe(observer);
                return observable;
            }
        };
    }

    public static <T> Function<T,T> getAppErrorHandler(){
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                if (t instanceof ThsNewsBean && ((ThsNewsBean) t).getContent() == null) {
//                    Exceptio
                }
                return t;
            }
        };
    }


    public static OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new CommonRequestInterceptor(iNetworkRequestInfo));
        builder.addInterceptor(new CommonResponseInterceptor());
        if (iNetworkRequestInfo != null && iNetworkRequestInfo.isDebug()) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

}
