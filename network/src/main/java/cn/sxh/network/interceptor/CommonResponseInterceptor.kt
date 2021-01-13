package cn.sxh.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class CommonResponseInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestTime = System.currentTimeMillis()
        Log.e("sxh","------>$requestTime")
        return chain.proceed(chain.request())
    }
}