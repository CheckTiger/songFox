package cn.sxh.network.interceptor

import cn.sxh.network.inter.INetworkRequestInfo
import okhttp3.Interceptor
import okhttp3.Response

class CommonRequestInterceptor (var requestInfo: INetworkRequestInfo): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var builder=chain.request().newBuilder()
        builder.addHeader("os", "android")
        builder.addHeader("Source", "Source")
        builder.addHeader("versionName", requestInfo.appVersionName())
        builder.addHeader("versionCode", requestInfo.appVersionCode())
        return chain.proceed(builder.build())
    }

}