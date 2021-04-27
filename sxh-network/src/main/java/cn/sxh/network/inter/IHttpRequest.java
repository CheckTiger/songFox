package cn.sxh.network.inter;

import android.content.Context;

import java.util.Map;

import cn.sxh.network.HttpCallBack;

public interface IHttpRequest {

    <T> void post(Context context, Map<String, Object> params,
                  String url, final boolean cache, final HttpCallBack<T> callback);


    <T> void get(Context context, Map<String, Object> params,
                 String url, final boolean cache, final HttpCallBack<T> callback);

}
