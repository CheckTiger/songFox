package cn.sxh.network.http;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpEngine implements IHttpEngine{
    @Override
    public void get(String url, Map<String, Object> params, final EngineCallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("OKHttpEngine", "onFailure: ");
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.d("OKHttpEngine", "onResponse: " +result);
                callBack.onSuccess(result);
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> params, EngineCallBack callBack) {

    }
}
