package cn.sxh.network.http;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils{

    private String host;
    private int method_type=GET;
    public static final int POST = 0x0011;
    public static final int GET  = 0x0012;

    private Context mContext;
    private Map<String, Object> mParams;

    public HttpUtils(Context context) {
        this.mContext = context;
        mParams = new HashMap<>();
    }

    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public HttpUtils host(String url){
        host = url;
        return this;
    }

    public HttpUtils setMethod(int method){
        method_type = method;
        return this;
    }

    public HttpUtils addParam(String key, Object object) {
        mParams.put(key, object);
        return this;
    }

    public HttpUtils addParams(Map<String, Object> params) {
        mParams.putAll(params);
        return this;
    }


    public void execute(EngineCallBack callBack) {
        if (callBack == null) {
            callBack = EngineCallBack.DEFAULT_CALL_BACK;
        }
        if (method_type == POST) {
            post(host,mParams,callBack);
        }

        if (method_type == GET) {
            get(host,mParams,callBack);
        }
    }


    private static IHttpEngine mHttpEngine = new OKHttpEngine();

    public static void initHttpEngine(IHttpEngine engine) {
        mHttpEngine = engine;
    }

    /**
     * 每次自带引擎
     * @param httpEngine
     */
    public void exchangeEngine(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }


    private void get(String url, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.get(url,params,callBack);
    }


    private void post(String url, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.post(url, params, callBack);
    }
}
