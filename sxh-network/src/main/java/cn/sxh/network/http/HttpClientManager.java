package cn.sxh.network.http;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpClientManager {

    public static HttpClient createHttpClient(){
        HttpParams httpParams = new BasicHttpParams();
        //设置连接超时相关配置
        HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        //设置http协议版本编码等
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(httpParams, true);
        //新建client
        return new DefaultHttpClient(httpParams);
    }
}
