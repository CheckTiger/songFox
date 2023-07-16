package cn.sxh.network.socket;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class APIManager {

    public static final String TAG = "APIManager";
    private static APIManager instance;
    private OkHttpClient client;

    public static APIManager getInstance() {
        if (instance == null) {
            synchronized (APIManager.class) {
                if (instance == null) {
                    instance = new APIManager();
                }
            }
        }
        return instance;
    }

    public APIManager(){
        client = APIManager.getOkHttpClient();
    }

    public void requestAuthorization(String url, Map<String, Object> params, final SocketCallback callBack) {
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        //{"zone":"8","time":"1660871218969","lang":"cn","user":"1835729563","version":"1.0.0"}
        for (String key : params.keySet()) {
            sb.append(key).append("=").append(params.get(key));
        }
        Log.d(TAG, "onFailure: ");
        final Request request = new Request.Builder().url(sb.toString()).build();
        Call call = client.newCall(request);
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

    private static OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(new CommonResponseInterceptor());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

}
