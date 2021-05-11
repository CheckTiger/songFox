package cn.sxh.network.http;

import android.content.Context;

import java.util.Map;

public abstract class HttpCallBack implements EngineCallBack{
    @Override
    public void onPreExecute(Context context, Map<String, Object> params) {
        params.put("sdk", "29");
        params.put("app_code", "1.0");
        params.put("app_version", "1.0");
        params.put("phone_type", "xiaomi");
        params.put("platform", "android");
        params.put("app_network", "wifi");
        onPreExecute();
    }

    private void onPreExecute() {

    }
}
