package cn.sxh.technology;

import android.text.TextUtils;
import android.webkit.CookieManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载工具
 */

public class DownloadUtil {
    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;

    private final Map<String, Call> callMap;


    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSslSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .retryOnConnectionFailure(true)
                .build();
        callMap = new HashMap<>();
    }


    /**
     * @param url      下载连接
     * @param saveDir  储存下载文件的SDCard目录
     * @param listener 下载监听
     */
    public void download(final String url, final String saveDir, final String fileName, final String type, final OnDownloadListener listener) {
        CookieManager cookieManager = CookieManager.getInstance();
        String cookie = cookieManager.getCookie(url);
        Request request = new Request.Builder().url(url)
                .addHeader("Accept-Encoding", "identity")
                .addHeader("Connection", "close").build();
        Call call = okHttpClient.newCall(request);
        if (callMap.get(type) != null) {
            if (callMap.get(type).isExecuted()) {
                callMap.get(type).cancel();
            }
            callMap.remove(type);
        }
        //加入map管理
        callMap.put(type, call);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
                callMap.remove(type);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(saveDir,fileName);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess();
                    callMap.remove(type);
                } catch (Exception e) {
                    listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    public void cancelCall(String type) {
        if (!TextUtils.isEmpty(type)) {
            Call call = callMap.get(type);
            if (call != null && call.isExecuted()) {
                call.cancel();
            }
        }
    }

    public void cancelAllCall() {
        Iterator<Map.Entry<String, Call>> iterator = callMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Call> entry = iterator.next();
            if (entry.getValue() != null) {
                if (entry.getValue().isExecuted())
                    entry.getValue().cancel();
            }
        }
    }


    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }

    public void onDestroy() {

    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}