package cn.sxh.network;

import android.content.Context;

public class HttpUtils {

    private OKHttpRequest mOkHttpRequest;

    public HttpUtils(Context context) {
        this.mOkHttpRequest = new OKHttpRequest();
//        mOkHttpRequest.with(context);
    }
}
