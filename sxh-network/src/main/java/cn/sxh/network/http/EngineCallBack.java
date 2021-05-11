package cn.sxh.network.http;

import android.content.Context;

import java.util.Map;

public interface EngineCallBack {

    void onPreExecute(Context context, Map<String, Object> params);

    void onError(Exception e);

    void onSuccess(String result);

    EngineCallBack DEFAULT_CALL_BACK = new EngineCallBack() {

        @Override
        public void onPreExecute(Context context, Map<String, Object> params) {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }
    };
}
