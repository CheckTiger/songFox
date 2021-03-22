package cn.sxh.network;

import android.content.Context;

import java.util.Map;

import cn.sxh.network.inter.IHttpRequest;
import okhttp3.OkHttpClient;

public class OKHttpRequest  implements IHttpRequest {

    private OkHttpClient mOKHttpClient;

    public OKHttpRequest() {
        this.mOKHttpClient = new OkHttpClient();
    }

    @Override
    public <T> void post(Context context, Map<String, Object> params,
                         String url, boolean cache, HttpCallBack<T> callback) {

    }

    @Override
    public <T> void get(Context context, Map<String, Object> params,
                        String url, boolean cache, HttpCallBack<T> callback) {

    }
}
