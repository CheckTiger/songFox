package cn.sxh.network.http;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;
import java.util.Map;

public class HttpEngine implements IHttpEngine{
    @Override
    public void get(String url, Map<String, Object> params, EngineCallBack callBack) {
        HttpClient client = HttpClientManager.createHttpClient();
        HttpGet mHttpGet = new HttpGet(url);
        mHttpGet.addHeader("Connection","Keep-Alive");
        try {
            HttpResponse mHttpResponse = client.execute(mHttpGet);
            HttpEntity httpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (null != httpEntity) {
                InputStream inputStream = httpEntity.getContent();
                String response = HttpDataManager.conferStreamToString(inputStream);
                Log.e("sxh", "code:" + code + "\n result: \n" + response);
                callBack.onSuccess(response);
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void post(String url, Map<String, Object> params, EngineCallBack callBack) {

    }
}
